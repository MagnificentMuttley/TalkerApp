package talkerapp.talkerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import talkerapp.talkerapp.userList.UserListAdapter;
import javaClasses.UserLogged;
import javaClasses.UserRegistered;
import javaClasses.WSocket;

public class SearchFriendsActivity extends AppCompatActivity
{
    private ArrayList<MyButton> buttons;
    private ListView usersContainer;
    private UserListAdapter adapter;
    private ArrayList<UserRegistered> registeredUsers;
    private Toolbar toolbar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_friends_screen);
        Intent intent = getIntent();
        
        initControls();
        
        getRegisteredUsers();
        for(int i = 0; i< registeredUsers.size(); i++)
        {
            UserRegistered user = registeredUsers.get(i);
            MyButton button = buttons.get(i);
            displayItem(user, button);
            
        }
    }
    
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        registeredUsers.clear();
        buttons.clear();
    }
    
    private void initControls() {
        toolbar = (Toolbar)findViewById(R.id.toolbarT);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.friends_search);
        
        buttons = new ArrayList<MyButton>();
        registeredUsers = new ArrayList<UserRegistered>();
        adapter = new UserListAdapter(SearchFriendsActivity.this, new ArrayList<UserRegistered>(), new ArrayList<MyButton>());
        
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
        
        usersContainer = (ListView) findViewById(R.id.usersContainer);
        RelativeLayout container = (RelativeLayout) findViewById(R.id.container);
        
        usersContainer.setAdapter(adapter);
    }
    
    public void displayItem(UserRegistered user, MyButton button) {
        adapter.add(user, button);
        adapter.notifyDataSetChanged();
    }
    
    protected void getRegisteredUsers()
    {
        WSocket wSocket = WSocket.getwSocketInstance();
        wSocket.sendData(UserRegistered.getAllUsers(UserLogged.getUserLoggedInstance().getToken()).toString());
        
        synchronized (wSocket.notifier) {
            try {
                wSocket.notifier.wait();
                
                JSONArray payload = wSocket.jsonMsg.getJSONArray("payload");
                for (int i =0; i<payload.length();i++) {
                    JSONObject userRegistered = payload.getJSONObject(i);
                    UserRegistered registered = new UserRegistered(userRegistered.getString("username"),
                            userRegistered.getString("email"),
                            userRegistered.getString("id"));
                    registeredUsers.add(registered);
                    buttons.add(new MyButton(this, Integer.parseInt(registered.getId())));
                }
                
            } catch (Exception exep) {
            }
        }
    }
    
    public static boolean sendInvitation(int id)
    {
        boolean send = false;
        WSocket wSocket = WSocket.getwSocketInstance();
        wSocket.sendData(UserLogged.addFriend(Integer.toString(id)).toString());
        
        synchronized (wSocket.notifier)
        {
            try {
                wSocket.notifier.wait(4000);
                if(wSocket.status.equals("200"))
                    send = true;
                
            } catch (Exception exep) {
            }
        }
        return send;
    }
}