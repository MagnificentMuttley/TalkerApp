package talkerapp.talkerapp;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import tomek.UserAdd;
import tomek.WSocket;

public class RegisterActivity extends AppCompatActivity
{
    Toolbar toolbar;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.register_screen);
        toolbar = (Toolbar)findViewById(R.id.toolbarT);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle(R.string.register_title);

        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });
        register = (Button)findViewById(R.id.registerButton);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(this, AccoutActivity.class);
        startActivity(intent);
    }
    public void tomek_rejestracja(View view) {
        TextView userName = (TextView) findViewById(R.id.usernameInput);
        TextView userEmail = (TextView) findViewById(R.id.emailInput);
        TextView userPassword = (TextView) findViewById(R.id.passwordInput);


        UserAdd addedUser = new UserAdd(userEmail.getText().toString(), userPassword.getText().toString(), userName.getText().toString());
        //region WebSocket

        try {
            WSocket wSocket = WSocket.getwSocketInstance();
            wSocket.sendData(addedUser.JSONStrigify().toString());
            Log.d("Wiadomosc", "Wyslano jstringa");

        } catch (Exception e) {
            Log.e("Except", "Wyjatek", e);
            // e.toString();
            //  Log.d("Except", e.getMessage().toString());
        }
        //endregion
        //userPassword.setText((String[])addedUser);
    }

}
