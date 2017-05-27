package tomek;

import android.util.Log;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * Created by tomek on 06.05.2017.
 */

public class WSocket {


    private static final String SERVER = "ws://talker-node.herokuapp.com";
    private static final int TIMEOUT = 5000;
    private static WSocket wSocketInstance = null;

    public WebSocketFactory factory;
    public WebSocket ws;


    private WSocket() throws Exception {
        factory = new WebSocketFactory().setConnectionTimeout(TIMEOUT);
        ws = factory.createSocket(SERVER);

        ws.addListener(new WebSocketAdapter() {
            @Override
            public void onTextMessage(WebSocket websocket, String message) throws Exception {
                Log.d("Odebrano", message);
            }
        });

        ws.connectAsynchronously();
    }

    public static WSocket getwSocketInstance(){
        if(wSocketInstance==null)
            try {
                wSocketInstance = new WSocket();
            }
            catch (Exception ex)
            {}
        return wSocketInstance;
    }

    public void sendData(Object obj) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(obj);
            out.flush();
            byte[] serializedBytes = bos.toByteArray();
            ws.sendBinary(serializedBytes);


            Log.d("Wiadomość", "Wysłano :" + serializedBytes.toString());


            ByteArrayInputStream bis = new ByteArrayInputStream(serializedBytes);
            ObjectInput in = null;
            try {
                in = new ObjectInputStream(bis);
                Object o = in.readObject();
                if (in != null) {
                    in.close();
                }
                UserAdd useradded = (UserAdd) o;
                Log.d("Wiadomość", "Wysłano :" + useradded.getUserName() + " " + useradded.getEmail() + " " + useradded.getPassword());

            } catch (Exception ex) {
                // ignore close exception
            }


        } catch (Exception ex) {
            Log.d("Wiadomosc", "Nie wysłano");

            // ignore close exception
        }

    }

    public void disconnect() {
        ws.disconnect();
    }
   /* private static WebSocket connect() throws IOException, WebSocketException
    {
        return new WebSocketFactory()
                .setConnectionTimeout(TIMEOUT)
                .createSocket(SERVER)
                .addListener(new WebSocketAdapter() {
                    // A text message arrived from the server.
                    public void onTextMessage(WebSocket websocket, String message) {
                        Log.d("COS", message);
                    }
                })
                .addExtension(WebSocketExtension.PERMESSAGE_DEFLATE)
                .connect();
    }
*/

}
