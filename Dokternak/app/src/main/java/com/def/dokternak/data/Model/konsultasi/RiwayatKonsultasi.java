package com.def.dokternak.data.Model.konsultasi;

import com.google.gson.annotations.SerializedName;

public class RiwayatKonsultasi {
    @SerializedName("id_riwayat")
    private int id_riwayat;
    @SerializedName("id_konsultasi")
    private int id_konsultasi;
    @SerializedName("id_respon")
    private int id_respon;
    @SerializedName("id_peternak")
    private int id_peternak;
    @SerializedName("id_dokter")
    private int id_dokter;
    @SerializedName("id_kategori")
    private int id_katgeori;
    @SerializedName("id_ktg")
    private int id_ktg;
    @SerializedName("id_jabatan")
    private int id_jabatn;
    @SerializedName("id")
    private int id;
    @SerializedName("tanggal_respon")
    private String tanggalRespon;
    @SerializedName("respon")
    private String respon;
    @SerializedName("nama_hewan")
    private String namaHewan;
    @SerializedName("keluhan")
    private String keluhan;
    @SerializedName("tanggal")
    private String tanggal;
    @SerializedName("status_kirim")
    private String statusKirim;
    @SerializedName("nama_dokter")
    private String namaDokter;
    @SerializedName("email")
    private String email;
    @SerializedName("jenis_kelamin")
    private String jenisKelamin;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("tempat")
    private String tempat;
    @SerializedName("telpon")
    private String telpon;
    @SerializedName("foto")
    private String foto;
    @SerializedName("sertifikasi")
    private String sertifikasi;
    @SerializedName("jadwal_kerja")
    private String jadwalKerja;
    @SerializedName("verifikasi")
    private String verifikasi;
    @SerializedName("namadepan_peternak")
    private String namaDepanPeternak;
    @SerializedName("namabelakang_peternak")
    private String namaBelakangPeternak;
    @SerializedName("email_peternak")
    private String emailPeternak;
    @SerializedName("no_hp")
    private String noHpPeternak;
    @SerializedName("foto_peternak")
    private String fotoPeternak;
    @SerializedName("kategori_artikel")
    private String jenisHewan;
    @SerializedName("kategori_hewan")
    private String kategoriHewan;

    public RiwayatKonsultasi(int id_riwayat, int id_konsultasi, int id_respon, int id_peternak, int id_dokter, int id_katgeori, int id_ktg, int id_jabatn, int id, String tanggalRespon, String respon, String namaHewan, String keluhan, String tanggal, String statusKirim, String namaDokter, String email, String jenisKelamin, String alamat, String tempat, String telpon, String foto, String sertifikasi, String jadwalKerja, String verifikasi, String namaDepanPeternak, String namaBelakangPeternak, String emailPeternak, String noHpPeternak, String fotoPeternak, String jenisHewan, String kategoriHewan) {
        this.id_riwayat = id_riwayat;
        this.id_konsultasi = id_konsultasi;
        this.id_respon = id_respon;
        this.id_peternak = id_peternak;
        this.id_dokter = id_dokter;
        this.id_katgeori = id_katgeori;
        this.id_ktg = id_ktg;
        this.id_jabatn = id_jabatn;
        this.id = id;
        this.tanggalRespon = tanggalRespon;
        this.respon = respon;
        this.namaHewan = namaHewan;
        this.keluhan = keluhan;
        this.tanggal = tanggal;
        this.statusKirim = statusKirim;
        this.namaDokter = namaDokter;
        this.email = email;
        this.jenisKelamin = jenisKelamin;
        this.alamat = alamat;
        this.tempat = tempat;
        this.telpon = telpon;
        this.foto = foto;
        this.sertifikasi = sertifikasi;
        this.jadwalKerja = jadwalKerja;
        this.verifikasi = verifikasi;
        this.namaDepanPeternak = namaDepanPeternak;
        this.namaBelakangPeternak = namaBelakangPeternak;
        this.emailPeternak = emailPeternak;
        this.noHpPeternak = noHpPeternak;
        this.fotoPeternak = fotoPeternak;
        this.jenisHewan = jenisHewan;
        this.kategoriHewan = kategoriHewan;
    }

