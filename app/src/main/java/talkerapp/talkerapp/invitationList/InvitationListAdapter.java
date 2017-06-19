package talkerapp.talkerapp.invitationList;

/**
 * Created by Adrian on 12.06.2017.
 */

import android.app.Activity;
import android.content.Context;
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

import talkerapp.talkerapp.InvitationActivity;
import talkerapp.talkerapp.MyButton;
import talkerapp.talkerapp.R;
import javaClasses.UserRegistered;

public class InvitationListAdapter extends BaseAdapter
{
    private final List<UserRegistered> invitation;
    private final List<MyButton> buttons;
    private Activity context;
    
    public InvitationListAdapter(Activity context, List<UserRegistered> invitation, List<MyButton> buttons)
    {
        this.context = context;
        this.invitation = invitation;
        this.buttons = buttons;
    }
    
    @Override
    public int getCount() {
        if (invitation != null) {
            return invitation.size();
        } else {
            return 0;
        }
    }
    
    @Override
    public UserRegistered getItem(int position)
    {
        if (invitation != null) {
            return invitation.get(position);
        }
        else
        {
            return null;
        }
    }
    
    public MyButton getItemButton(int position)
    {
        if (buttons != null)
        {
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
            convertView = vi.inflate(R.layout.list_item_invitation, null);
            holder = createViewHolder(convertView);
            convertView.setTag(holder);
        }
        else
            {
            holder = (ViewHolder) convertView.getTag();
        }
        
        holder.username.setText(user.getUsername());
        holder.email.setText(user.getEmail());
        holder.btn.setText(R.string.accept_invitation);
        holder.btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (InvitationActivity.AcceptInvitation(btnId.getId()))
                {
                    Toast toast = Toast.makeText(context, "Zakceptowano zaproszenie", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        holder.btn2.setText(R.string.reject_invitation);
        holder.btn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (InvitationActivity.RejectInvitation(btnId.getId()))
                {
                    Toast toast = Toast.makeText(context, "Odrzucono zaproszenie", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        
        return convertView;
    }
    
    public void add(UserRegistered message, MyButton button)
    {
        invitation.add(message);
        buttons.add(button);
    }
    
    public void add(List<UserRegistered> messages, List<MyButton> button)
    {
        invitation.addAll(messages);
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
    
        layoutParams = (LinearLayout.LayoutParams) holder.btn2.getLayoutParams();
        layoutParams.gravity = Gravity.RIGHT;
        holder.btn2.setLayoutParams(layoutParams);
            
        layoutParams = (LinearLayout.LayoutParams) holder.email.getLayoutParams();
        layoutParams.gravity = Gravity.RIGHT;
        holder.email.setLayoutParams(layoutParams);
    }
    
    private ViewHolder createViewHolder(View v)
    {
        ViewHolder holder = new ViewHolder();
        holder.email = (TextView) v.findViewById(R.id.invitation_email);
        holder.btn = (Button) v.findViewById(R.id.button_accept_friend);
        holder.btn2 = (Button) v.findViewById(R.id.button_reject_friend);
        holder.content = (LinearLayout) v.findViewById(R.id.content);
        holder.contentWithBG = (LinearLayout) v.findViewById(R.id.contentWithBackground);
        holder.username = (TextView) v.findViewById(R.id.invitation_username);
        return holder;
    }
    
    private static class ViewHolder
    {
        public Button btn;
        public Button btn2;
        public TextView username;
        public TextView email;
        public LinearLayout content;
        public LinearLayout contentWithBG;
    }
}