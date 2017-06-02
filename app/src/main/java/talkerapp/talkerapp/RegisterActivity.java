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
    private Toolbar toolbar;
    private Button registerButton;
    private EditText username;
    private EditText email;
    private EditText password;
    private EditText passwordConfirm;
    private Toast toast;
    private View layout;
    private TextView text;
    private LayoutInflater inflater;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.register_screen);
        toolbar = (Toolbar) findViewById(R.id.toolbarT);
        username = (EditText) findViewById(R.id.usernameInput);
        email = (EditText) findViewById(R.id.emailInput);
        password = (EditText) findViewById(R.id.passwordInput);
        passwordConfirm = (EditText) findViewById(R.id.passwordInput2);
        registerButton = (Button) findViewById(R.id.registerButton);
        inflater = getLayoutInflater();
        layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.customToastContainer));
        toast = new Toast(getApplicationContext());
        toast.setView(layout);
        toast.setDuration(Toast.LENGTH_SHORT);
        text = (TextView) layout.findViewById(R.id.toastText);
    
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
        
        registerButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (username.getText().toString().equals(""))
                    username.setError(getResources().getString(R.string.empty_data));
                else
                    username.setError(null);
    
                if (email.getText().toString().equals(""))
                    email.setError(getResources().getString(R.string.empty_data));
                else
                    email.setError(null);
                
                if (password.getText().toString().equals(passwordConfirm.getText().toString())
                        && !password.getText().toString().equals("")
                        && !passwordConfirm.getText().toString().equals(""))
                {
                    password.setError(null);
                    passwordConfirm.setError(null);
                }
                else
                {
                    if (password.getText().toString().equals(""))
                        password.setError(getResources().getString(R.string.empty_data));
                    else
                        password.setError(getResources().getString(R.string.register_password_error));
                    if (passwordConfirm.getText().toString().equals(""))
                        passwordConfirm.setError(getResources().getString(R.string.empty_data));
                    else
                        passwordConfirm.setError(getResources().getString(R.string.register_password_error));
                }
                if (username.getError() == null
                        && email.getError() == null
                        && password.getError() == null
                        && passwordConfirm.getError() == null)
                    SendRegisterData();
            }
        });
    }
    
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(this, AccoutActivity.class);
        startActivity(intent);
    }
    
    public void SendRegisterData()
    {
        UserAdd addedUser = new UserAdd(email.getText().toString(), password.getText().toString(), username.getText().toString());
        //region WebSocket
        
        try
        {
            WSocket wSocket = WSocket.getwSocketInstance();
            wSocket.sendData(addedUser.JSONStrigify().toString());
            Log.d("Wiadomosc", "Wyslano jstringa");

        } catch (Exception e) {
            Log.e("Except", "Wyjatek", e);
            wSocket.sendData(addedUser);
            text.setText(getString(R.string.register_success) + getString(R.string.logged));
            toast.show();
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        }
        catch (Exception e)
        {
            layout.setBackgroundColor(getResources().getColor(R.color.warningColor));
            text.setText(getString(R.string.register_error));
            toast.show();
            // e.toString();
            //  Log.d("Except", e.getMessage().toString());
        }
        //endregion
        //userPassword.setText((String[])addedUser);
    }

}
