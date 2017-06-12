package talkerapp.talkerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import tomek.UserLogged;
import tomek.UserRegistered;
import tomek.WSocket;

public class FriendsListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_screen);
        Intent intent = getIntent();
        toolbar = (Toolbar) findViewById(R.id.toolbarT);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.my_friends);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    ArrayList<UserRegistered> friends = new ArrayList<UserRegistered>();

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

                    // friends to lista z kurwa tymi ludźi, dosć łątwo to zauważyc
                    // buttons.add(new MyButton(this, Integer.parseInt(registered.getId())));
                }
                UserLogged.setFriends(friends);

            } catch (Exception exep) {
            }
        }

    }

    protected void removeFromFriends()
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
            getAllFriends();
        }
    }
}