package com.example.bc_mobile.model.movie;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieResponse {
    @SerializedName("status")
    boolean status;
    @SerializedName("pesan")
    private String pesan;
    @SerializedName("data")
    private ArrayList<Movie> data;
    public boolean isStatus() { return this.status; }
    public String getMessage() { return this.pesan; }
    public ArrayList<Movie> getData(){
        return this.data;
    }
}
