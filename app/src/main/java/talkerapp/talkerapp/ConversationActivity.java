package talkerapp.talkerapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ConversationActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private Button sendMsgButton;
    private Toolbar toolbar;
    private EditText msg;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversation_screen);
        Intent intent = getIntent();
        toolbar = (Toolbar)findViewById(R.id.toolbarT);
        setSupportActionBar(toolbar);
        msg = (EditText)findViewById(R.id.edit_text_send_msg);
        msg.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {
        
            }
    
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                sendMsgButton.setEnabled(true);
                sendMsgButton.setText("Działa");
            }
    
            @Override
            public void afterTextChanged(Editable s)
            {
                if (msg.getText().toString().equals(""))
                {
                    sendMsgButton.setEnabled(false);
                }
            }
        });
        sendMsgButton = (Button)findViewById(R.id.button_send_msg);
        sendMsgButton.setEnabled(false);
        
        recyclerView = (RecyclerView)findViewById(R.id.conversation_recyclerview);
        
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
        sendMsgButton.setText("Działa");
    }

    @Override
    public void onBackPressed()
    {
        // Powraca do ekranu głównego
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}