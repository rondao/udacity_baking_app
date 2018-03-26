package com.rondao.ubakingapp.activities.recipes.steps.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rondao.ubakingapp.R;
import com.rondao.ubakingapp.data.model.RecipeStep;

import org.parceler.Parcels;

import java.util.List;

public class StepNavigationFragment extends Fragment {

    private List<RecipeStep> mSteps;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mSteps = Parcels.unwrap(getArguments().getParcelable(StepActivity.EXTRA_STEPS));

        for (RecipeStep s : mSteps) {
            Log.e("RONDAO", s.getShortDescription());
        }

        return inflater.inflate(R.layout.step_navigation_fragment, container, false);
    }
}
