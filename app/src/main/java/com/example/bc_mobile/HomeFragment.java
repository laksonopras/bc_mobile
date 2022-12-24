package com.example.bc_mobile;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;


import com.example.bc_mobile.adapter.MovieAdapter;
import com.example.bc_mobile.api.APIConfig;
import com.example.bc_mobile.model.customer.Customer;
import com.example.bc_mobile.model.customer.CustomerResponse;
import com.example.bc_mobile.model.movie.Movie;
import com.example.bc_mobile.model.movie.MovieResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
//    private Customer pelangganAuth;
    private SearchView svSearch;

    ArrayList<Movie> listMovie = new ArrayList<Movie>();
    RecyclerView mRecyclerView;
    MovieAdapter mAdapter;
    Integer customerAuth;
    RecyclerView.LayoutManager mLayoutManager;

    View apa;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        svSearch = v.findViewById(R.id.searchFilm);
        listMovie = new ArrayList<>();

        Intent intent = getActivity().getIntent();
        customerAuth = Integer.valueOf(intent.getStringExtra("auth"));
        recycleBuild(v);
        getMovie();
        return v;
    }

    public void recycleBuild(View apa){
        View v = apa;
        mRecyclerView = (RecyclerView) v.findViewById(R.id.rvFilmRecommend);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        mAdapter = new MovieAdapter(requireContext(), listMovie, customerAuth);
        mRecyclerView.setAdapter(mAdapter);
    }
    public void getMovie(){
        Call<MovieResponse> client = APIConfig.getApiService().getFilm();
        client.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
//                    showLoading(false);
                if (response.isSuccessful()){
                    if (response.body() != null){
                        listMovie.clear();
                        listMovie.addAll(response.body().getData());
                        mAdapter.notifyDataSetChanged();
                    }
                } else {
                    if (response.body() != null) {
                        Log.e("", "onFailure: " + response.body().getMessage());
                    }
                }
            }
            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
//                    showLoading(false);
                Log.e("Error Retrofit", "onFailure: " + t.getMessage());
            }
        });
    }

}