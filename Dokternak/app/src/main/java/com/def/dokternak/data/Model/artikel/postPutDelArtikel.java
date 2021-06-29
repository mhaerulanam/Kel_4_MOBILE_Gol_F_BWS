package com.def.dokternak.data.Model.artikel;

import com.google.gson.annotations.SerializedName;

public class postPutDelArtikel {
    @SerializedName("status")
    String status;
    @SerializedName("data")
    Artikel result;
    @SerializedName("message")
    String message;
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Artikel getArtikel() {
        return result;
    }
    public void setArtikel(Artikel artikel) {
        result = artikel;
    }
}
