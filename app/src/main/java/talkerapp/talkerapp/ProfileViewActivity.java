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
        apply.setEnabled(false);
        username = (EditText) findViewById(R.id.change_profile_usernameInput);
        username.addTextChangedListener(new TextWatcher() {
            String tmp;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                tmp = username.getText().toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!tmp.equals(username.getText().toString())) {
                    apply.setEnabled(true);
                }
            }
        });
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
        // to mają być te pola tekstowe
        String username, password, email;

        WSocket wSocket = WSocket.getwSocketInstance();
        wSocket.sendData(UserLogged.updateInfo(UserLogged.getUserLoggedInstance().getId(),
                UserLogged.getUserLoggedInstance().getToken(),
                username, password, email).toString());

        synchronized (wSocket.notifier) {
            try {
                wSocket.notifier.wait();
                if (wSocket.status.equals("200")) {
                    //tost że zmieniono dane
                    JSONObject payload = wSocket.jsonMsg.getJSONObject("payload");
                    UserLogged userLogged = UserLogged.setUserLoggedInstance(payload.getString("email"), payload.getString("username"), token, payload.getString("id"));
                }

            } catch (Exception ex) {
            }
        }
    }
}