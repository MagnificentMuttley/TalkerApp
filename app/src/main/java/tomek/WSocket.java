package tomek;

import android.util.Log;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketFactory;

import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by tomek on 06.05.2017.
 */

public class WSocket {


    private static final String SERVER = "ws://talker-node.herokuapp.com";
    private static final int TIMEOUT = 5000;
    private static WSocket wSocketInstance = null;

    public WebSocketFactory factory;
    public WebSocket ws;
    public String action;
    private ExecutorService es;
    public Future<WebSocket> future;


    private WSocket() throws Exception {
        factory = new WebSocketFactory().setConnectionTimeout(TIMEOUT);
        ws = factory.createSocket(SERVER);



        ws.addListener(new WebSocketAdapter() {
            @Override
            public void onTextMessage(WebSocket websocket, String message) throws Exception {
                Log.d("Odebrano", message);
                JSONObject jsonMsg = new JSONObject(message);
                //JSONObject jsonMsgProcedure = jsonMsg.getJSONObject("procedure");
                // String jsonScope = jsonMsgProcedure.getString("scope");
                String messageStatus = jsonMsg.getString("status");
                String messagePayload = jsonMsg.getString("payload");
                Log.d("msgStatus", messageStatus);
                Log.d("msgPayload", messagePayload);

                action = messageStatus;
            }
        });
        es = Executors.newSingleThreadExecutor();
        future = ws.connect(es);
        //ws.connectAsynchronously();
    }

    public static WSocket getwSocketInstance() {
        if (wSocketInstance == null)
            try {
                wSocketInstance = new WSocket();
            } catch (Exception ex) {
            }
        return wSocketInstance;
    }


    public void sendData(String jsonString) {
        ws.sendText(jsonString);
        //ws.sendText("PING");

        Log.d("Wiadomosc", "Wyslano: " + jsonString);
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
