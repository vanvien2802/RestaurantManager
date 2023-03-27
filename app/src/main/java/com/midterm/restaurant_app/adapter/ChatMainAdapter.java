package com.midterm.restaurant_app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.midterm.restaurant_app.R;
import com.midterm.restaurant_app.model.ChatItem;

import java.util.List;

public class ChatMainAdapter extends RecyclerView.Adapter<ChatMainAdapter.ChatViewHolder> {
    private List<ChatItem> chatList;

    public ChatMainAdapter(List<ChatItem> chatList) {
        this.chatList = chatList;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemchat, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        ChatItem chatItem = chatList.get(position);
        holder.userAvatar.setImageDrawable(chatItem.getAvatar());
        holder.userName.setText(chatItem.getUserName());
        holder.latestMessage.setText(chatItem.getLatestMessage());
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    public static class ChatViewHolder extends RecyclerView.ViewHolder {
        public ImageView userAvatar;
        public TextView userName;
        public TextView latestMessage;

        public ChatViewHolder(View itemView) {
            super(itemView);
            userAvatar = itemView.findViewById(R.id.user_avatar);
            userName = itemView.findViewById(R.id.user_name);
            latestMessage = itemView.findViewById(R.id.latest_message);
        }
    }
}
