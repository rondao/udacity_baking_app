package com.rondao.ubakingapp.view.recipes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.rondao.ubakingapp.R;
import com.rondao.ubakingapp.data.BakingApi;
import com.rondao.ubakingapp.model.Recipe;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipesActivity extends AppCompatActivity {
    private RecipeAdapter mRecipeAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_recipes);

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        mRecyclerView.setHasFixedSize(true);

        mRecipeAdapter = new RecipeAdapter();
        mRecyclerView.setAdapter(mRecipeAdapter);

        BakingApi.ApiInterface apiService =
                BakingApi.getClient().create(BakingApi.ApiInterface.class);

        Call<List<Recipe>> call = apiService.getBakingRecipes();
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                mRecipeAdapter.setRecipeData(response.body());
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.e(RecipesActivity.class.getName(), t.toString());
            }
        });
    }
}