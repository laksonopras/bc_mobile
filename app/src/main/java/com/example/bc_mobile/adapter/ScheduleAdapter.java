package com.example.bc_mobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bc_mobile.BookingActivity;
import com.example.bc_mobile.R;
import com.example.bc_mobile.ScheduleActivity;
import com.example.bc_mobile.model.schedule.Schedule;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.concurrent.ScheduledExecutorService;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.CustomViewHolder>{

    private LayoutInflater mInflater;
    private ArrayList<Schedule> jadwal;
    private Context mContext;
    Integer customerAuth;

    public ScheduleAdapter (Context context, ArrayList<Schedule> movieSchedule, Integer customerAuth) {
        this.mInflater = LayoutInflater.from(context);
        this.jadwal = movieSchedule;
        this.mContext = context;
        this.customerAuth = customerAuth;
    }

    public void add(int position, Schedule item) {
        jadwal.add(position, item);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        jadwal.remove(position);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ScheduleAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //return null;
        // Create view from layout

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = mInflater.inflate(R.layout.rowitem_jadwal, parent, false);
        return new CustomViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleAdapter.CustomViewHolder holder, int position) {
        final Schedule detail = jadwal.get(position);
        final Integer id = detail.getId_jadwal();
        final String mTitle = jadwal.get(position).getJudul();
        final String cClass = jadwal.get(position).getKelas();
        final Integer rCode = jadwal.get(position).getStudio();
        final String stimes = jadwal.get(position).getJam();
        final Integer tPrice = jadwal.get(position).getHarga();
//        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
//        String strDate = dateFormat.format(stimes);
        String code = "1";
        holder.movieTitle.setText(jadwal.get(position).getTanggal());
        holder.cinemaClass.setText(cClass);
        holder.roomCode.setText(String.valueOf(rCode));
        holder.showtimes.setText(stimes);
        holder.ticketPrice.setText(String.valueOf(tPrice));
        holder.cvItem.setOnClickListener((view -> {
            Intent intent = new Intent(mContext, BookingActivity.class);
            intent.putExtra("id_jadwal", Integer.toString(detail.getId_jadwal()));
            intent.putExtra("auth", Integer.toString(customerAuth));
            mContext.startActivity(intent);
            Toast.makeText(view.getContext(), mTitle + " ditampilkan!", Toast.LENGTH_SHORT).show();
        }));
    }

    @Override
    public int getItemCount() {
        // Return the number of
        // data items to display
        return jadwal.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public CardView cvItem;
        public TextView movieTitle;
        public TextView cinemaClass;
        public TextView roomCode;
        public TextView showtimes;
        public TextView ticketPrice;
        private ScheduleAdapter mAdapter;

        public CustomViewHolder(@NonNull View itemView, ScheduleAdapter adapter) {
            super(itemView);

            //get the layout
            cvItem = (CardView) itemView.findViewById(R.id.cv_item_jadwal);
            movieTitle = (TextView) itemView.findViewById(R.id.tv_movie_title);
            cinemaClass = (TextView) itemView.findViewById(R.id.tv_cinema_class);
            roomCode = (TextView) itemView.findViewById(R.id.tv_room_code);
            showtimes = (TextView) itemView.findViewById(R.id.tv_showtimes);
            ticketPrice = (TextView) itemView.findViewById(R.id.tv_ticket_price);

            //Associate eith this adapter
            this.mAdapter = adapter;
        }
    }
}
