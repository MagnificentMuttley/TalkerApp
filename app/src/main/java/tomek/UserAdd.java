package tomek;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by tomek on 06.05.2017.
 */

public class UserAdd extends User implements JSONStringer {
    private String password;
    private String token = null;

    public UserAdd(String email, String password, String userName) {

        super(email, userName);
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {

        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public JSONObject JSONStrigify() {
        String scope = "auth";
        String method = "register";

        JSONObject procedureObject = new JSONObject();
        JSONObject headerObject = new JSONObject();
        JSONObject payloadObject = new JSONObject();

        JSONObject finalObject = new JSONObject();

        try {
            procedureObject.put("scope", scope);
            procedureObject.put("method", method);
            payloadObject.put("password", this.getPassword());
            payloadObject.put("username", this.getUserName());
            payloadObject.put("email", this.getEmail());
            headerObject.put("token", this.getToken());
            finalObject.put("procedure", procedureObject);
            finalObject.put("meta", headerObject);
            finalObject.put("payload", payloadObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return finalObject;
    }
}
