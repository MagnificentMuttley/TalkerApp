package tomek;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.Serializable;

/**
 * Created by tomek on 06.05.2017.
 */

public abstract class User implements Serializable, tomek.JSONStringer {
    private String email;
    private String userName;

    public User(String email, String userName) {
        this.email = email;
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }


}