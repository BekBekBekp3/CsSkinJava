package com.example.csskinsjava;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceHelper {
    private static final String BASE_URL = "http://192.168.1.99:8080/api/";

    // Method to get SkinService instance
    public static SkinService getSkinService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(SkinService.class);
    }

    // Method to delete a skin item
    public static void deleteSkin(int skinId, Context context) {
        SkinService service = getSkinService();

        Call<Void> call = service.deleteSkin(skinId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Skin item deleted successfully
                    Toast.makeText(context, "Skin item deleted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // Error deleting skin item
                    Toast.makeText(context, "Failed to delete skin item", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Error making network call
                Toast.makeText(context, "Failed to delete skin item", Toast.LENGTH_SHORT).show();
                Log.e("ApiServiceHelper", "Failed to delete skin item", t);
            }
        });
    }

    // Method to update a skin item
    public static void updateSkin(int skinId, Skin updatedSkin, Context context) {
        SkinService service = getSkinService();

        Call<Void> call = service.updateSkin(skinId, updatedSkin);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Skin item updated successfully
                    Toast.makeText(context, "Skin item updated successfully", Toast.LENGTH_SHORT).show();
                } else {
                    // Error updating skin item
                    Toast.makeText(context, "Failed to update skin item", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Error making network call
                Toast.makeText(context, "Failed to update skin item", Toast.LENGTH_SHORT).show();
                Log.e("ApiServiceHelper", "Failed to update skin item", t);
            }
        });
    }

    // Add more methods for other API calls as needed
}
