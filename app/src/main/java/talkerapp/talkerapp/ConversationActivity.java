package talkerapp.talkerapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ConversationActivity extends Activity
{
    Button sendMsg;
    RelativeLayout background;
    EditText editText;
    TextView txtView;
    String tmp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversation_screen);
        Intent intent = getIntent();
        sendMsg = (Button) findViewById(R.id.sendMsg);
        background = (RelativeLayout)findViewById(R.id.conversationScreen);
        editText = (EditText) findViewById(R.id.editText);
        txtView = (TextView) findViewById(R.id.textView4);
    }

    public void sendMsg(View view)
    {
        tmp = editText.getText().toString();
        txtView.setText(tmp);
    }

    public void onBackPressed()
    {
        // Powraca do ekranu głównego
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}