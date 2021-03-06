package talkerapp.talkerapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import talkerapp.talkerapp.chat.ChatAdapter;
import talkerapp.talkerapp.chat.ChatMessage;

public class ConversationActivity extends AppCompatActivity
{
    private Toolbar toolbar;
    private EditText messageET;
    private ListView messagesContainer;
    private ImageButton sendBtn;
    private ChatAdapter adapter;
    private ArrayList<ChatMessage> chatHistory;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversation_screen);
        initControls();
    }
    
    private void initControls() {
        toolbar = (Toolbar)findViewById(R.id.toolbarT);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.username_placeholder);
        messagesContainer = (ListView) findViewById(R.id.messagesContainer);
        messageET = (EditText) findViewById(R.id.messageEdit);
        sendBtn = (ImageButton) findViewById(R.id.chatSendButton);
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
        RelativeLayout container = (RelativeLayout) findViewById(R.id.container);
        loadHistory();
        
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = messageET.getText().toString();
                if (TextUtils.isEmpty(messageText)) {
                    return;
                }
                
                ChatMessage chatMessage = new ChatMessage();
                chatMessage.setId(122);//dummy
                chatMessage.setMessage(messageText);
                chatMessage.setDate(DateFormat.getDateTimeInstance().format(new Date()));
                chatMessage.setMe(true);
                
                messageET.setText("");
                
                displayMessage(chatMessage);
            }
        });
    }
    
    public void displayMessage(ChatMessage message) {
        adapter.add(message);
        adapter.notifyDataSetChanged();
        scroll();
    }
    
    private void scroll()
    {
        messagesContainer.setSelection(messagesContainer.getCount() - 1);
    }
    
    private void loadHistory()
    {
        
        chatHistory = new ArrayList<ChatMessage>();
        
        ChatMessage msg = new ChatMessage();

        adapter = new ChatAdapter(ConversationActivity.this, new ArrayList<ChatMessage>());
        messagesContainer.setAdapter(adapter);
    }
}