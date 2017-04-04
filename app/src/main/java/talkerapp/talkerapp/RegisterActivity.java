package talkerapp.talkerapp;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class RegisterActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.register_screen);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }
}
