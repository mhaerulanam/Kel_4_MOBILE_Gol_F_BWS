package com.def.dokternak.data.Model.artikel;

import com.def.dokternak.data.Model.kategori.GetKategori;
import com.def.dokternak.data.Model.petugas.Petugas;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetKategoriArtikel {
    @SerializedName("status")
    String status;
    @SerializedName("data")
    List<Artikel> listDataKategoriArtikel;
    @SerializedName("message")
    String message;

    public GetKategoriArtikel(String status, List<Artikel> listDataKategoriArtikel, String message) {
        this.status = status;
        this.listDataKategoriArtikel = listDataKategoriArtikel;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Artikel> getListDataKategoriArtikel() {
        return listDataKategoriArtikel;
    }

    public void setListDataKategoriArtikel(List<Artikel> listDataKategoriArtikel) {
        this.listDataKategoriArtikel = listDataKategoriArtikel;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
