package com.example.bc_mobile.api;


import com.example.bc_mobile.model.bill.BillResponse;
import com.example.bc_mobile.model.bill.HistoryResponse;
import com.example.bc_mobile.model.customer.CustomerAuth;
import com.example.bc_mobile.model.customer.CustomerResponse;
import com.example.bc_mobile.model.movie.Movie;
import com.example.bc_mobile.model.movie.MovieResponse;
import com.example.bc_mobile.model.schedule.DetailScheduleResponse;
import com.example.bc_mobile.model.schedule.Schedule;
import com.example.bc_mobile.model.schedule.ScheduleResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    @POST("auth/daftar")
    @FormUrlEncoded
    Call<CustomerResponse> register(
            @Field("nama") String username,
            @Field("email") String email,
            @Field("password") String password
    );
    @FormUrlEncoded
    @POST("auth/masuk")
    Call<CustomerAuth> login(
            @Field("email") String email,
            @Field("password") String password
    );
    @FormUrlEncoded
    @POST("transaction/add")
    Call<BillResponse> transaction(
            @Field("id_pelanggan") Integer id_pelanggan,
            @Field("id_jadwal") Integer id_jadwal,
            @Field("jumlah_tiket") Integer jumlah_tiket
    );
    @GET("film")
    Call<MovieResponse> getFilm();


    @POST("film/jadwal/{id}")
    Call<ScheduleResponse> getSchedule(
            @Path("id") Integer id
    );

    @POST("jadwal/{id}")
    Call<DetailScheduleResponse> getDetailSchedule(
            @Path("id") Integer id
    );

    @POST("pelanggan/history/{id}")
    Call<HistoryResponse> getHistory(
            @Path("id") Integer id
    );
    @POST("pelanggan/{id}")
    Call<CustomerResponse> getProfile(
            @Path("id") Integer id
    );

}
//
//    @GET("mahasiswa")
//    Call<MahasiswaResponse> getMahasiswa(@Query("nrp") String nrp);
//    @POST("mahasiswa")
//    @FormUrlEncoded
//    Call<AddMahasiswaResponse> addMahasiswa(
//            @Field("nrp") String nrp,
//            @Field("nama") String nama,
//            @Field("email") String email,
//            @Field("jurusan") String jurusan
//    );
//    @FormUrlEncoded
//    @HTTP(method = "DELETE", path = "/mahasiswa", hasBody = true)
//    Call<AddMahasiswaResponse> deleteMahasiswa(@Field("id") String id);
//
//    @FormUrlEncoded
//    @HTTP(method = "PUT", path = "/mahasiswa", hasBody = true)
//    Call<AddMahasiswaResponse> updateMahasiswa(
//            @Field("id") String id,
//            @Field("nrp") String nrp,
//            @Field("nama") String nama,
//            @Field("email") String email,
//            @Field("jurusan") String jurusan
//    );
