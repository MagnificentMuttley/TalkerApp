package talkerapp.talkerapp;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;
import org.w3c.dom.Text;

import tomek.UserLogged;
import tomek.WSocket;

public class ProfileViewActivity extends AppCompatActivity {
    private static int RESULT_LOAD_IMAGE = 1;
    private Toolbar toolbar;
    private Button apply;
    private ImageView avatar;
    private TextView txtViewUsername;
    private EditText username;
    private EditText email;
    private EditText password;
    private EditText passwordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_profile_view);
        Intent intent = getIntent();
        toolbar = (Toolbar) findViewById(R.id.toolbarT);
        avatar = (ImageView) findViewById(R.id.change_profile_avatar);
        txtViewUsername = (TextView) findViewById(R.id.my_profile_username);
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
        apply = (Button) findViewById(R.id.change_profile_button);
        txtViewUsername.setText(UserLogged.getUserLoggedInstance().getUserName());
        apply.setOnClickListener(new View.OnClickListener()
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
                        && !passwordConfirm.getText().toString().equals("")) {
                    password.setError(null);
                    passwordConfirm.setError(null);
                } else {
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
                    updateUserInfo();
            }
        });
        username = (EditText) findViewById(R.id.change_profile_usernameInput);
        email = (EditText) findViewById(R.id.change_profile_emailInput);
        password = (EditText) findViewById(R.id.change_profile_passwordInput);
        passwordConfirm = (EditText) findViewById(R.id.change_profile_password_ConfirmInput);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getResources().getString(R.string.my_profile));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            avatar.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }

    protected void updateUserInfo() {
        String usernameTmp = username.getText().toString(), passwordTmp = password.getText().toString(), emailTmp = email.getText().toString();

        WSocket wSocket = WSocket.getwSocketInstance();
        wSocket.sendData(UserLogged.updateInfo(UserLogged.getUserLoggedInstance().getId(),
                UserLogged.getUserLoggedInstance().getToken(),
                usernameTmp, passwordTmp, emailTmp).toString());

        synchronized (wSocket.notifier) {
            try {
                wSocket.notifier.wait();
                if (wSocket.status.equals("200")) {
                    //tost że zmieniono dane
                    JSONObject payload = wSocket.jsonMsg.getJSONObject("payload");
                    UserLogged userLogged = UserLogged.setUserLoggedInstance(payload.getString("email"), payload.getString("username"), UserLogged.getUserLoggedInstance().getToken(), payload.getString("id"));
                }

            } catch (Exception ex) {
            }
        }
    }
}