    public int getId_riwayat() {
        return id_riwayat;
    }

    public void setId_riwayat(int id_riwayat) {
        this.id_riwayat = id_riwayat;
    }

    public int getId_konsultasi() {
        return id_konsultasi;
    }

    public void setId_konsultasi(int id_konsultasi) {
        this.id_konsultasi = id_konsultasi;
    }

    public int getId_respon() {
        return id_respon;
    }

    public void setId_respon(int id_respon) {
        this.id_respon = id_respon;
    }

    public int getId_peternak() {
        return id_peternak;
    }

    public void setId_peternak(int id_peternak) {
        this.id_peternak = id_peternak;
    }

    public int getId_dokter() {
        return id_dokter;
    }

    public void setId_dokter(int id_dokter) {
        this.id_dokter = id_dokter;
    }

    public int getId_katgeori() {
        return id_katgeori;
    }

    public void setId_katgeori(int id_katgeori) {
        this.id_katgeori = id_katgeori;
    }

    public int getId_ktg() {
        return id_ktg;
    }

    public void setId_ktg(int id_ktg) {
        this.id_ktg = id_ktg;
    }

    public int getId_jabatn() {
        return id_jabatn;
    }

    public void setId_jabatn(int id_jabatn) {
        this.id_jabatn = id_jabatn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTanggalRespon() {
        return tanggalRespon;
    }

    public void setTanggalRespon(String tanggalRespon) {
        this.tanggalRespon = tanggalRespon;
    }

    public String getRespon() {
        return respon;
    }

    public void setRespon(String respon) {
        this.respon = respon;
    }

    public String getNamaHewan() {
        return namaHewan;
    }

    public void setNamaHewan(String namaHewan) {
        this.namaHewan = namaHewan;
    }

    public String getKeluhan() {
        return keluhan;
    }

    public void setKeluhan(String keluhan) {
        this.keluhan = keluhan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getStatusKirim() {
        return statusKirim;
    }

    public void setStatusKirim(String statusKirim) {
        this.statusKirim = statusKirim;
    }

    public String getNamaDokter() {
        return namaDokter;
    }

    public void setNamaDokter(String namaDokter) {
        this.namaDokter = namaDokter;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getTelpon() {
        return telpon;
    }

    public void setTelpon(String telpon) {
        this.telpon = telpon;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getSertifikasi() {
        return sertifikasi;
    }

    public void setSertifikasi(String sertifikasi) {
        this.sertifikasi = sertifikasi;
    }

    public String getJadwalKerja() {
        return jadwalKerja;
    }

    public void setJadwalKerja(String jadwalKerja) {
        this.jadwalKerja = jadwalKerja;
    }

    public String getVerifikasi() {
        return verifikasi;
    }

    public void setVerifikasi(String verifikasi) {
        this.verifikasi = verifikasi;
    }

    public String getNamaDepanPeternak() {
        return namaDepanPeternak;
    }

    public void setNamaDepanPeternak(String namaDepanPeternak) {
        this.namaDepanPeternak = namaDepanPeternak;
    }

    public String getNamaBelakangPeternak() {
        return namaBelakangPeternak;
    }

    public void setNamaBelakangPeternak(String namaBelakangPeternak) {
        this.namaBelakangPeternak = namaBelakangPeternak;
    }

    public String getEmailPeternak() {
        return emailPeternak;
    }

    public void setEmailPeternak(String emailPeternak) {
        this.emailPeternak = emailPeternak;
    }

    public String getNoHpPeternak() {
        return noHpPeternak;
    }

    public void setNoHpPeternak(String noHpPeternak) {
        this.noHpPeternak = noHpPeternak;
    }

    public String getFotoPeternak() {
        return fotoPeternak;
    }

    public void setFotoPeternak(String fotoPeternak) {
        this.fotoPeternak = fotoPeternak;
    }

    public String getJenisHewan() {
        return jenisHewan;
    }

    public void setJenisHewan(String jenisHewan) {
        this.jenisHewan = jenisHewan;
    }

    public String getKategoriHewan() {
        return kategoriHewan;
    }

    public void setKategoriHewan(String kategoriHewan) {
        this.kategoriHewan = kategoriHewan;
    }
}
