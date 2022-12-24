package com.example.bc_mobile.model.movie;

public class Movie {
    private int id_film;
    private String judul;
    private double rating;
    private String storyline;
    private String poster;
    private String backdrop;

    public int getId() { return this.id_film; }
    public void setId(int id) {
        this.id_film = id;
    }
    public String getJudul() {
        return this.judul;
    }
    public void setJudul(String judul) {
        this.judul = judul;
    }
    public double getPenilaian() {
        return this.rating;
    }
    public void setPenilaian(double penilaian) {
        this.rating = penilaian;
    }
    public String getOverview() { return this.storyline; }
    public void setOverview(String overview) {
        this.storyline = storyline;
    }
    public String getPoster() { return this.poster; }
    public void setPoster(String poster) {
        this.poster = poster;
    }
    public String getBackdrop() { return this.backdrop; }
    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }
}
