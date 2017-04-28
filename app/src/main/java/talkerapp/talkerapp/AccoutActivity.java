package talkerapp.talkerapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AccoutActivity extends Activity
{
    EditText login;
    EditText password;
    TextView incorrectLogin;
    Button logButton;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.accout_login);
        incorrectLogin = (TextView)findViewById(R.id.incorrectLogin);
        login = (EditText)findViewById(R.id.inputLogin);
        password = (EditText)findViewById(R.id.inputPassword);
        incorrectLogin.setVisibility(View.INVISIBLE);
        logButton = (Button)findViewById(R.id.loginButton);
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
    public void register(View view)
    {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();
    }
}
