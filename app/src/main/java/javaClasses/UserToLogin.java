package javaClasses;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by tomek on 09.06.2017.
 */

public class UserToLogin {

private String email;
    private String password;

    public UserToLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public JSONObject JSONStrigify() {
        String scope = "auth";
        String method = "signin";

        JSONObject procedureObject = new JSONObject();
        JSONObject headerObject = new JSONObject();
        JSONObject payloadObject = new JSONObject();

        JSONObject finalObject = new JSONObject();

        try {
            procedureObject.put("scope", scope);
            procedureObject.put("method", method);
            payloadObject.put("password", getPassword());
            payloadObject.put("email", this.getEmail());
            finalObject.put("procedure", procedureObject);
            finalObject.put("meta", headerObject);
            finalObject.put("payload", payloadObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return finalObject;
    }
}
