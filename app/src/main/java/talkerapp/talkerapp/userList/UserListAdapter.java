package talkerapp.talkerapp.userList;

/**
 * Created by Adrian on 11.06.2017.
 */

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import talkerapp.talkerapp.MyButton;
import talkerapp.talkerapp.R;
import talkerapp.talkerapp.SearchFriendsActivity;
import tomek.UserRegistered;

public class UserListAdapter extends BaseAdapter
{
    private final List<UserRegistered> usersRegistered;
    private final List<MyButton> buttons;
    private Activity context;
    
    public UserListAdapter(Activity context, List<UserRegistered> userRegistered, List<MyButton> buttons) {
        this.context = context;
        this.usersRegistered = userRegistered;
        this.buttons = buttons;
    }
    
    @Override
    public int getCount() {
        if (usersRegistered != null) {
            return usersRegistered.size();
        } else {
            return 0;
        }
    }
    
    @Override
    public UserRegistered getItem(int position)
    {
        if (usersRegistered != null) {
            return usersRegistered.get(position);
        }
        else
            {
            return null;
        }
    }
    
    public MyButton getItemButton(int position)
    {
        if (buttons != null) {
            return buttons.get(position);
        }
        else
        {
            return null;
        }
    }
    
    @Override
    public long getItemId(int position)
    {
        return position;
    }
    
    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;
        UserRegistered user = getItem(position);
        final MyButton btnId = getItemButton(position);
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
        if (convertView == null)
        {
            convertView = vi.inflate(R.layout.list_item_registered_user, null);
            holder = createViewHolder(convertView);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        
        holder.username.setText(user.getUsername());
        holder.email.setText(user.getEmail());
        holder.btn.setText(R.string.invite_user);
        holder.btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (SearchFriendsActivity.sendInvitation(btnId.getId()))
                {
                    Toast toast = Toast.makeText(context, "Zaproszenie zostały wysłane", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        
        return convertView;
    }
    
    public void add(UserRegistered message, MyButton button)
    {
        usersRegistered.add(message);
        buttons.add(button);
    }
    
    public void add(List<UserRegistered> messages, List<MyButton> button)
    {
        usersRegistered.addAll(messages);
        buttons.addAll(button);
    }
    
    private void setAlignment(ViewHolder holder)
    {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) holder.contentWithBG.getLayoutParams();
        layoutParams.gravity = Gravity.RIGHT;
        holder.contentWithBG.setLayoutParams(layoutParams);
        
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) holder.content.getLayoutParams();
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        holder.content.setLayoutParams(lp);
        
        layoutParams = (LinearLayout.LayoutParams) holder.username.getLayoutParams();
        layoutParams.gravity = Gravity.RIGHT;
        holder.username.setLayoutParams(layoutParams);

        layoutParams = (LinearLayout.LayoutParams) holder.btn.getLayoutParams();
        layoutParams.gravity = Gravity.RIGHT;
        holder.btn.setLayoutParams(layoutParams);
        
        layoutParams = (LinearLayout.LayoutParams) holder.email.getLayoutParams();
        layoutParams.gravity = Gravity.RIGHT;
        holder.email.setLayoutParams(layoutParams);
    }
    
    private ViewHolder createViewHolder(View v)
    {
        ViewHolder holder = new ViewHolder();
        holder.username = (TextView) v.findViewById(R.id.registeredUsers_username);
        holder.btn = (Button) v.findViewById(R.id.button_add_user);
        holder.content = (LinearLayout) v.findViewById(R.id.content);
        holder.contentWithBG = (LinearLayout) v.findViewById(R.id.contentWithBackground);
        holder.email = (TextView) v.findViewById(R.id.registeredUsers_email);
        return holder;
    }
    
    private static class ViewHolder
    {
        public Button btn;
        public TextView username;
        public TextView email;
        public LinearLayout content;
        public LinearLayout contentWithBG;
    }
}