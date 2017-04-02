package talkerapp.talkerapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AccoutActivity extends Activity
{
    EditText login;
    EditText password;
    TextView incorrectLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.accout_login);
        login = (EditText)findViewById(R.id.inputLogin);
        password = (EditText)findViewById(R.id.inputPassword);
        incorrectLogin = (TextView)findViewById(R.id.incorrectLogin);
        incorrectLogin.setVisibility(View.INVISIBLE);
    }

    public void logIn(View view)
    {
        incorrectLogin.setVisibility(View.INVISIBLE);
        if (login.getText().toString().equals("test") && password.getText().toString().equals("test"))
        {
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        }
        else
        {
            incorrectLogin.setVisibility(View.VISIBLE);
        }

    }
}
