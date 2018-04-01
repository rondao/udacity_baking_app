package com.rondao.ubakingapp.activities.recipes.steps.details;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rondao.ubakingapp.R;
import com.rondao.ubakingapp.data.model.RecipeStep;
import com.rondao.ubakingapp.databinding.StepDescriptionFragmentBinding;

import org.parceler.Parcels;

public class StepDescriptionFragment extends Fragment {
    private StepDescriptionFragmentBinding mBinding;

    private RecipeStep mRecipeStep;

    public StepDescriptionFragment() {
        // Required empty public constructor
    }

    public static StepDescriptionFragment newInstance(RecipeStep recipeStep) {
        StepDescriptionFragment fragment = new StepDescriptionFragment();
        Bundle args = new Bundle();
        args.putParcelable(StepActivity.EXTRA_STEP, Parcels.wrap(recipeStep));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.step_description_fragment, container, false);

        if (getArguments() != null) {
            mRecipeStep = Parcels.unwrap(getArguments().getParcelable(StepActivity.EXTRA_STEP));
        }
        mBinding.setObj(mRecipeStep);

        return mBinding.getRoot();
    }

    public void setRecipeStep(RecipeStep recipeStep) {
        mRecipeStep = recipeStep;
        mBinding.setObj(mRecipeStep);
    }
}
