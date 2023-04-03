package com.midterm.restaurant_app.model;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class Conversation {
    private String idConvs;
    private String urlAvatar;
    private String idAcc;
    private String idMess;

    public Conversation(String idConvs, String urlAvatar, String idAcc, String idMess) {
        this.idConvs = idConvs;
        this.urlAvatar = urlAvatar;
        this.idAcc = idAcc;
        this.idMess = idMess;
    }

    public Conversation() {
    }

    public String getIdConvs() {
        return idConvs;
    }

    public void setIdConvs(String idConvs) {
        this.idConvs = idConvs;
    }

    public String getUrlAvatar() {
        return urlAvatar;
    }

    public void setUrlAvatar(String urlAvatar) {
        this.urlAvatar = urlAvatar;
    }

    public String getIdAcc() {
        return idAcc;
    }

    public void setIdAcc(String idAcc) {
        this.idAcc = idAcc;
    }

    public String getIdMess() {
        return idMess;
    }

    public void setIdMess(String idMess) {
        this.idMess = idMess;
    }
}
