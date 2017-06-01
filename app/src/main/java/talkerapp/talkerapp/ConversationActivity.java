package talkerapp.talkerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class ConversationActivity extends AppCompatActivity
{
    private Button sendMsg;
    private Toolbar toolbar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversation_screen);
        Intent intent = getIntent();
        toolbar = (Toolbar)findViewById(R.id.toolbarT);
        setSupportActionBar(toolbar);

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
        toolbar.setTitle(R.string.contacts_title);
    }

    public void sendMsg(View view)
    {

    }

    @Override
    public void onBackPressed()
    {
        // Powraca do ekranu głównego
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}