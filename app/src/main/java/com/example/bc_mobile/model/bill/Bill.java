package com.example.bc_mobile.model.bill;

public class Bill {
    String kode_booking, nama_pelanggan, judul_film, kelas, tanggal, studio, jam;
    Integer harga_tiket, jumlah_tiket, total_harga;

    public String getKode_booking() {
        return kode_booking;
    }

    public String getNama_pelanggan() {
        return nama_pelanggan;
    }

    public String getJudul_film() {
        return judul_film;
    }

    public String getKelas() {
        return kelas;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getJam() {
        return jam;
    }

    public String getStudio() {
        return studio;
    }

    public Integer getHarga_tiket() {
        return harga_tiket;
    }

    public Integer getJumlah_tiket() {
        return jumlah_tiket;
    }

    public Integer getTotal_harga() {
        return total_harga;
    }
}

