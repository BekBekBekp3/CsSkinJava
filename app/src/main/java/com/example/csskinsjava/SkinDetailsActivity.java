package com.example.csskinsjava;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.csskinsjava.Skin;
import com.example.csskinsjava.SkinService;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SkinDetailsActivity extends AppCompatActivity {
    private static final String TAG = "SkinDetailsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skin_details);

        // Get the selected skin id from the intent
        int selectedSkinId = getIntent().getIntExtra("selectedSkinId", 0); // Provide a default value

        // Call the API to get the details of the selected skin
        SkinService service = ApiServiceHelper.getSkinService();

        service.getSkin(selectedSkinId).enqueue(new Callback<Skin>() {
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

        // Call the delete method when the delete button is clicked
        findViewById(R.id.buttonDelete).setOnClickListener(view -> {
            ApiServiceHelper.deleteSkin(selectedSkinId, SkinDetailsActivity.this);
        });

        // Call the update method when the update button is clicked
        findViewById(R.id.buttonUpdate).setOnClickListener(view -> {
            // Assuming you have an updated skin object
            Skin updatedSkin = getUpdatedSkin(); // Implement this method to get the updated skin details
            ApiServiceHelper.updateSkin(selectedSkinId, updatedSkin, SkinDetailsActivity.this);
        });
    }

    // Method to display skin details
    private void displaySkinDetails(Skin skin) {
        if (skin != null) {
            TextView textViewDetails = findViewById(R.id.textViewDetails);
            TextView textViewStatTrak = findViewById(R.id.textViewStatTrak);

            StringBuilder detailsBuilder = new StringBuilder();
            detailsBuilder.append("Name: ").append(skin.getName()).append("\n")
                    .append("Category: ").append(skin.getCategory()).append("\n")
                    .append("Skin Float: ").append(skin.getSkinFloat()).append("\n")
                    .append("Rarity: ").append(skin.getRarity()).append("\n")
                    .append("Quality: ").append(skin.getQuality()).append("\n")
                    .append("Image URL: ").append(skin.getImageURL()).append("\n");

            Boolean statTrak = skin.getStatTrak();
            if (statTrak != null) {
                detailsBuilder.append("StatTrak: ").append(statTrak ? "true" : "false").append("\n");
            } else {
                detailsBuilder.append("StatTrak: N/A\n");
            }

            textViewDetails.setText(detailsBuilder.toString());

            ImageView imageViewSkin = findViewById(R.id.imageViewSkin);
            if (skin.getImageURL() != null) {
                Picasso.get().load(skin.getImageURL()).into(imageViewSkin);
            } else {
                // Handle the case where imageURL is null
            }
        }
    }

    // Method to get the updated skin details
    private Skin getUpdatedSkin() {
        // Implement this method to get the updated skin details from your activity's UI components
        return null; // Replace null with the actual updated skin object
    }
}