package com.rondao.ubakingapp.activities.recipes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.rondao.ubakingapp.R;
import com.rondao.ubakingapp.data.model.Recipe;
import com.rondao.ubakingapp.data.source.RecipesDataSource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipesActivity extends AppCompatActivity {
    private RecipeAdapter mRecipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        initRecipesRecyclerView();
        RecipesDataSource.getRecipes(new Callback<List<Recipe>>() {
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

    private void initRecipesRecyclerView() {
        RecyclerView mRecyclerView = findViewById(R.id.rv_recipes);

        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        mRecyclerView.setHasFixedSize(true);

        mRecipeAdapter = new RecipeAdapter();
        mRecyclerView.setAdapter(mRecipeAdapter);
    }
}
