package tomek;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomek on 02.06.2017.
 */

public class UserLogged extends User implements JSONStringer {
    private String token;
    private String id;
    private String avatarid;
    private static UserLogged userLoggedInstance = null;
    private List<UserRegistered> friends;




    public static UserLogged setUserLoggedInstance(String email, String userName, String token, String id) {
        if (userLoggedInstance == null)
            userLoggedInstance = new UserLogged(email, userName, token, id);

        return userLoggedInstance;
    }

    public static UserLogged getUserLoggedInstance()
    {
        return userLoggedInstance;
    }

    public UserLogged(String email, String userName, String token, String id) {
        super(email, userName);
        this.token = token;
        this.id = id;
        friends = new ArrayList<UserRegistered>();

    }



    public void setFriends(ArrayList<UserRegistered> friendList)
    {
        friends=friendList;
    }

    public static JSONObject addFriend(String id, String token)
    {
        String scope = "friendship";
        String method = "inviteFriend";

        JSONObject procedureObject = new JSONObject();
        JSONObject headerObject = new JSONObject();
        JSONObject payloadObject = new JSONObject();
        JSONObject finalObject = new JSONObject();

        try {
            headerObject.put("token", token);
            procedureObject.put("scope", scope);
            procedureObject.put("method", method);
            payloadObject.put("id", id);
            finalObject.put("procedure", procedureObject);
            finalObject.put("meta", headerObject);
            finalObject.put("payload", payloadObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return finalObject;

    }

    public static JSONObject removeFriend(String id, String token)
    {

        String scope = "friendship";
        String method = "removeFriend";

        JSONObject procedureObject = new JSONObject();
        JSONObject headerObject = new JSONObject();
        JSONObject payloadObject = new JSONObject();
        JSONObject finalObject = new JSONObject();

        try {
            headerObject.put("token", token);
            procedureObject.put("scope", scope);
            procedureObject.put("method", method);
            payloadObject.put("id", id);
            finalObject.put("procedure", procedureObject);
            finalObject.put("meta", headerObject);
            finalObject.put("payload", payloadObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return finalObject;
    }
    public static JSONObject rejectFriend(String id, String token)
    {

        String scope = "friendship";
        String method = "rejectFriendshipInvite";

        JSONObject procedureObject = new JSONObject();
        JSONObject headerObject = new JSONObject();
        JSONObject payloadObject = new JSONObject();
        JSONObject finalObject = new JSONObject();

        try {
            headerObject.put("token", token);
            procedureObject.put("scope", scope);
            procedureObject.put("method", method);
            payloadObject.put("id", id);
            finalObject.put("procedure", procedureObject);
            finalObject.put("meta", headerObject);
            finalObject.put("payload", payloadObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return finalObject;
    }
    public static JSONObject acceptFriend(String id, String token)
    {
        String scope = "friendship";
        String method = "acceptFriendshipInvite";

        JSONObject procedureObject = new JSONObject();
        JSONObject headerObject = new JSONObject();
        JSONObject payloadObject = new JSONObject();
        JSONObject finalObject = new JSONObject();

        try {
            headerObject.put("token", token);
            procedureObject.put("scope", scope);
            procedureObject.put("method", method);
            payloadObject.put("id", id);
            finalObject.put("procedure", procedureObject);
            finalObject.put("meta", headerObject);
            finalObject.put("payload", payloadObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return finalObject;
    }

    public static JSONObject getFriends(String token)
    {
        String scope = "friendship";
        String method = "getFriendsList";

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


    public static JSONObject LoggedUserInfo(String token) {

        String scope = "user";
        String method = "me";

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
