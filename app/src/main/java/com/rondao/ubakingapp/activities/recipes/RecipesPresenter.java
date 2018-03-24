package com.rondao.ubakingapp.activities.recipes;

import android.util.Log;

import com.rondao.ubakingapp.data.model.Recipe;
import com.rondao.ubakingapp.data.source.RecipesDataSource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipesPresenter implements RecipesContract.Presenter {
    private final RecipesContract.View mView;

    public RecipesPresenter(RecipesContract.View view) {
        mView = view;
    }

    public void loadRecipes() {
        RecipesDataSource.getRecipes(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                mView.onLoadRecipes(response.body());
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.e(RecipesPresenter.class.getName(), t.toString());
            }
        });
    }

    @Override
    public void onRecipeClicked(Recipe recipe) {
        mView.showRecipeDetails(recipe);
    }
}
