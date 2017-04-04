package talkerapp.talkerapp;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class RegisterActivity extends AppCompatActivity
{
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.register_screen);
        toolbar = (Toolbar)findViewById(R.id.toolbarRegister);
        setSupportActionBar(toolbar);

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
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(this, AccoutActivity.class);
        startActivity(intent);
    }
}
