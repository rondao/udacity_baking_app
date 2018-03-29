package com.rondao.ubakingapp.activities.recipes.steps.details;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.rondao.ubakingapp.R;
import com.rondao.ubakingapp.data.model.RecipeStep;

import org.parceler.Parcels;

import java.util.List;

import icepick.Icepick;
import icepick.State;

import static android.view.View.SYSTEM_UI_FLAG_FULLSCREEN;
import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;

public class StepActivity extends AppCompatActivity implements StepNavigationFragment.StepNavigationListener {
    public static final String EXTRA_STEP = "step";
    public static final String EXTRA_STEPS = "steps";

    @State int mCurrentStep;
    private List<RecipeStep> mListSteps;

    private StepVideoFragment mStepVideoFragment;
    private StepDescriptionFragment mStepDescriptionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_activity);
        Icepick.restoreInstanceState(this, savedInstanceState);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            hideUiInFullscreen();
        }

        mListSteps = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_STEPS));

        if (savedInstanceState == null) {
            RecipeStep recipe = Parcels.unwrap(getIntent().getParcelableExtra(EXTRA_STEP));
            mCurrentStep = mListSteps.indexOf(recipe);

            initFragments();
         } else {
            retrieveExistingFragment();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Icepick.saveInstanceState(this, outState);
        super.onSaveInstanceState(outState);
    }

    public void hideUiInFullscreen() {
        getWindow().getDecorView().setSystemUiVisibility(
                SYSTEM_UI_FLAG_FULLSCREEN | SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getSupportActionBar().hide();
    }

    public void initFragments() {
        mStepVideoFragment = new StepVideoFragment();
        mStepDescriptionFragment = StepDescriptionFragment.newInstance(mListSteps.get(mCurrentStep));
        StepNavigationFragment stepNavigationFragment = new StepNavigationFragment();

        mStepVideoFragment.setArguments(getIntent().getExtras());
        stepNavigationFragment.setArguments(getIntent().getExtras());

        getSupportFragmentManager().beginTransaction()
                .add(R.id.step_video_fragment, mStepVideoFragment)
                .add(R.id.step_description_fragment, mStepDescriptionFragment)
                .add(R.id.step_navigation_fragment, stepNavigationFragment)
                .commit();
    }

    public void retrieveExistingFragment() {
        mStepVideoFragment = (StepVideoFragment) getSupportFragmentManager().findFragmentById(R.id.step_video_fragment);
        mStepDescriptionFragment  = (StepDescriptionFragment) getSupportFragmentManager().findFragmentById(R.id.step_description_fragment);

        Bundle args = new Bundle();
        args.putParcelable(EXTRA_STEP, Parcels.wrap(mListSteps.get(mCurrentStep)));

        mStepVideoFragment.setArguments(args);
        mStepDescriptionFragment.setArguments(args);
    }

    @Override
    public void onPrevious() {
        if (mCurrentStep > 0) {
            mStepVideoFragment.setRecipeStep(mListSteps.get(--mCurrentStep));
            mStepDescriptionFragment.setRecipeStep(mListSteps.get(mCurrentStep));
        }
    }

    @Override
    public void onNext() {
        if (mCurrentStep < mListSteps.size() - 1) {
            mStepVideoFragment.setRecipeStep(mListSteps.get(++mCurrentStep));
            mStepDescriptionFragment.setRecipeStep(mListSteps.get(mCurrentStep));
        }
    }
}
