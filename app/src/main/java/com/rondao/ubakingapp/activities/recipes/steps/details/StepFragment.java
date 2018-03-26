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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        StepFragmentBinding binding = StepFragmentBinding.inflate(inflater, container, false);

        mStep = Parcels.unwrap(getArguments().getParcelable(StepActivity.EXTRA_STEP));
        binding.setObj(mStep);

        return binding.getRoot();
    }
}
