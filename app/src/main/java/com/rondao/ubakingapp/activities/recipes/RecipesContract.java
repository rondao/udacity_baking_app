package com.rondao.ubakingapp.activities.recipes;

import com.rondao.ubakingapp.data.model.Recipe;

import java.util.List;

public interface RecipesContract {
    interface Presenter {
        void loadRecipes();
        void onRecipeClicked(Recipe recipe);
    }
    interface View {
        void onLoadRecipes(List<Recipe> recipes);
        void showRecipeDetails(Recipe recipe);
    }
}
