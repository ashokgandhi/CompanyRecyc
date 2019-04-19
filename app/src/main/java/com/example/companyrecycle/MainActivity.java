package com.example.companyrecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.companyrecycle.ApiInterface.ApiInterface;
import com.example.companyrecycle.ApiManager.ApiClient;
import com.example.companyrecycle.ApiResponse.CyclerResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
        RecyclerView recycler;
        ApiInterface apiInterface;
        CyclerAdapter cyclerAdapter;
        List<CyclerResponse.User>responses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler = findViewById(R.id.recycler);

        getView();

    }

    private void getView() {
        apiInterface = ApiClient.createService(ApiInterface.class,"");
        Call<CyclerResponse>responseCall=apiInterface.view("view1");
        responseCall.enqueue(new Callback<CyclerResponse>() {
            @Override
            public void onResponse(Call<CyclerResponse> call, retrofit2.Response<CyclerResponse> response) {
                if (response.body().getStatus()==1) {
                    responses = response.body().getUsers();
                    recycler.setHasFixedSize(true);
                    recycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    cyclerAdapter=new CyclerAdapter(MainActivity.this, (ArrayList<CyclerResponse.User>) responses);
                    recycler.setAdapter(cyclerAdapter);
                }
            }

            @Override
            public void onFailure(Call<CyclerResponse> call, Throwable t) {

                Toast.makeText(MainActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();

            }


        });
    }
}
