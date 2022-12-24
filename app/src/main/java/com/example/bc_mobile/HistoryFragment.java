package com.example.bc_mobile;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.bc_mobile.adapter.HistoryAdapter;
import com.example.bc_mobile.adapter.MovieAdapter;
import com.example.bc_mobile.api.APIConfig;
import com.example.bc_mobile.model.bill.Bill;
import com.example.bc_mobile.model.bill.BillResponse;
import com.example.bc_mobile.model.bill.HistoryResponse;
import com.example.bc_mobile.model.customer.Customer;
import com.example.bc_mobile.model.history.History;
import com.example.bc_mobile.model.movie.Movie;
import com.example.bc_mobile.model.movie.MovieResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageView back;
    private RecyclerView riwayatRecyclerView;
    private RecyclerView.LayoutManager riwayatLayoutManager;
    private HistoryAdapter riwayatAdapter;
    private ArrayList<Bill> historyList = new ArrayList<Bill>();
    Integer customerAuth;
    RecyclerView mRecyclerView;
    HistoryAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    public HistoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();
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
        View v = inflater.inflate(R.layout.fragment_history, container, false);
        back = v.findViewById(R.id.iv_icon_back_riwayat);

        Intent intent = getActivity().getIntent();
        customerAuth = Integer.valueOf(intent.getStringExtra("auth"));
        recycleBuild(v);
        getHistory(customerAuth);
        return v;
    }

    public void recycleBuild(View apa){
        View v = apa;
        mRecyclerView = (RecyclerView) v.findViewById(R.id.rv_riwayat);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        mAdapter = new HistoryAdapter(requireContext(), historyList);
        mRecyclerView.setAdapter(mAdapter);
    }
    public void getHistory(Integer id){
        Call<HistoryResponse> client = APIConfig.getApiService().getHistory(id);
        client.enqueue(new Callback<HistoryResponse>() {
            @Override
            public void onResponse(Call<HistoryResponse> call, Response<HistoryResponse> response) {
//                    showLoading(false);
                if (response.isSuccessful()){
                    if (response.body() != null){
                        historyList.clear();
                        historyList.addAll(response.body().getData());
                        mAdapter.notifyDataSetChanged();
                    }
                } else {
                    if (response.body() != null) {
                        Log.e("", "onFailure: " + response.body().getMessage());
                    }
                }
            }
            @Override
            public void onFailure(Call<HistoryResponse> call, Throwable t) {
//                    showLoading(false);
                Log.e("Error Retrofit", "onFailure: " + t.getMessage());
            }
        });
    }
}