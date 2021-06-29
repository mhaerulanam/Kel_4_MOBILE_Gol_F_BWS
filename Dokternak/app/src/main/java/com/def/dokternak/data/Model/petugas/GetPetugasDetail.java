package com.def.dokternak.data.Model.petugas;

import com.def.dokternak.data.Model.artikel.Artikel;
import com.google.gson.annotations.SerializedName;

public class GetPetugasDetail {
    @SerializedName("status")
    String status;
    @SerializedName("data")
    Petugas petugas;
    @SerializedName("message")
    String message;

    public GetPetugasDetail(String status, Petugas petugas, String message) {
        this.status = status;
        this.petugas = petugas;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Petugas getPetugas() {
        return petugas;
    }

    public void setPetugas(Petugas petugas) {
        this.petugas = petugas;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
