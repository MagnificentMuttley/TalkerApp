package talkerapp.talkerapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ConversationActivity extends AppCompatActivity
{
    Button sendMsg;
    RelativeLayout background;
    EditText editText;
    TextView txtView;
    String tmp;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversation_screen);
        Intent intent = getIntent();
        sendMsg = (Button) findViewById(R.id.sendMsg);
        txtView = (TextView) findViewById(R.id.textView19);
        toolbar = (Toolbar)findViewById(R.id.toolbarRegister);
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