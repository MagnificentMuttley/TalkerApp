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

import java.util.ArrayList;

public class FriendsListActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends_list_screen);
        Intent intent = getIntent();
//        ArrayList<TextView> contacts = new ArrayList<TextView>();
//        ArrayList<Person> friendsList = new ArrayList<Person>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewFriends);
        mLinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        
//        toolbar = (Toolbar)findViewById(R.id.toolbarT);
//        setSupportActionBar(toolbar);
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                onBackPressed();
//            }
//        });
//        toolbar.setTitle(R.string.my_friends);

//        for (int i = 0; i < 10; i++)
//        {
//            friendsList.add(new Person("Imie " + (i + 1), "Nazwisko "  + (i + 1), "Last Message " + (i + 1)));
//        }
//        for (Person item : friendsList)
//        {
//            TextView tmp = new TextView(this);
//            tmp.setTextColor(getResources().getColor(R.color.textColorPrimary));
//            tmp.setGravity(Gravity.CENTER_HORIZONTAL);
//            tmp.setTextSize(20);
//            tmp.setText(item.getName() + " " + item.getSurname());
//            contacts.add(tmp);
//
//            TextView tmp2 = new TextView(this);
//            tmp2.setTextColor(getResources().getColor(R.color.textColorPrimary));
//            tmp2.setGravity(Gravity.CENTER_HORIZONTAL);
//            tmp2.setTextSize(20);
//            tmp2.setText(item.getTxt());
//            contacts.add(tmp2);
//        }
//
//        for (int i = 0; i < contacts.size(); i++)
//        {
//            recyclerView.addView(contacts.get(i));
//        }
    }
}