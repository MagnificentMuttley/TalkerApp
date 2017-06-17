package talkerapp.talkerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import talkerapp.talkerapp.friendsList.FriendsListAdapter;
import javaClasses.UserLogged;
import javaClasses.UserRegistered;
import javaClasses.WSocket;

public class FriendsListActivity extends AppCompatActivity {
    private ArrayList<MyButton> buttons;
    private ListView usersContainer;
    private FriendsListAdapter adapter;
    private ArrayList<UserRegistered> friends;
    private Toolbar toolbar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends_list_screen);
        Intent intent = getIntent();
        
        initControls();
        
        getAllFriends();
        for(int i = 0; i < friends.size(); i++)
        {
            UserRegistered user = friends.get(i);
            MyButton button = buttons.get(i);
            displayItem(user, button);
        }
    }
    
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        friends.clear();
        buttons.clear();
    }
    
    private void initControls() {
        toolbar = (Toolbar)findViewById(R.id.toolbarT);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.my_friends);
        
        buttons = new ArrayList<MyButton>();
        friends = new ArrayList<UserRegistered>();
        adapter = new FriendsListAdapter(FriendsListActivity.this, new ArrayList<UserRegistered>(), new ArrayList<MyButton>());
        
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });
        
        usersContainer = (ListView) findViewById(R.id.friendsContainer);
        RelativeLayout container = (RelativeLayout) findViewById(R.id.container);
        
        usersContainer.setAdapter(adapter);
    }
    
    public void displayItem(UserRegistered user, MyButton button) {
        adapter.add(user, button);
        adapter.notifyDataSetChanged();
    }

    protected void getAllFriends() {
        WSocket wSocket = WSocket.getwSocketInstance();
        wSocket.sendData(UserLogged.getFriends(UserLogged.getUserLoggedInstance().getToken()).toString());

        synchronized (wSocket.notifier) {
            try {
                wSocket.notifier.wait(5000);

                JSONArray payload = wSocket.jsonMsg.getJSONArray("payload");
                for (int i = 0; i < payload.length(); i++) {
                    JSONObject userRegistered = payload.getJSONObject(i);
                    UserRegistered registered = new UserRegistered(userRegistered.getString("username"),
                            userRegistered.getString("email"),
                            userRegistered.getString("id"));
                    friends.add(registered);
                    Log.d("Friend: ", registered.getUsername());
                    buttons.add(new MyButton(this, Integer.parseInt(registered.getId())));
                }
                UserLogged.setFriends(friends);

            } catch (Exception exep) {
            }
        }

    }

    public static void inviteToChat(int id)
    {
        WSocket wSocket=WSocket.getwSocketInstance();
        // tutaj dodaj id
        wSocket.sendData(UserLogged.inviteToChat(Integer.toString(id), UserLogged.getUserLoggedInstance().getToken()).toString());

        synchronized (wSocket.notifier) {
            try {
                wSocket.notifier.wait(4000);

            } catch (Exception exep) {
            }
//            getAllFriends();
        }
    }

    public static void removeFromFriends(int id)
    {
        boolean dodano = false;
        WSocket wSocket = WSocket.getwSocketInstance();
        wSocket.sendData(UserLogged.removeFriend(Integer.toString(id), UserLogged.getUserLoggedInstance().getToken()).toString());

        synchronized (wSocket.notifier) {
            try {
                wSocket.notifier.wait(4000);
                if(wSocket.status.equals("200"))
                    dodano =true;

            } catch (Exception exep) {
            }
//            getAllFriends();
        }
    }
}