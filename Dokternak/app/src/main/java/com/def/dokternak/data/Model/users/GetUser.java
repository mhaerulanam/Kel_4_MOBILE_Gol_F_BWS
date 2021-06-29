package com.def.dokternak.data.Model.users;

import com.def.dokternak.data.Model.petugas.Petugas;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetUser {
    @SerializedName("status")
    String status;
    @SerializedName("data")
    List<User> listDataUser;
    @SerializedName("message")
    String message;

    public GetUser(String status, List<User> listDataUser, String message) {
        this.status = status;
        this.listDataUser = listDataUser;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<User> getListDataUser() {
        return listDataUser;
    }

    public void setListDataUser(List<User> listDataUser) {
        this.listDataUser = listDataUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
