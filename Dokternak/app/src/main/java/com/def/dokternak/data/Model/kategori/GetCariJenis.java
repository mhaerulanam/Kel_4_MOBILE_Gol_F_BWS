package com.def.dokternak.data.Model.kategori;

import com.def.dokternak.data.Model.petugas.Petugas;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetCariJenis {
    @SerializedName("status")
    String status;
    @SerializedName("data")
    List<KategoriHewan> listDataJenisHewan;
    @SerializedName("message")
    String message;

    public GetCariJenis(String status, List<KategoriHewan> listDataJenisHewan, String message) {
        this.status = status;
        this.listDataJenisHewan = listDataJenisHewan;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<KategoriHewan> getListDataJenisHewan() {
        return listDataJenisHewan;
    }

    public void setListDataJenisHewan(List<KategoriHewan> listDataJenisHewan) {
        this.listDataJenisHewan = listDataJenisHewan;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
