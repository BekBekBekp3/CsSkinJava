package com.example.csskinsjava;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SkinService {
    @GET("skins")
    Call<List<Skin>> getAllSkins();

    @GET("skins/{id}")
    Call<Skin> getSkin(@Path("id") int id);
}
