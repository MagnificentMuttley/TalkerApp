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

import talkerapp.talkerapp.invitationList.InvitationListAdapter;
import talkerapp.talkerapp.userList.UserListAdapter;
import tomek.UserLogged;
import tomek.UserRegistered;
import tomek.WSocket;

public class InvitationActivity extends AppCompatActivity {
    private ArrayList<MyButton> buttons;
    private ArrayList<UserRegistered> friendsRequests;
    private ListView invitationContainer;
    private InvitationListAdapter adapter;
    Toolbar toolbar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invitation_screen);
        Intent intent = getIntent();
        
        initControls();
    
        getFriendsRequests();
        for(int i = 0; i< friendsRequests.size(); i++)
        {
            UserRegistered user = friendsRequests.get(i);
            MyButton button = buttons.get(i);
            displayItem(user, button);
        }
    }
    
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        friendsRequests.clear();
        buttons.clear();
    }
    
    private void initControls() {
        toolbar = (Toolbar)findViewById(R.id.toolbarT);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.invitation);
        
        buttons = new ArrayList<MyButton>();
        friendsRequests = new ArrayList<UserRegistered>();
        adapter = new InvitationListAdapter(InvitationActivity.this, new ArrayList<UserRegistered>(), new ArrayList<MyButton>());
        
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
    
        invitationContainer = (ListView) findViewById(R.id.invitationContainer);
        RelativeLayout container = (RelativeLayout) findViewById(R.id.container);
    
        invitationContainer.setAdapter(adapter);
    }
    
    public void displayItem(UserRegistered user, MyButton button) {
        adapter.add(user, button);
        adapter.notifyDataSetChanged();
    }
    
    protected void getFriendsRequests() {
        WSocket wSocket = WSocket.getwSocketInstance();
        wSocket.sendData(UserLogged.getFriendsRequests(UserLogged.getUserLoggedInstance().getToken()).toString());

        synchronized (wSocket.notifier) {
            try {
                wSocket.notifier.wait(5000);

                JSONArray payload = wSocket.jsonMsg.getJSONArray("payload");
                for (int i = 0; i < payload.length(); i++) {
                    JSONObject userRegistered = payload.getJSONObject(i);
                    UserRegistered registered = new UserRegistered(userRegistered.getString("username"),
                            userRegistered.getString("email"),
                            userRegistered.getString("id"));
                    friendsRequests.add(registered);
                    buttons.add(new MyButton(this, Integer.parseInt(registered.getId())));
                }


            } catch (Exception exep) {
            }
        }
    }

    public static boolean AcceptInvitation(int id)
    {
        boolean added = false;
    
        WSocket wSocket = WSocket.getwSocketInstance();
        
        wSocket.sendData(UserLogged.acceptFriend(Integer.toString(id), UserLogged.getUserLoggedInstance().getToken()).toString());

        synchronized (wSocket.notifier) {
            try {
                wSocket.notifier.wait(4000);
                if (wSocket.status.equals("200"))
                    added = true;

            } catch (Exception exep) {
            }
        }
        return added;
    }
    
    public static boolean RejectInvitation(int id)
    {
        boolean rejected = false;
        
        WSocket wSocket = WSocket.getwSocketInstance();
        
        wSocket.sendData(UserLogged.rejectFriend(Integer.toString(id), UserLogged.getUserLoggedInstance().getToken()).toString());

        synchronized (wSocket.notifier) {
            try {
                wSocket.notifier.wait(4000);
                if (wSocket.status.equals("200"))
                    rejected = true;

            } catch (Exception exep) {
            }
        }
        return rejected;
    }
}