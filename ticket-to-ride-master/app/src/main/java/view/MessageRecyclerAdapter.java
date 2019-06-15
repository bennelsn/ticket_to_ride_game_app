package view;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a340team.tickettoride.R;

import java.util.List;

import model.ClientRoot;
import shared.model.interfaces.Message;

/**
 * Created by bdemann on 3/8/18.
 */

public class MessageRecyclerAdapter extends RecyclerView.Adapter<MessageRecyclerAdapter.ViewHolder> {

    List<? extends Message> messages;

    public MessageRecyclerAdapter(List<? extends Message> messages){
        this.messages = messages;
    }

    public void updateMessage(List<? extends Message> messages){
        this.messages = messages;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.chat_item, parent, false);
        MessageRecyclerAdapter.ViewHolder viewHolder = new MessageRecyclerAdapter.ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TextView message = holder.message;
        TextView username = holder.username;
        TextView date = holder.date;
        LinearLayout container = holder.container;

        Message m = messages.get(position);

        message.setText(m.getMessage());
        username.setText(m.getUsername());
        date.setText(m.getFormattedTime());

        if(m.getUsername().equals(ClientRoot.getClientPlayer().getUsername())){
            container.setBackground(ContextCompat.getDrawable(holder.container.getContext(), R.drawable.chatrectangletwo));
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView message;
        public TextView username;
        public TextView date;
        public LinearLayout container;

        public ViewHolder(final View itemView) {
            super(itemView);
            message = (TextView) itemView.findViewById(R.id.message);
            username = (TextView) itemView.findViewById(R.id.metadata_username);
            date = (TextView) itemView.findViewById(R.id.metadata_time);
            container = (LinearLayout) itemView.findViewById(R.id.message_container);

        }

        @Override
        public void onClick(View v) {
            //This recycler view just displays information. YAY! Easy!
        }
    }
}
