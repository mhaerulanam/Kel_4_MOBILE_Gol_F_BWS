package com.def.dokternak.data.Model.konsultasi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetKonsultasiTerkirim {
    @SerializedName("status")
    String status;
    @SerializedName("data")
    List<Konsultasi> listDataKonsultasi;
    @SerializedName("message")
    String message;

    public GetKonsultasiTerkirim(String status, List<Konsultasi> listDataKonsultasi, String message) {
        this.status = status;
        this.listDataKonsultasi = listDataKonsultasi;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Konsultasi> getListDataKonsultasi() {
        return listDataKonsultasi;
    }

    public void setListDataKonsultasi(List<Konsultasi> listDataKonsultasi) {
        this.listDataKonsultasi = listDataKonsultasi;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
