package com.def.dokternak.data.Model.puskeswan;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetCariPuskeswan {
    @SerializedName("status")
    String status;
    @SerializedName("data")
    List<Puskeswan> listDataPuskeswan;
    @SerializedName("message")
    String message;

    public GetCariPuskeswan(String status, List<Puskeswan> listDataPuskeswan, String message) {
        this.status = status;
        this.listDataPuskeswan = listDataPuskeswan;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Puskeswan> getListDataPuskeswan() {
        return listDataPuskeswan;
    }

    public void setListDataPuskeswan(List<Puskeswan> listDataPuskeswan) {
        this.listDataPuskeswan = listDataPuskeswan;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


