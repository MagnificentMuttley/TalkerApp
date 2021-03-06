package talkerapp.talkerapp.chatRoomList;

/**
 * Created by Adrian on 13.06.2017.
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

import java.util.List;

import talkerapp.talkerapp.MenuActivity;
import talkerapp.talkerapp.MyButton;
import talkerapp.talkerapp.R;

public class ChatRoomListAdapter extends BaseAdapter
{
    private final List<String> chatRooms;
    private final List<MyButton> buttons;
    private Activity context;

    public ChatRoomListAdapter(Activity context, List<String> chatRooms, List<MyButton> buttons)
    {
        this.context = context;
        this.chatRooms = chatRooms;
        this.buttons = buttons;
    }

    @Override
    public int getCount()
    {
        if (chatRooms != null)
        {
            return chatRooms.size();
        }
        else
        {
            return 0;
        }
    }

    @Override
    public String getItem(int position)
    {
        if (chatRooms != null)
        {
            return chatRooms.get(position);
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        String user = getItem(position);
        final MyButton btnId = getItemButton(position);
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
        {
            convertView = vi.inflate(R.layout.list_item_chat_room, null);
            holder = createViewHolder(convertView);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.username.setText(user);
        holder.email.setText(" ");
        holder.btn.setText(R.string.chat);
        holder.btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
            }
        });
        return convertView;
    }

    public void add(String message, MyButton button)
    {
        chatRooms.add(message);
        buttons.add(button);
    }

    public void add(List<String> messages, List<MyButton> button)
    {
        chatRooms.addAll(messages);
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

    private ViewHolder createViewHolder(View v) {
        ViewHolder holder = new ViewHolder();
        holder.username = (TextView) v.findViewById(R.id.chatList_username);
        holder.btn = (Button) v.findViewById(R.id.button_chat);
        holder.content = (LinearLayout) v.findViewById(R.id.content);
        holder.contentWithBG = (LinearLayout) v.findViewById(R.id.contentWithBackground);
        holder.email = (TextView) v.findViewById(R.id.chatList_email);
        return holder;
    }

    private static class ViewHolder {
        public Button btn;
        public TextView username;
        public TextView email;
        public LinearLayout content;
        public LinearLayout contentWithBG;
    }
}