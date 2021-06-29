package com.def.dokternak.data.Model.petugas;

import com.def.dokternak.data.Model.artikel.Artikel;
import com.google.gson.annotations.SerializedName;

public class postPutDelPetugas {
    @SerializedName("status")
    String status;
    @SerializedName("data")
    Petugas result;
    @SerializedName("message")
    String message;

    public postPutDelPetugas(String status, Petugas result, String message) {
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

    public Petugas getResult() {
        return result;
    }

    public void setResult(Petugas result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
