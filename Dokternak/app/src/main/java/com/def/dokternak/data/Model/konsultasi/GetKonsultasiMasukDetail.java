package com.def.dokternak.data.Model.konsultasi;

import com.def.dokternak.data.Model.petugas.Petugas;
import com.google.gson.annotations.SerializedName;

public class GetKonsultasiMasukDetail {
    @SerializedName("status")
    String status;
    @SerializedName("data")
    RiwayatKonsultasi konsultasi;
    @SerializedName("message")
    String message;

    public GetKonsultasiMasukDetail(String status, RiwayatKonsultasi konsultasi, String message) {
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

    public RiwayatKonsultasi getKonsultasi() {
        return konsultasi;
    }

    public void setKonsultasi(RiwayatKonsultasi konsultasi) {
        this.konsultasi = konsultasi;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
