package talkerapp.talkerapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import tomek.UserLogged;
import tomek.UserToLogin;
import tomek.WSocket;

import static tomek.UserLogged.LoggedUserInfo;

public class AccoutActivity extends Activity {
    private EditText login;
    private EditText password;
    private TextView incorrectLogin;
    private Button logButton;
    private String fileName;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.accout_login);
        
        fileName = "loginData";
        incorrectLogin = (TextView) findViewById(R.id.incorrectLogin);
        login = (EditText) findViewById(R.id.login_email_input);
        password = (EditText) findViewById(R.id.login_password_input);
        incorrectLogin.setVisibility(View.INVISIBLE);
        logButton = (Button) findViewById(R.id.loginButton);
        try
        {
            String msg;
            FileInputStream fileInputStream = openFileInput(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String stringBuffer = new String();
            String stringBuffer2 = new String();
            while ((msg = bufferedReader.readLine()) != null) {
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
    
    int press = 0;
    
    public void logIn(View view) throws FileNotFoundException
    {
        UserToLogin userToLogin = new UserToLogin(login.getText().toString(), password.getText().toString());
        try
        {
            WSocket wSocket = WSocket.getwSocketInstance();
            wSocket.sendData(userToLogin.JSONStrigify().toString());
            press++;
            if (press > 1)
                
                synchronized (wSocket.notifier)
                {
                    try
                    {
                        wSocket.notifier.wait(4000);
                        Log.d("wSocket status", wSocket.status);
                        if (wSocket.status.equals("200")) {
                            String token = wSocket.payload;
                            wSocket.sendData(LoggedUserInfo(token).toString());
                            
                            synchronized (wSocket.notifier)
                            {
                                try
                                {
                                    wSocket.notifier.wait(4000);
                                    
                                    JSONObject payload = wSocket.jsonMsg.getJSONObject("payload");
                                    
                                    UserLogged userLogged = UserLogged.setUserLoggedInstance(payload.getString("email"), payload.getString("username"), token, payload.getString("id"));
                                    
                                    Intent intent = new Intent(this, MenuActivity.class);
                                    startActivity(intent);
                                }
                                catch (InterruptedException inexe) {}
                            }
                        }
                        else if (wSocket.status.equals("409")) {}
                    }
                    catch (InterruptedException inex) {}
                }
        }
        catch (Exception ex)
        {
        }
//
    }
    
    public void register(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
    
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}