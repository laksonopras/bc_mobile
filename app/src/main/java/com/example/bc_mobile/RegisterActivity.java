package com.example.bc_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bc_mobile.api.APIConfig;
import com.example.bc_mobile.model.customer.Customer;
import com.example.bc_mobile.model.customer.CustomerResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;


import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText etRegName;
    EditText etRegEmail;
    EditText etRegPassword;
    TextView tvLoginHere;
    Button btnRegister;
    private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etRegName = findViewById(R.id.etRegName);
        etRegEmail = findViewById(R.id.etRegEmail);
        etRegPassword = findViewById(R.id.etRegPass);
        tvLoginHere = findViewById(R.id.tvLoginHere);
        btnRegister = findViewById(R.id.btnRegister);

        //mAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(view ->{
            createUser();
        });

        tvLoginHere.setOnClickListener(view ->{
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        });
    }

    private void createUser(){
        String username = etRegName.getText().toString();
        String email = etRegEmail.getText().toString();
        String password = etRegPassword.getText().toString();

        if (TextUtils.isEmpty(username)){
            etRegName.setError("Username tidak boleh kosong");
            etRegName.requestFocus();
        }else if (TextUtils.isEmpty(email)){
            etRegEmail.setError("Email tidak boleh kosong");
            etRegEmail.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            etRegPassword.setError("Password tidak boleh kosong");
            etRegPassword.requestFocus();
        }else{
            Call<CustomerResponse> client = APIConfig.getApiService().register(username, email, password);
            client.enqueue(new Callback<CustomerResponse>() {
                @Override
                public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {
//                    showLoading(false);
                    if (response.isSuccessful()){
                        if (response.body() != null){
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class );
                            startActivity(intent);
                            Toast.makeText(RegisterActivity.this, response.body().getMessage(),  Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        if (response.body() != null) {
                            Log.e("", "onFailure: " + response.body().getMessage());
                        }
                    }
                }
                @Override
                public void
                onFailure(Call<CustomerResponse> call, Throwable t) {
//                    showLoading(false);
                    Log.e("Error Retrofit", "onFailure: " + t.getMessage());
                }
            });
        }

    }
//    private void showLoading(Boolean isLoading) {
//        if (isLoading) {
//            progressBar.setVisibility(View.VISIBLE);
//        } else {
//            progressBar.setVisibility(View.GONE);
//        }
//    }

}
