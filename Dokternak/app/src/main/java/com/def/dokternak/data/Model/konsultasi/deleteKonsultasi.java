package com.def.dokternak.data.Model.konsultasi;

import com.google.gson.annotations.SerializedName;

public class deleteKonsultasi {
    @SerializedName("status")
    String status;
    @SerializedName("data")
    Konsultasi konsultasi;
    @SerializedName("message")
    String message;

    public deleteKonsultasi(String status, Konsultasi konsultasi, String message) {
        this.status = status;
        this.konsultasi = konsultasi;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Konsultasi getKonsultasi() {
        return konsultasi;
    }

    public void setKonsultasi(Konsultasi konsultasi) {
        this.konsultasi = konsultasi;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
