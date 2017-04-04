package talkerapp.talkerapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class HaroldActivity extends AppCompatActivity
{



    ///////////////////////////////////////////////////////////////////////////////////SZTYWNA PREZENTACJA

    ImageView haroldImg;
    TextView haroldText2;
    TextView haroldText1;
    ImageView tomekImg1;
    ImageView tomekImg2;
    TextView tomekText1;
    TextView tomekText2;
    TextView tomekText3;

    TextView tomekText4;
    ImageView tomekImg3;

    EditText wpisywanieTekstu;
    ///////////////////////////////////////////////////////////////////////////////////SZTYWNA PREZENTACJA


    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.harold_message);
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

        ///////////////////////////////////////////////////////////////////////////////////SZTYWNA PREZENTACJA
        haroldImg=(ImageView) findViewById(R.id.imgHarold);
        haroldText2=(TextView) findViewById(R.id.textView3);
        haroldText1=(TextView) findViewById(R.id.textView4);

        tomekImg1=(ImageView) findViewById(R.id.imageView4);
        tomekImg2=(ImageView) findViewById(R.id.imageView2);
        tomekText1=(TextView) findViewById(R.id.textView11);
        tomekText2=(TextView) findViewById(R.id.textView7);
        tomekText3=(TextView) findViewById(R.id.textView10);

        tomekText4=(TextView) findViewById(R.id.textView9);

        wpisywanieTekstu=(EditText) findViewById(R.id.editText2);

///////////////////////////////////////////////////////////////////////////////////SZTYWNA PREZENTACJA
}

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void HaroldsendMsg2(View view) {

        try{
            /*tomekText4 = tomekText3;
            tomekText4.setText(wpisywanieTekstu.getText());
            tomekText4.setX(tomekText3.getX());
            tomekText4.setY(tomekText3.getY());*/


            haroldImg.setY(haroldImg.getY()-180);
            haroldText2.setY(haroldText2.getY()-180);
            haroldText1.setY(haroldText1.getY()-180);
            tomekImg1.setY(tomekImg1.getY()-180);
            tomekImg2.setY(tomekImg2.getY()-180);
            tomekText1.setY(tomekText1.getY()-180);
            tomekText2.setY(tomekText2.getY()-180);
            tomekText3.setY(tomekText3.getY()-180);
            // tmp = editText.getText().toString();
            //txtView.setText(tmp);
            tomekText4.setText(wpisywanieTekstu.getText());
            wpisywanieTekstu.setText(null);



        }
        catch(Exception e){}
    }
}
