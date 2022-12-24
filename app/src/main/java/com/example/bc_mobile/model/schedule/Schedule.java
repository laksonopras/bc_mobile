package com.example.bc_mobile.model.schedule;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Schedule {


    public Integer getId_jadwal() {
        return id_jadwal;
    }

    public void setId_jadwal(Integer id_jadwal) {
        this.id_jadwal = id_jadwal;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public Integer getStudio() {
        return studio;
    }

    public void setStudio(Integer studio) {
        this.studio = studio;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    private Integer id_jadwal;
    private String judul;
    private String tanggal;
    private String jam;
    private Integer studio;
    private String kelas;
    private Integer harga;

}
