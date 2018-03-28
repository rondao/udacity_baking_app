package com.rondao.ubakingapp.activities.recipes.steps.details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.rondao.ubakingapp.R;
import com.rondao.ubakingapp.data.model.RecipeStep;

import org.parceler.Parcels;

import java.util.List;

public class StepActivity extends AppCompatActivity implements StepNavigationFragment.StepNavigationListener {
    public static final String EXTRA_STEP = "step";
    public static final String EXTRA_STEPS = "steps";

    private int mCurrentStep;
    private List<RecipeStep> mListSteps;

    private StepFragment mStepFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_activity);

        RecipeStep recipe = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_STEP));
        mListSteps = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_STEPS));

        mCurrentStep = mListSteps.indexOf(recipe);

        if (savedInstanceState == null) {
            mStepFragment = new StepFragment();
            StepNavigationFragment stepNavigationFragment = new StepNavigationFragment();

            mStepFragment.setArguments(getIntent().getExtras());
            stepNavigationFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.step_fragment, mStepFragment)
                    .add(R.id.step_navigation_fragment, stepNavigationFragment)
                    .commit();
        }
    }

    @Override
    public void onPrevious() {
        if (mCurrentStep > 0) {
            mStepFragment.setRecipeStep(mListSteps.get(--mCurrentStep));
        }
    }

    @Override
    public void onNext() {
        if (mCurrentStep < mListSteps.size() - 1) {
            mStepFragment.setRecipeStep(mListSteps.get(++mCurrentStep));
        }
    }
}
