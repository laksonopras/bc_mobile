package com.example.bc_mobile.model.schedule;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DetailScheduleResponse {
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public Schedule getData() {
        return data;
    }

    public void setData(Schedule data) {
        this.data = data;
    }

    @SerializedName("status")
    boolean status;
    @SerializedName("pesan")
    private String pesan;
    @SerializedName("data")
    private Schedule data;
}
