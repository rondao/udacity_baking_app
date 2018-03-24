package com.rondao.ubakingapp.data.source;

import com.rondao.ubakingapp.data.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class RecipesDataSource {
    public static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static void getRecipes(Callback<List<Recipe>> callback) {
        RecipesDataSource.ApiInterface apiService =
                RecipesDataSource.getClient().create(RecipesDataSource.ApiInterface.class);

        apiService.getBakingRecipes().enqueue(callback);
    }

    public interface ApiInterface {
        @GET("baking.json")
        Call<List<Recipe>> getBakingRecipes();
    }
}
