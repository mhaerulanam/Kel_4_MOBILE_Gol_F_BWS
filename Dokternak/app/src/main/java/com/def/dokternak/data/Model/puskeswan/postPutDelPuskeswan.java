package com.def.dokternak.data.Model.puskeswan;

import com.def.dokternak.data.Model.petugas.Petugas;
import com.google.gson.annotations.SerializedName;

public class postPutDelPuskeswan {
    @SerializedName("status")
    String status;
    @SerializedName("data")
    Puskeswan result;
    @SerializedName("message")
    String message;

    public postPutDelPuskeswan(String status, Puskeswan result, String message) {
        this.status = status;
        this.result = result;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Puskeswan getResult() {
        return result;
    }

    public void setResult(Puskeswan result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
