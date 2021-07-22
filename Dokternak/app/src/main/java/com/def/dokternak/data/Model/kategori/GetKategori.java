package com.def.dokternak.data.Model.kategori;

import com.def.dokternak.data.Model.petugas.Petugas;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetKategori {
    @SerializedName("status")
    String status;
    @SerializedName("data")
    List<KategoriHewan> listDataKategori;
    @SerializedName("message")
    String message;

    public GetKategori(String status, List<KategoriHewan> listDataKategori, String message) {
        this.status = status;
        this.listDataKategori = listDataKategori;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<KategoriHewan> getListDataKategori() {
        return listDataKategori;
    }

    public void setListDataKategori(List<KategoriHewan> listDataKategori) {
        this.listDataKategori = listDataKategori;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
