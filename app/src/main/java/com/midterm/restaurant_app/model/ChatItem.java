package com.midterm.restaurant_app.model;

import android.graphics.drawable.Drawable;

public class ChatItem {
    private Drawable avatar;
    private String userName;
    private String latestMessage;

    public ChatItem(Drawable avatar, String userName, String latestMessage) {
        this.avatar = avatar;
        this.userName = userName;
        this.latestMessage = latestMessage;
    }

    public Drawable getAvatar() {
        return avatar;
    }

    public void setAvatar(Drawable avatar) {
        this.avatar = avatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLatestMessage() {
        return latestMessage;
    }

    public void setLatestMessage(String latestMessage) {
        this.latestMessage = latestMessage;
    }
}
