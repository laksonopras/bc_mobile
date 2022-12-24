package com.example.bc_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bc_mobile.adapter.MovieAdapter;
import com.example.bc_mobile.adapter.ScheduleAdapter;
import com.example.bc_mobile.api.APIConfig;
import com.example.bc_mobile.model.movie.MovieResponse;
import com.example.bc_mobile.model.schedule.Schedule;
import com.example.bc_mobile.model.schedule.ScheduleResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleActivity extends AppCompatActivity {

    private ImageView back;
    private TextView tvTitle;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ScheduleAdapter mAdapter;
    private ArrayList<Schedule> listSchedule = new ArrayList<Schedule>();
    Integer customerAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        back = (ImageView) findViewById(R.id.iv_icon_back);
        tvTitle = (TextView) findViewById(R.id.tv_title_nav);


        Intent intent = getIntent();
        String id = intent.getStringExtra("filmId");
        tvTitle.setText(intent.getStringExtra("filmTitle"));
        customerAuth = Integer.valueOf(intent.getStringExtra("auth"));

        recycleBuild();
        getSchedule(Integer.valueOf(id));

    }

    public void getSchedule(Integer id){
        Call<ScheduleResponse> client = APIConfig.getApiService().getSchedule(id);
        client.enqueue(new Callback<ScheduleResponse>() {
            @Override
            public void onResponse(Call<ScheduleResponse> call, Response<ScheduleResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        listSchedule.clear();
                        listSchedule.addAll(response.body().getData());
                        mAdapter.notifyDataSetChanged();
                    }
                } else {
                    if (response.body() != null) {
                        Log.e("", "onFailure: " + response.body().getMessage());
                    }
                }
            }
            @Override
            public void onFailure(Call<ScheduleResponse> call, Throwable t) {
//                    showLoading(false);
                Log.e("Error Retrofit", "onFailure: " + t.getMessage());
            }
        });
    }
    public void recycleBuild(){
        mRecyclerView = findViewById(R.id.rv_jadwal);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ScheduleAdapter(ScheduleActivity.this, listSchedule, customerAuth);
        mRecyclerView.setAdapter(mAdapter);
    }
}