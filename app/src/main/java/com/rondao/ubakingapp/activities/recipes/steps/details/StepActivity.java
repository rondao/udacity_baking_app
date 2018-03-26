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

    private RecipeStep mCurrentStep;
    private List<RecipeStep> mListSteps;

    private StepFragment mStepFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_activity);

        mCurrentStep = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_STEP));
        mListSteps = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_STEPS));

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
        int previousStep = mCurrentStep.getId() - 1;
        if (previousStep >= 0) {
            mCurrentStep = mListSteps.get(previousStep);
            mStepFragment.setRecipeStep(mCurrentStep);
        }
    }

    @Override
    public void onNext() {
        int nextStep = mCurrentStep.getId() + 1;
        if (nextStep < mListSteps.size()) {
            mCurrentStep = mListSteps.get(nextStep);
            mStepFragment.setRecipeStep(mCurrentStep);
        }
    }
}
