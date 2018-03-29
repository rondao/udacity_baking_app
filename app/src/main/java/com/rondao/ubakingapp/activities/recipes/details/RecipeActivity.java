package com.rondao.ubakingapp.activities.recipes.details;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rondao.ubakingapp.R;
import com.rondao.ubakingapp.activities.recipes.steps.details.StepActivity;
import com.rondao.ubakingapp.activities.recipes.steps.details.StepDescriptionFragment;
import com.rondao.ubakingapp.activities.recipes.steps.details.StepVideoFragment;
import com.rondao.ubakingapp.data.model.Recipe;
import com.rondao.ubakingapp.data.model.RecipeStep;

import org.parceler.Parcels;

public class RecipeActivity extends AppCompatActivity implements RecipeFragment.RecipeFragmentListener {
    public static final String EXTRA_RECIPE = "recipe";
    public static final String RECIPE_STEP_STATE = "recipe_step_state";

    private Recipe mRecipe;
    private RecipeStep mRecipeStep;

    private StepVideoFragment mStepVideoFragment;
    private StepDescriptionFragment mStepDescriptionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_activity);

        mRecipe = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_RECIPE));
        getSupportActionBar().setTitle(mRecipe.getName());

        if (savedInstanceState == null) {
            mRecipeStep = mRecipe.getSteps().get(0);
            initFragments();
        } else {
            mRecipeStep = Parcels.unwrap(savedInstanceState.getParcelable(RECIPE_STEP_STATE));
            retrieveExistingFragment();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(RECIPE_STEP_STATE, Parcels.wrap(mRecipeStep));
        super.onSaveInstanceState(outState);
    }

    public void initFragments() {
        RecipeFragment recipeFragment = new RecipeFragment();
        recipeFragment.setArguments(getIntent().getExtras());

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                .add(R.id.recipe_fragment, recipeFragment);

        if (findViewById(R.id.master_detail_layout) != null) {
            mStepVideoFragment = StepVideoFragment.newInstance(mRecipeStep);
            mStepDescriptionFragment = StepDescriptionFragment.newInstance(mRecipeStep);

            transaction.add(R.id.step_video_fragment, mStepVideoFragment)
                    .add(R.id.step_description_fragment, mStepDescriptionFragment);
        }

        transaction.commit();
    }

    public void retrieveExistingFragment() {
        if (findViewById(R.id.master_detail_layout) != null) {
            mStepVideoFragment = (StepVideoFragment) getSupportFragmentManager().findFragmentById(R.id.step_video_fragment);
            mStepDescriptionFragment = (StepDescriptionFragment) getSupportFragmentManager().findFragmentById(R.id.step_description_fragment);

            Bundle args = new Bundle();
            args.putParcelable(StepActivity.EXTRA_STEP, Parcels.wrap(mRecipeStep));

            mStepVideoFragment.setArguments(args);
            mStepDescriptionFragment.setArguments(args);
        }
    }

    public void onStepClicked(RecipeStep step) {
        mRecipeStep = step;

        if (findViewById(R.id.master_detail_layout) != null) {
            mStepVideoFragment.setRecipeStep(mRecipeStep);
            mStepDescriptionFragment.setRecipeStep(mRecipeStep);
        } else {
            Intent intent = new Intent(this, StepActivity.class);
            intent.putExtra(StepActivity.EXTRA_STEP, Parcels.wrap(mRecipeStep));
            intent.putExtra(StepActivity.EXTRA_STEPS, Parcels.wrap(mRecipe.getSteps()));
            startActivity(intent);
        }
    }
}