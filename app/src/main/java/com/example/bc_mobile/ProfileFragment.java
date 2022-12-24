package com.example.bc_mobile;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bc_mobile.api.APIConfig;
import com.example.bc_mobile.model.bill.HistoryResponse;
import com.example.bc_mobile.model.customer.Customer;
import com.example.bc_mobile.model.customer.CustomerResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageView img_back;
    private TextView tv_judul, tv_nama, tv_email, tv_profile;
    private Customer customerAuth = new Customer ();

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        img_back = v.findViewById(R.id.back);
        tv_judul= v.findViewById(R.id.header_pesan);
        tv_nama = v.findViewById(R.id.nama);
        tv_email= v.findViewById(R.id.email);
        tv_profile = v.findViewById(R.id.char_profile);

        Intent intent = getActivity().getIntent();
        getProfile(Integer.valueOf(intent.getStringExtra("auth")));


        return v;
    }

    public void getProfile(Integer id){
        Call<CustomerResponse> client = APIConfig.getApiService().getProfile(id);
        client.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
//                    showLoading(false);
                if (response.isSuccessful()){
                    if (response.body() != null){
                        customerAuth = response.body().getData();
                        tv_nama.setText(customerAuth.getNama());
                        tv_email.setText(customerAuth.getEmail());
                        tv_profile.setText(String.valueOf(customerAuth.getNama().charAt(0)).toUpperCase());
                    }
                } else {
                    if (response.body() != null) {
                        Log.e("", "onFailure: " + response.body().getMessage());
                    }
                }
            }
            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {
//                    showLoading(false);
                Log.e("Error Retrofit", "onFailure: " + t.getMessage());
            }
        });

    }
}