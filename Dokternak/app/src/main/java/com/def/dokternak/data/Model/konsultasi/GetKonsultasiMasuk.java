package com.def.dokternak.data.Model.konsultasi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetKonsultasiMasuk {
    @SerializedName("status")
    String status;
    @SerializedName("data")
    List<RiwayatKonsultasi> listDataKonsultasiMasuk;
    @SerializedName("message")
    String message;

    public GetKonsultasiMasuk(String status, List<RiwayatKonsultasi> listDataKonsultasiMasuk, String message) {
        this.status = status;
        this.listDataKonsultasiMasuk = listDataKonsultasiMasuk;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<RiwayatKonsultasi> getListDataKonsultasiMasuk() {
        return listDataKonsultasiMasuk;
    }

    public void setListDataKonsultasiMasuk(List<RiwayatKonsultasi> listDataKonsultasiMasuk) {
        this.listDataKonsultasiMasuk = listDataKonsultasiMasuk;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
