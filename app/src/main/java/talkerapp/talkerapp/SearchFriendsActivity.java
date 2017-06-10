package talkerapp.talkerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import tomek.UserLogged;
import tomek.UserRegistered;
import tomek.WSocket;

public class SearchFriendsActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_screen);
        Intent intent = getIntent();
        toolbar = (Toolbar)findViewById(R.id.toolbarT);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.friends_search);
        recyclerView = (RecyclerView)findViewById(R.id.search_friends_recyclerview);
        
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
    }

    protected void TUTAJFUNKCJA()
    {
        WSocket wSocket = WSocket.getwSocketInstance();
        wSocket.sendData(UserRegistered.getAllUsers(UserLogged.getUserLoggedInstance().getToken()).toString());

        List<UserRegistered> registeredUsers = new ArrayList<UserRegistered>();

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
                }

            } catch (Exception exep) {
            }
        }
    }
}