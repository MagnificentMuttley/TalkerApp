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

import talkerapp.talkerapp.userList.AddButton;
import talkerapp.talkerapp.userList.UserListAdapter;
import tomek.UserLogged;
import tomek.UserRegistered;
import tomek.WSocket;

public class SearchFriendsActivity extends AppCompatActivity
{
    private ArrayList<AddButton> buttons;
    private ListView usersContainer;
    private UserListAdapter adapter;
    private ArrayList<UserRegistered> list;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_friends_screen);
        Intent intent = getIntent();
        
        initControls();

        getRegisteredUsers();
        for(int i=0; i<list.size(); i++)
        {
            UserRegistered user = list.get(i);
            AddButton button = buttons.get(i);
            displayMessage(user, button);
        }
    }
    
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        list.clear();
        buttons.clear();
    }
    
    private void initControls() {
        toolbar = (Toolbar)findViewById(R.id.toolbarT);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.friends_search);
        
        buttons = new ArrayList<AddButton>();
        list = new ArrayList<UserRegistered>();
        adapter = new UserListAdapter(SearchFriendsActivity.this, new ArrayList<UserRegistered>(), new ArrayList<AddButton>());
        
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
    
    public void displayMessage(UserRegistered user, AddButton button) {
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
                    list.add(registered);
                    buttons.add(new AddButton(this, Integer.parseInt(registered.getId())));
                }
                
            } catch (Exception exep) {
            }
        }
    }
}