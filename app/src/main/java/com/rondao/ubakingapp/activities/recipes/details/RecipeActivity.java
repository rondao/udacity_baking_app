package com.rondao.ubakingapp.activities.recipes.details;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rondao.ubakingapp.R;
import com.rondao.ubakingapp.data.model.Recipe;

import org.parceler.Parcels;

public class RecipeActivity extends AppCompatActivity {
    public static final String EXTRA_RECIPE = "recipe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_activity);

        Recipe recipe = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_RECIPE));
        getSupportActionBar().setTitle(recipe.getName());

        if (savedInstanceState == null) {
            RecipeFragment recipeFragment = new RecipeFragment();
            recipeFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.recipe_fragment, recipeFragment).commit();
        }
    }
}