package com.rondao.ubakingapp.activities.recipes.details;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rondao.ubakingapp.R;
import com.rondao.ubakingapp.activities.recipes.steps.details.StepActivity;
import com.rondao.ubakingapp.data.model.Recipe;
import com.rondao.ubakingapp.data.model.RecipeStep;

import org.parceler.Parcels;

public class RecipeActivity extends AppCompatActivity implements RecipeFragment.RecipeFragmentListener {
    public static final String EXTRA_RECIPE = "recipe";

    private Recipe mRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_activity);

        mRecipe = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_RECIPE));
        getSupportActionBar().setTitle(mRecipe.getName());

        if (savedInstanceState == null) {
            RecipeFragment recipeFragment = new RecipeFragment();
            recipeFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.recipe_fragment, recipeFragment).commit();
        }
    }

    public void onStepClicked(RecipeStep step) {
        Intent intent = new Intent(this, StepActivity.class);
        intent.putExtra(StepActivity.EXTRA_STEP, Parcels.wrap(step));
        intent.putExtra(StepActivity.EXTRA_STEPS, Parcels.wrap(mRecipe.getSteps()));
        startActivity(intent);
    }
}