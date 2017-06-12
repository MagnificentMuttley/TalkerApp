package tomek;

import org.json.JSONException;
import org.json.JSONObject;
import talkerapp.talkerapp.SearchFriendsActivity;

/**
 * Created by tomek on 10.06.2017.
 */

public class UserRegistered {
    String username;
    String email;
    String id;
    String avatarId;
    
    public UserRegistered(String username, String email, String id) {
        this.username = username;
        this.email = email;
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getId() {
        return id;
    }
    
    public static JSONObject getAllUsers(String token) {
        String scope = "user";
        String method = "list";
        
        JSONObject procedureObject = new JSONObject();
        JSONObject headerObject = new JSONObject();
        JSONObject payloadObject = new JSONObject();
        JSONObject finalObject = new JSONObject();
        
        try {
            headerObject.put("token", token);
            procedureObject.put("scope", scope);
            procedureObject.put("method", method);
            finalObject.put("procedure", procedureObject);
            finalObject.put("meta", headerObject);
            finalObject.put("payload", payloadObject);
            
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return finalObject;
    }
}