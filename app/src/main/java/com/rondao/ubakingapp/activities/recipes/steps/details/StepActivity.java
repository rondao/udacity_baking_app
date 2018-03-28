package com.rondao.ubakingapp.activities.recipes.steps.details;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.rondao.ubakingapp.R;
import com.rondao.ubakingapp.data.model.RecipeStep;
import com.rondao.ubakingapp.databinding.StepActivityBinding;

import org.parceler.Parcels;

import java.util.List;

import icepick.Icepick;
import icepick.State;

public class StepActivity extends AppCompatActivity implements StepNavigationFragment.StepNavigationListener {
    public static final String EXTRA_STEP = "step";
    public static final String EXTRA_STEPS = "steps";

    private StepActivityBinding mBinding;

    @State int mCurrentStep;
    private List<RecipeStep> mListSteps;

    private StepFragment mStepFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.step_activity);

        Icepick.restoreInstanceState(this, savedInstanceState);

        mListSteps = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_STEPS));

        if (savedInstanceState == null) {
            RecipeStep recipe = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_STEP));
            mCurrentStep = mListSteps.indexOf(recipe);

            mStepFragment = new StepFragment();
            StepNavigationFragment stepNavigationFragment = new StepNavigationFragment();

            mStepFragment.setArguments(getIntent().getExtras());
            stepNavigationFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.step_fragment, mStepFragment)
                    .add(R.id.step_navigation_fragment, stepNavigationFragment)
                    .commit();
         } else {
            mStepFragment = (StepFragment) getSupportFragmentManager().findFragmentById(R.id.step_fragment);
            Bundle args = new Bundle();
            args.putParcelable(EXTRA_STEP, Parcels.wrap(mListSteps.get(mCurrentStep)));
            mStepFragment.setArguments(args);
        }

        mBinding.setObj(mListSteps.get(mCurrentStep));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Icepick.saveInstanceState(this, outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onPrevious() {
        if (mCurrentStep > 0) {
            mStepFragment.setRecipeStep(mListSteps.get(--mCurrentStep));
            mBinding.setObj(mListSteps.get(mCurrentStep));
        }
    }

    @Override
    public void onNext() {
        if (mCurrentStep < mListSteps.size() - 1) {
            mStepFragment.setRecipeStep(mListSteps.get(++mCurrentStep));
            mBinding.setObj(mListSteps.get(mCurrentStep));
        }
    }
}
