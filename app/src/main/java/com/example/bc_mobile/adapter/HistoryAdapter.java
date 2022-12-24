package com.example.bc_mobile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bc_mobile.R;
import com.example.bc_mobile.model.bill.Bill;
import com.example.bc_mobile.model.customer.Customer;
import com.example.bc_mobile.model.history.History;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.CustomViewHolder>{

    private LayoutInflater mInflaterRiwayat;
    private ArrayList<Bill> riwayat;

    public HistoryAdapter (Context context, ArrayList<Bill> historyOrders) {
        this.mInflaterRiwayat = LayoutInflater.from(context);
        this.riwayat = historyOrders;
    }

    @NonNull
    @Override
    public HistoryAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView1 = mInflaterRiwayat.inflate(R.layout.item_history, parent, false);
        return new HistoryAdapter.CustomViewHolder(itemView1, this);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.CustomViewHolder holder, int position) {
        final String bCode = riwayat.get(position).getKode_booking();
        final String rdmCode = riwayat.get(position).getKode_booking();
        final String mTitle1 = riwayat.get(position).getJudul_film();
        final String stimes1 = riwayat.get(position).getJam();
        final String cClass1 = riwayat.get(position).getKelas();
        final String rCode1 = riwayat.get(position).getStudio();
        final String oTotal = String.valueOf(riwayat.get(position).getJumlah_tiket());
        final String sumPrice = String.valueOf(riwayat.get(position).getTotal_harga());

        //holder.bookingCode.setText(bCode);
        holder.randomCode.setText(rdmCode);
        holder.movieTitle1.setText(mTitle1);
        holder.showtimes1.setText(stimes1);
        holder.cinemaClass1.setText(cClass1);
        holder.roomCode1.setText(rCode1);
        holder.orderTotal.setText(oTotal);
        holder.totalPrice.setText(sumPrice);
        holder.cvItem1.setOnClickListener((view -> {
            Toast.makeText(view.getContext(), mTitle1 + " ditampilkan!", Toast.LENGTH_SHORT).show();
        }));
    }

    @Override
    public int getItemCount() {
        return riwayat.size();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        public CardView cvItem1;
        public TextView bookingCode;
        public TextView randomCode;
        public TextView movieTitle1;
        public TextView showtimes1;
        public TextView cinemaClass1;
        public TextView roomCode1;
        public TextView orderTotal;
        public TextView totalPrice;
        private HistoryAdapter mAdapter;

        public CustomViewHolder(@NonNull View itemView, HistoryAdapter adapter) {
            super(itemView);

            //get the layout
            cvItem1 = (CardView) itemView.findViewById(R.id.cv_item_riwayat);
            bookingCode = (TextView) itemView.findViewById(R.id.tv_bookingCode);
            randomCode = (TextView) itemView.findViewById(R.id.tv_randomCode);
            movieTitle1 = (TextView) itemView.findViewById(R.id.tv_movie_title1);
            showtimes1 = (TextView) itemView.findViewById(R.id.tv_showtimes1);
            cinemaClass1 = (TextView) itemView.findViewById(R.id.tv_cinema_class1);
            roomCode1 = (TextView) itemView.findViewById(R.id.tv_room_code1);
            orderTotal = (TextView) itemView.findViewById(R.id.tv_order_total);
            totalPrice = (TextView) itemView.findViewById(R.id.tv_total_price);

            //Associate eith this adapter
            this.mAdapter = adapter;
        }
    }
}