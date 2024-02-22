package com.example.csskinsjava;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.csskinsjava.Skin;
import com.example.csskinsjava.SkinService;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SkinDetailsActivity extends AppCompatActivity {
    private static final String BASE_URL = "http://192.168.1.99:8080/api/";
    private static final String TAG = "SkinDetailsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin_details);

        // Get the selected skin id from the intent
        int selectedSkinId = getIntent().getIntExtra("selectedSkinId", 0); // Provide a default value

        // Call the API to get the details of the selected skin
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SkinService service = retrofit.create(SkinService.class);

        Call<Skin> call = service.getSkin(selectedSkinId);
        call.enqueue(new Callback<Skin>() {
            @Override
            public void onResponse(Call<Skin> call, Response<Skin> response) {
                if (response.isSuccessful()) {
                    Skin selectedSkin = response.body();
                    displaySkinDetails(selectedSkin);
                    Log.d(TAG, "Response: " + selectedSkin.toString()); // Log the response
                } else {
                    Log.e(TAG, "Failed to get skin details. Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Skin> call, Throwable t) {
                Log.e(TAG, "Failed to get skin details. Error: " + t.getMessage());
            }
        });
    }

    private void displaySkinDetails(Skin skin) {
        if (skin != null) {
            TextView textViewName = findViewById(R.id.textViewName);
            textViewName.setText("Name: " + skin.getName());

            // Display other details similarly
        }
    }
}