package talkerapp.talkerapp.userList;

import android.content.Context;
import android.widget.Button;

/**
 * Created by Adrian on 11.06.2017.
 */

public class AddButton extends android.support.v7.widget.AppCompatButton
{
    private int id;
    public AddButton(Context context, int id)
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
