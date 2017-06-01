package talkerapp.talkerapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class AccoutActivity extends Activity
{
    private EditText login;
    private EditText password;
    private TextView incorrectLogin;
    private Button logButton;
    private String fileName;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.accout_login);
        
        fileName = "loginData";
        incorrectLogin = (TextView)findViewById(R.id.incorrectLogin);
        login = (EditText)findViewById(R.id.login_email_input);
        password = (EditText)findViewById(R.id.login_password_input);
        incorrectLogin.setVisibility(View.INVISIBLE);
        logButton = (Button)findViewById(R.id.loginButton);
        try
        {
            String msg;
            FileInputStream fileInputStream = openFileInput(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String stringBuffer = new String();
            String stringBuffer2 = new String();
            while((msg = bufferedReader.readLine()) != null)
            {
                stringBuffer = msg;
                stringBuffer2 = msg;
            }
            login.setText(stringBuffer);
            password.setText(stringBuffer2);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void logIn(View view) throws FileNotFoundException
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