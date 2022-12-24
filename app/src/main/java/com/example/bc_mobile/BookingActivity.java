package com.example.bc_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bc_mobile.adapter.ScheduleAdapter;
import com.example.bc_mobile.api.APIConfig;
import com.example.bc_mobile.model.movie.MovieResponse;
import com.example.bc_mobile.model.schedule.DetailScheduleResponse;
import com.example.bc_mobile.model.schedule.Schedule;
import com.example.bc_mobile.model.schedule.ScheduleResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingActivity extends AppCompatActivity {

    private ImageView img_back;
    private TextView tv_judul_film, tv_tanggal, tv_jam, tv_kode_ruangan, tv_kelas, tv_harga, tv_kode_bayar, tv_total, tv_total2,tv_jumlah;
    private Button btn_konfirmasi, btn_min, btn_max;
    private TextView tv_header_pesan, tv_detail, tv_hartik, tv_jml, tv_ttl, tv_kodbay, tv_salin, tv_ttl2;
    Schedule detailSchedule;
    Integer jumlah = 0;
    Integer customerAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        img_back = (ImageView) findViewById(R.id.back);
        tv_judul_film = (TextView) findViewById(R.id.judul_film);
        tv_tanggal = findViewById(R.id.tanggal);
        tv_jam =(TextView) findViewById(R.id.jam);
        tv_kode_ruangan =(TextView) findViewById(R.id.kode_ruangan);
        tv_kelas = (TextView) findViewById(R.id.kelas);
        tv_harga =(TextView) findViewById(R.id.harga);
        tv_jumlah = findViewById(R.id.jumlah);
        tv_kode_bayar =(TextView) findViewById(R.id.kode_bayar);
        tv_total =(TextView) findViewById(R.id.total);
        tv_total2 =(TextView) findViewById(R.id.total2);
        btn_konfirmasi = (Button) findViewById(R.id.konfirmasi);
        btn_min = findViewById(R.id.min);
        btn_max = findViewById(R.id.max);

        tv_header_pesan = (TextView) findViewById(R.id.header_pesan);
        tv_detail= (TextView) findViewById(R.id.detail);
        tv_hartik= (TextView) findViewById(R.id.hartik);
        tv_jml= (TextView) findViewById(R.id.jml);
        tv_ttl = (TextView) findViewById(R.id.ttl);
        tv_ttl2= (TextView) findViewById(R.id.ttl2);



        Intent intent = getIntent();
        String id_jadwal = intent.getStringExtra("id_jadwal");
        customerAuth = Integer.parseInt(intent.getStringExtra("auth"));
        getDetail(Integer.valueOf(id_jadwal));

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(jumlah > 0 ){
                    tv_jumlah.setText(String.valueOf(jumlah = jumlah - 1));
                    tv_total.setText(String.valueOf(jumlah * detailSchedule.getHarga()));
                    tv_total2.setText(String.valueOf(jumlah * detailSchedule.getHarga()));
                }

            }
        });

        btn_max.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_jumlah.setText(String.valueOf(jumlah = jumlah + 1));
                tv_total.setText(String.valueOf(jumlah * detailSchedule.getHarga()));
                tv_total2.setText(String.valueOf(jumlah * detailSchedule.getHarga()));

            }
        });

        btn_konfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BookingActivity.this, BillActivity.class);
                intent.putExtra("id_jadwal", Integer.toString(detailSchedule.getId_jadwal()));
                intent.putExtra("jumlah_tiket", tv_jumlah.getText().toString());
                intent.putExtra("auth", Integer.toString(customerAuth));
                startActivity(intent);
            }
        });

    }

    public void getDetail(Integer id){
        Call<DetailScheduleResponse> client = APIConfig.getApiService().getDetailSchedule(id);
        client.enqueue(new Callback<DetailScheduleResponse>() {
            @Override
            public void onResponse(Call<DetailScheduleResponse> call, Response<DetailScheduleResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        detailSchedule = response.body().getData();

                        tv_judul_film.setText(detailSchedule.getJudul());
                        tv_tanggal.setText(detailSchedule.getTanggal());
                        tv_jam.setText(detailSchedule.getJam());
                        tv_kode_ruangan.setText(String.valueOf(detailSchedule.getStudio()));
                        tv_kelas.setText(detailSchedule.getKelas());
                        tv_harga.setText(String.valueOf(detailSchedule.getHarga()));
                        tv_jumlah.setText(String.valueOf(jumlah));
                        tv_total.setText(String.valueOf(jumlah * detailSchedule.getHarga()));
                        tv_total2.setText(String.valueOf(jumlah * detailSchedule.getHarga()));
                    }
                } else {
                    if (response.body() != null) {
                        Log.e("", "onFailure: " + response.body().getPesan());
                    }
                }
            }
            @Override
            public void onFailure(Call<DetailScheduleResponse> call, Throwable t) {
//                    showLoading(false);
                Log.e("Error Retrofit", "onFailure: " + t.getMessage());
            }
        });
    }
}