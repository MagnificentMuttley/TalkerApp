package talkerapp.talkerapp;

import android.content.Context;

/**
 * Created by Adrian on 11.06.2017.
 */

public class MyButton extends android.support.v7.widget.AppCompatButton
{
    private int id;
    public MyButton(Context context, int id)
    {
        super(context);
        this.id = id;
    }
    
    @Override
    public int getId()
    {
        return id;
    }

    
    @Override
    public void setId(int id)
    {
        this.id = id;
    }
}
