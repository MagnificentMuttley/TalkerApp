package tomek;

import org.json.JSONObject;

/**
 * Created by Tomek on 02.06.2017.
 */

public class UserLogged extends User implements JSONStringer {
    private String token;

    public UserLogged(String email, String userName, String token) {
        super(email, userName);
        this.token = token;

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override

    public JSONObject JSONStrigify() {
        return null;
    }
}
