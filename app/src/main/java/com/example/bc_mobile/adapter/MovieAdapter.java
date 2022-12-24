package com.example.bc_mobile.adapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.bc_mobile.R;
import com.example.bc_mobile.ScheduleActivity;
import com.example.bc_mobile.model.customer.Customer;
import com.example.bc_mobile.model.movie.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private ArrayList<Movie> movieList;
    private MovieAdapter.onSelectData onSelectData;
    private Context mContext;
    Double Rating;
    Integer customerAuth;

    public interface onSelectData {
        void onSelected(Movie modelMovie);
    }

    public MovieAdapter(Context context, ArrayList<Movie> listMovie, Integer customerAuth) {
        this.mContext = context;
        this.movieList = listMovie;
        this.customerAuth = customerAuth;
    }

    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_view, parent, false);
        return new MovieAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieAdapter.ViewHolder holder, int position) {
        final Movie data = movieList.get(position);
        Rating = data.getPenilaian();
        holder.tvTitle.setText(data.getJudul());
        holder.tvDesc.setText(data.getOverview());

        float newValue = Rating.floatValue();
        holder.ratingBar.setNumStars(5);
        holder.ratingBar.setStepSize((float) 0.5);
        holder.ratingBar.setRating(newValue / 2);
        Glide.with(mContext)
                .load(data.getPoster())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_image)
                        .transform(new RoundedCorners(16)))
                .into(holder.imgPhoto);
        holder.cvFilm.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, ScheduleActivity.class);
            intent.putExtra("filmId", Integer.toString(data.getId()));
            intent.putExtra("filmTitle", data.getJudul());
            intent.putExtra("auth", String.valueOf(customerAuth));
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    //Class Holder
    class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cvFilm;
        public ImageView imgPhoto;
        public TextView tvTitle;
        public TextView tvDesc;
        public RatingBar ratingBar;

        public ViewHolder(View itemView) {
            super(itemView);
            cvFilm = itemView.findViewById(R.id.cvFilm);
            imgPhoto = itemView.findViewById(R.id.imgPhoto);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDesc = itemView.findViewById(R.id.tvDesc);
            ratingBar = itemView.findViewById(R.id.ratingBar);
        }
    }

}
