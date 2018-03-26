package com.rondao.ubakingapp.activities.recipes.steps.details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.rondao.ubakingapp.R;

public class StepActivity extends AppCompatActivity {
    public static final String EXTRA_STEP = "step";
    public static final String EXTRA_STEPS = "steps";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.step_activity);

        if (savedInstanceState == null) {
            StepFragment stepFragment = new StepFragment();
            StepNavigationFragment stepNavigationFragment = new StepNavigationFragment();

            stepFragment.setArguments(getIntent().getExtras());
            stepNavigationFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.step_fragment, stepFragment)
                    .add(R.id.step_navigation_fragment, stepNavigationFragment)
                    .commit();
        }
    }
}
