package com.rondao.ubakingapp.activities.recipes.steps.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rondao.ubakingapp.data.model.RecipeStep;
import com.rondao.ubakingapp.databinding.StepFragmentBinding;

import org.parceler.Parcels;

public class StepFragment extends Fragment {

    private RecipeStep mStep;
    private StepFragmentBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = StepFragmentBinding.inflate(inflater, container, false);

        setRecipeStep((RecipeStep) Parcels.unwrap(getArguments().getParcelable(StepActivity.EXTRA_STEP)));

        return mBinding.getRoot();
    }

    public void setRecipeStep(RecipeStep step) {
        mStep = step;
        mBinding.setObj(mStep);
    }
}
