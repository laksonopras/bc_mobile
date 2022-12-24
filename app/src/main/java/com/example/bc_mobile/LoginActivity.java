package com.example.bc_mobile;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bc_mobile.api.APIConfig;
import com.example.bc_mobile.model.customer.Customer;
import com.example.bc_mobile.model.customer.CustomerAuth;
import com.example.bc_mobile.model.customer.CustomerResponse;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText etLoginEmail;
    EditText etLoginPassword;
    TextView tvRegisterHere;
    Button btnLogin;
    Integer customerAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(customerAuth != null){
            Intent();
        }

        etLoginEmail = findViewById(R.id.etLoginEmail);
        etLoginPassword = findViewById(R.id.etLoginPass);
        tvRegisterHere = findViewById(R.id.tvRegisterHere);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(view -> {
            loginUser();
        });
        tvRegisterHere.setOnClickListener(view ->{
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }

    private void loginUser(){
        String email = etLoginEmail.getText().toString();
        String password = etLoginPassword.getText().toString();

        if (TextUtils.isEmpty(email)){
            etLoginEmail.setError("Email tidak boleh kosong");
            etLoginEmail.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            etLoginPassword.setError("Password tidak boleh kosong");
            etLoginPassword.requestFocus();
        }else{
            Call<CustomerAuth> client = APIConfig.getApiService().login(email, password);
            client.enqueue(new Callback<CustomerAuth>() {
                @Override
                public void onResponse(Call<CustomerAuth> call, Response<CustomerAuth> response) {
//                    showLoading(false);
                    if (response.isSuccessful()){
                        if (response.body() != null){
                            if(response.body().isStatus() == true){
                                customerAuth = response.body().getAuth();
                                Intent();
                            } else{}
                            Toast.makeText(LoginActivity.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        if (response.body() != null) {
                            Log.e("", "onFailure: " + response.message());
                        }
                    }
                }
                @Override
                public void
                onFailure(Call<CustomerAuth> call, Throwable t) {
//                    showLoading(false);
                    Log.e("Error Retrofit", "onFailure: " + t.getMessage());
                }
            });
        }
    }

    public void Intent(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class );
        intent.putExtra("auth", String.valueOf(customerAuth));
        startActivity(intent);
    }
}
