package talkerapp.talkerapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeScreenActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        Thread myThread = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    sleep(2000);
                    Intent intent = new Intent(getApplicationContext(), AccoutActivity.class);
                    startActivity(intent);
                    finish();
                }
                catch (InterruptedException e)
                {

                }
            }
        };
        myThread.start();
    }
}