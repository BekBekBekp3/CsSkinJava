package com.example.csskinsjava;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String BASE_URL = "http://192.168.1.99:8080/api/";
    private List<Skin> skinList;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SkinService service = retrofit.create(SkinService.class);

        Call<List<Skin>> call = service.getAllSkins();
        call.enqueue(new Callback<List<Skin>>() {
            @Override
            public void onResponse(Call<List<Skin>> call, Response<List<Skin>> response) {
                if (response.isSuccessful()) {
                    skinList = response.body();
                    populateSpinner();
                    Log.d(TAG, "Response: " + skinList.toString());
                } else {
                    Log.e(TAG, "Failed to get skins. Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Skin>> call, Throwable t) {
                Log.e(TAG, "Failed to get skins. Error: " + t.getMessage());
            }
        });
    }

    private void populateSpinner() {
        List<String> skinNames = new ArrayList<>();
        for (Skin skin : skinList) {
            skinNames.add(skin.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, skinNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

}