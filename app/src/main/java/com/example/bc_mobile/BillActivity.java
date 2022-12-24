package com.example.bc_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bc_mobile.api.APIConfig;
import com.example.bc_mobile.model.bill.Bill;
import com.example.bc_mobile.model.bill.BillResponse;
import com.example.bc_mobile.model.customer.CustomerResponse;
import com.example.bc_mobile.model.schedule.DetailScheduleResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BillActivity extends AppCompatActivity {
    private ImageView img_back;
    private TextView tv_judul_film, tv_jam, tv_kode_ruangan, tv_kelas, tv_harga, tv_jumlah, tv_kode_bayar, tv_total, tv_total2;
    private Button btn_back;
    private TextView tv_header_pesan, tv_detail, tv_hartik, tv_jml, tv_ttl, tv_kodbay, tv_salin, tv_ttl2;

    Integer id_jadwal;
    Integer id_pelanggan;
    Integer jumlah_tiket;
    Bill bill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        //img_back = findViewById(R.id.back);
        tv_judul_film = findViewById(R.id.judul_film);
        tv_jam = findViewById(R.id.jam);
        tv_kode_ruangan = findViewById(R.id.kode_ruangan);
        tv_kelas =  findViewById(R.id.kelas);
        tv_harga = findViewById(R.id.harga);
        tv_jumlah = findViewById(R.id.jumlah);
        tv_kode_bayar = findViewById(R.id.kode_bayar);
        tv_total = findViewById(R.id.total);
        tv_header_pesan = (TextView) findViewById(R.id.header_pesan);
        tv_detail= (TextView) findViewById(R.id.detail);
        tv_hartik= (TextView) findViewById(R.id.hartik);
        tv_jml= (TextView) findViewById(R.id.jml);
        tv_salin= (TextView) findViewById(R.id.salin);
        tv_ttl2= (TextView) findViewById(R.id.ttl2);
        btn_back = findViewById(R.id.back);

        Intent intent = getIntent();
        id_jadwal = Integer.valueOf(intent.getStringExtra("id_jadwal"));
        id_pelanggan = Integer.valueOf(intent.getStringExtra("auth"));
        jumlah_tiket = Integer.valueOf(intent.getStringExtra("jumlah_tiket"));
        getBill(id_pelanggan, id_jadwal, jumlah_tiket);

        btn_back.setOnClickListener(view -> {
            Intent intent1 = new Intent(BillActivity.this, MainActivity.class);
            startActivity(intent1);
        });
    }

    public void getBill (Integer id_pelanggan, Integer id_jadwal, Integer jumlah_tiket){
        Call<BillResponse> client = APIConfig.getApiService().transaction(id_pelanggan, id_jadwal, jumlah_tiket);
        client.enqueue(new Callback<BillResponse>() {
            @Override
            public void onResponse(Call<BillResponse> call, Response<BillResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        bill = response.body().getData();

                        tv_judul_film.setText("Judul Film : "+bill.getJudul_film());
                        tv_jam.setText(bill.getJam());
                        tv_kode_ruangan.setText("Studio : "+bill.getStudio());
                        tv_kelas.setText(bill.getKelas());
                        tv_harga.setText("Rp. "+Integer.toString(bill.getHarga_tiket()));
                        tv_jumlah.setText(Integer.toString(bill.getJumlah_tiket()));
                        tv_kode_bayar.setText(bill.getKode_booking());
                        tv_total.setText("Rp. "+Integer.toString(bill.getTotal_harga()));

                    }
                } else {
                    if (response.body() != null) {
                        Log.e("", "onFailure: " + response.body().getMessage());
                    }
                }
            }
            @Override
            public void
            onFailure(Call<BillResponse> call, Throwable t) {
//                    showLoading(false);
                Log.e("Error Retrofit", "onFailure: " + t.getMessage());
            }
        });
    }

}