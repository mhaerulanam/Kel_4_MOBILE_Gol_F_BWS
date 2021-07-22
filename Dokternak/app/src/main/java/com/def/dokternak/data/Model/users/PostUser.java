package com.def.dokternak.data.Model.users;

import com.def.dokternak.data.Model.petugas.Petugas;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostUser {
    @SerializedName("status")
    String status;
    @SerializedName("data")
    User data;
    @SerializedName("message")
    String message;

    public PostUser(String status, User data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}