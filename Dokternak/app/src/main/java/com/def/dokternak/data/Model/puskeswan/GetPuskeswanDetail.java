package com.def.dokternak.data.Model.puskeswan;


import com.google.gson.annotations.SerializedName;

public class GetPuskeswanDetail {
    @SerializedName("status")
    String status;
    @SerializedName("data")
    Puskeswan puskeswan;
    @SerializedName("message")
    String message;

    public GetPuskeswanDetail(String status, Puskeswan puskeswan, String message) {
        this.status = status;
        this.puskeswan = puskeswan;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Puskeswan getPuskeswan() {
        return puskeswan;
    }

    public void setPuskeswan(Puskeswan puskeswan) {
        this.puskeswan = puskeswan;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
