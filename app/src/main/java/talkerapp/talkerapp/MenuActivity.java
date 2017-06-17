package talkerapp.talkerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import talkerapp.talkerapp.chatRoomList.ChatRoomListAdapter;
import talkerapp.talkerapp.friendsList.FriendsListAdapter;
import tomek.UserLogged;
import tomek.UserRegistered;
import tomek.WSocket;


public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FloatingActionButton fab;
    private ArrayList<MyButton> buttons;
    private ListView friendsContainer;
    private ChatRoomListAdapter adapter;
    private ArrayList<String> chatList;
    private ArrayList<String> memberList;
    private ArrayList<String> memberUsernames;
    TextView txtViewUsername;
    TextView txtViewEmail;
    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent intent = getIntent();

        toolbar = (Toolbar) findViewById(R.id.toolbarT);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        toggle.syncState();
        txtViewUsername = (TextView) findViewById(R.id.textView_username);
        txtViewEmail = (TextView) findViewById(R.id.textView_email);
        txtViewUsername.setText(UserLogged.getUserLoggedInstance().getUserName());
        txtViewEmail.setText(UserLogged.getUserLoggedInstance().getEmail());
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        getMyChats();
    }

    private void initControls() {
        toolbar = (Toolbar) findViewById(R.id.toolbarT);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        chatList = new ArrayList<String>();
        memberList = new ArrayList<String>();
        memberUsernames = new ArrayList<String>();
        buttons = new ArrayList<MyButton>();

        adapter = new ChatRoomListAdapter(MenuActivity.this, new ArrayList<String>(), new ArrayList<MyButton>());

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
    
        friendsContainer = (ListView) findViewById(R.id.friendsContainer);
        RelativeLayout container = (RelativeLayout) findViewById(R.id.container);
    
        friendsContainer.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the status bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle status bar item clicks here. The status bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Intent intent = new Intent(this, ProfileViewActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_friends) {
            Intent intent = new Intent(this, FriendsListActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_invitation) {
            Intent intent = new Intent(this, InvitationActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_friends_search) {
            Intent intent = new Intent(this, SearchFriendsActivity.class);
            startActivity(intent);

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void newMessage(View view) {
        // Przechodzi do ekranu tworzenia nowej wiadomości
        // Akcja na przycisk koperty
        Intent intent = new Intent(this, ConversationActivity.class);
        startActivity(intent);
    }

    protected void getMyChats() {
        WSocket wSocket = WSocket.getwSocketInstance();
        wSocket.sendData(UserLogged.getMyChats().toString());
        synchronized (wSocket.notifier) {
            try {
                wSocket.notifier.wait();

                JSONArray payload = wSocket.jsonMsg.getJSONArray("payload");
                JSONArray members;
                String memberID;
                String chatID;
                String memberUsername;

                for (int i = 0; i < payload.length(); i++) {
                    JSONObject chats = payload.getJSONObject(i);
                    chatID = chats.getString("id");
                    chatList.add(chatID);
                    buttons.add(new MyButton(this, Integer.parseInt(chatID)));


                    members = chats.getJSONArray("GroupChatMembers");
                    for (int j = 0; j < chats.length(); j++) {
                        JSONObject member = members.getJSONObject(j);
                        if (!member.getString("id").equals(UserLogged.getUserLoggedInstance().getId())) {
                            memberID = member.getString("id");
                            memberList.add(memberID);
                            memberUsername = member.getString("username");
                            memberUsernames.add(memberUsername);
                        }

                    }
                }

            } catch (Exception exep) {
            }
        }

    }
}