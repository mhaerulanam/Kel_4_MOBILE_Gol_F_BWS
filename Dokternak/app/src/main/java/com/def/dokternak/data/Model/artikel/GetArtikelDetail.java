package com.def.dokternak.data.Model.artikel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetArtikelDetail {
    @SerializedName("status")
    String status;
    @SerializedName("data")
    Artikel artikel;
    @SerializedName("message")
    String message;

    public GetArtikelDetail(String status, Artikel artikel, String message) {
        this.status = status;
        this.artikel = artikel;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Artikel getArtikel() {
        return artikel;
    }

    public void setArtikel(Artikel artikel) {
        this.artikel = artikel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
