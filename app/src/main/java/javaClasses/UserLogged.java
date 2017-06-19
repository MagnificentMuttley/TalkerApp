package javaClasses;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Tomek on 02.06.2017.
 */

public class UserLogged extends User implements JSONStringer {
    private String token;
    private String id;
    private String avatarid;
    private static UserLogged userLoggedInstance = null;
    private ArrayList<UserRegistered> friends;

    public String getId() {
        return id;
    }

    public static UserLogged setUserLoggedInstance(String email, String userName, String token, String id) {
        if (userLoggedInstance == null)
            userLoggedInstance = new UserLogged(email, userName, token, id);

        return userLoggedInstance;
    }

    public static UserLogged getUserLoggedInstance() {
        return userLoggedInstance;
    }

    public UserLogged(String email, String userName, String token, String id) {
        super(email, userName);
        this.token = token;
        this.id = id;
        friends = new ArrayList<UserRegistered>();

    }


    public static void setFriends(ArrayList<UserRegistered> friendList) {
        userLoggedInstance.friends = friendList;
    }

    public static JSONObject getMyChatRooms()
    {
        String scope = "groupChat";
        String method = "myChatRooms";

        JSONObject procedureObject = new JSONObject();
        JSONObject headerObject = new JSONObject();
        JSONObject payloadObject = new JSONObject();
        JSONObject finalObject = new JSONObject();

        try {
            headerObject.put("token", getUserLoggedInstance().token);
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

    public static JSONObject addFriend(String id) {
        String scope = "friendship";
        String method = "inviteFriend";

        JSONObject procedureObject = new JSONObject();
        JSONObject headerObject = new JSONObject();
        JSONObject payloadObject = new JSONObject();
        JSONObject finalObject = new JSONObject();

        try {
            headerObject.put("token", getUserLoggedInstance().getToken());
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

    public static JSONObject getMyChats() {
        String scope = "groupChat";
        String method = "myChatRooms";

        JSONObject procedureObject = new JSONObject();
        JSONObject headerObject = new JSONObject();
        JSONObject payloadObject = new JSONObject();
        JSONObject finalObject = new JSONObject();

        try {
            headerObject.put("token", UserLogged.getUserLoggedInstance().token);
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

    public static JSONObject inviteToChat(String id2) {
        String scope = "groupChat";
        String method = "newRoom";

        JSONObject procedureObject = new JSONObject();
        JSONObject headerObject = new JSONObject();
        JSONObject payloadObject = new JSONObject();
        JSONObject finalObject = new JSONObject();

        ArrayList<String> lista = new ArrayList<String>();
        lista.add(id2);
        lista.add(getUserLoggedInstance().getId());
        try {
            headerObject.put("token", getUserLoggedInstance().getToken());
            procedureObject.put("scope", scope);
            procedureObject.put("method", method);
            // payloadObject.put("invitees", getUserLoggedInstance().getId());
            payloadObject.put("invitees", lista);
            finalObject.put("procedure", procedureObject);
            finalObject.put("meta", headerObject);
            finalObject.put("payload", payloadObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return finalObject;
    }

    public static JSONObject sendMessage(String chatID, String message)
    {
        String scope = "groupChat";
        String method = "newMessage";

        JSONObject procedureObject = new JSONObject();
        JSONObject headerObject = new JSONObject();
        JSONObject payloadObject = new JSONObject();
        JSONObject finalObject = new JSONObject();

        try {
            headerObject.put("token", getUserLoggedInstance().token);
            procedureObject.put("scope", scope);
            procedureObject.put("method", method);
            // payloadObject.put("invitees", getUserLoggedInstance().getId());
            payloadObject.put("roomId", chatID);
            payloadObject.put("content", message);
            finalObject.put("procedure", procedureObject);
            finalObject.put("meta", headerObject);
            finalObject.put("payload", payloadObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return finalObject;
    }

    public static JSONObject getMessages() {
        String scope = "groupChat";
        String method = "getMessages";

        JSONObject procedureObject = new JSONObject();
        JSONObject headerObject = new JSONObject();
        JSONObject payloadObject = new JSONObject();
        JSONObject finalObject = new JSONObject();

        try {
            headerObject.put("token", UserLogged.getUserLoggedInstance().token);
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

    public static JSONObject removeFriend(String id  ) {

        String scope = "friendship";
        String method = "removeFriend";

        JSONObject procedureObject = new JSONObject();
        JSONObject headerObject = new JSONObject();
        JSONObject payloadObject = new JSONObject();
        JSONObject finalObject = new JSONObject();

        try {
            headerObject.put("token", getUserLoggedInstance().getToken());
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

    public static JSONObject rejectFriend(String id) {

        String scope = "friendship";
        String method = "rejectFriendshipInvite";

        JSONObject procedureObject = new JSONObject();
        JSONObject headerObject = new JSONObject();
        JSONObject payloadObject = new JSONObject();
        JSONObject finalObject = new JSONObject();

        try {
            headerObject.put("token", getUserLoggedInstance().getToken());
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

    public static JSONObject acceptFriend(String id) {
        String scope = "friendship";
        String method = "acceptFriendshipInvite";

        JSONObject procedureObject = new JSONObject();
        JSONObject headerObject = new JSONObject();
        JSONObject payloadObject = new JSONObject();
        JSONObject finalObject = new JSONObject();

        try {
            headerObject.put("token", getUserLoggedInstance().getToken());
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

    public static JSONObject getFriends() {
        String scope = "friendship";
        String method = "getFriendsList";

        JSONObject procedureObject = new JSONObject();
        JSONObject headerObject = new JSONObject();
        JSONObject payloadObject = new JSONObject();
        JSONObject finalObject = new JSONObject();

        try {
            headerObject.put("token", getUserLoggedInstance().getToken());
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


    public static JSONObject getFriendsRequests() {
        String scope = "friendship";
        String method = "getInvitesList";

        JSONObject procedureObject = new JSONObject();
        JSONObject headerObject = new JSONObject();
        JSONObject payloadObject = new JSONObject();
        JSONObject finalObject = new JSONObject();

        try {
            headerObject.put("token", getUserLoggedInstance().getToken());
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

    public static JSONObject updateInfo(String id, String username, String password, String email) {
        String scope = "user";
        String method = "update";

        JSONObject procedureObject = new JSONObject();
        JSONObject headerObject = new JSONObject();
        JSONObject payloadObject = new JSONObject();
        JSONObject finalObject = new JSONObject();
        JSONObject dataObject = new JSONObject();

        try {
            headerObject.put("token", getUserLoggedInstance().getToken());

            procedureObject.put("scope", scope);
            procedureObject.put("method", method);

            dataObject.put("username", username);
            dataObject.put("password", password);
            dataObject.put("email", email);

            payloadObject.put("id", id);
            payloadObject.put("data", dataObject);

            finalObject.put("procedure", procedureObject);
            finalObject.put("meta", headerObject);
            finalObject.put("payload", payloadObject);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return finalObject;
    }

    public static JSONObject loggedUserInfo(String token) {

        String scope = "user";
        String method = "me";

        JSONObject procedureObject = new JSONObject();
        JSONObject headerObject = new JSONObject();
        JSONObject payloadObject = new JSONObject();
        JSONObject finalObject = new JSONObject();

        try {
            headerObject.put("token",token);
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
