package com.example.bc_mobile.model.schedule;

import com.example.bc_mobile.model.movie.Movie;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ScheduleResponse {
    @SerializedName("status")
    boolean status;
    @SerializedName("pesan")
    private String pesan;
    @SerializedName("data")
    private ArrayList<Schedule> data;
    public boolean isStatus() { return this.status; }
    public String getMessage() { return this.pesan; }
    public ArrayList<Schedule> getData(){
        return this.data;
    }
}
