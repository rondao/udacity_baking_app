package com.rondao.ubakingapp.activities.recipes.steps.details;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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

    public static final String TAG_VIDEO_FRAGMENT = "video";
    public static final String TAG_DESCRIPTION_FRAGMENT = "description";
    public static final String TAG_NAVIGATION_FRAGMENT = "navigation";

    private StepVideoFragment mStepVideoFragment;
    private StepDescriptionFragment mStepDescriptionFragment;
    private StepNavigationFragment mStepNavigationFragment;

    @State int mCurrentStep;
    private List<RecipeStep> mListSteps;

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

            createFragments();
         }
         initFragments();
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

    public void createFragments() {
        mStepVideoFragment = new StepVideoFragment();
        mStepDescriptionFragment = new StepDescriptionFragment();
        mStepNavigationFragment = new StepNavigationFragment();

        getSupportFragmentManager().beginTransaction()
                .add(mStepVideoFragment, TAG_VIDEO_FRAGMENT)
                .add(mStepDescriptionFragment, TAG_DESCRIPTION_FRAGMENT)
                .add(mStepNavigationFragment, TAG_NAVIGATION_FRAGMENT)
                .commit();
    }

    public void initFragments() {
        mStepVideoFragment = getFragmentIfExists(TAG_VIDEO_FRAGMENT, mStepVideoFragment);
        mStepDescriptionFragment = getFragmentIfExists(TAG_DESCRIPTION_FRAGMENT, mStepDescriptionFragment);
        mStepNavigationFragment = getFragmentIfExists(TAG_NAVIGATION_FRAGMENT, mStepNavigationFragment);

        Bundle args = new Bundle();
        args.putParcelable(EXTRA_STEP, Parcels.wrap(mListSteps.get(mCurrentStep)));

        mStepVideoFragment.setArguments(args);
        mStepDescriptionFragment.setArguments(args);
        mStepNavigationFragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (findViewById(R.id.step_video_fragment) != null) {
            transaction.replace(R.id.step_video_fragment, mStepVideoFragment);
        }
        if (findViewById(R.id.step_description_fragment) != null) {
            transaction.replace(R.id.step_description_fragment, mStepDescriptionFragment);
        }
        if (findViewById(R.id.step_navigation_fragment) != null) {
            transaction.replace(R.id.step_navigation_fragment, mStepNavigationFragment);
        }
        transaction.commit();
    }

    private <T extends Fragment> T getFragmentIfExists(String tag, T fragment) {
        T savedFragment = (T) getSupportFragmentManager().findFragmentByTag(tag);
        if (savedFragment != null) {
            fragment = savedFragment;
        }
        return fragment;
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
