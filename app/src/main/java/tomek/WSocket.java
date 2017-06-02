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


    public void sendData(String jsonString) {
        ws.sendText(jsonString);
        Log.d("Wiadomosc", "Wyslano: "+jsonString);
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
