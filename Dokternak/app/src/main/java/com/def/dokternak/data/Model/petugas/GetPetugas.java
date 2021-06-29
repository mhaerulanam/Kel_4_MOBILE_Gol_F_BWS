package com.def.dokternak.data.Model.petugas;

import com.def.dokternak.data.Model.artikel.Artikel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPetugas {
    @SerializedName("status")
    String status;
    @SerializedName("data")
    List<Petugas> listDataPetugas;
    @SerializedName("message")
    String message;

    public GetPetugas(String status, List<Petugas> listDataPetugas, String message) {
        this.status = status;
        this.listDataPetugas = listDataPetugas;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Petugas> getListDataPetugas() {
        return listDataPetugas;
    }

    public void setListDataPetugas(List<Petugas> listDataPetugas) {
        this.listDataPetugas = listDataPetugas;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
