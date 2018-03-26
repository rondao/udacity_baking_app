package com.rondao.ubakingapp.activities.recipes.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rondao.ubakingapp.R;
import com.rondao.ubakingapp.activities.recipes.steps.details.StepActivity;
import com.rondao.ubakingapp.data.model.Ingredient;
import com.rondao.ubakingapp.data.model.Recipe;
import com.rondao.ubakingapp.data.model.RecipeStep;
import com.rondao.ubakingapp.util.GenericAdapter;

import org.parceler.Parcels;

public class RecipeFragment extends Fragment {
    private GenericAdapter<Ingredient> mIngredientAdapter;
    private GenericAdapter<RecipeStep> mStepAdapter;

    private View mView;
    private Recipe mRecipe;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.recipe_fragment, container, false);

        mRecipe = Parcels.unwrap(getArguments().getParcelable(RecipeActivity.EXTRA_RECIPE));

        initIngredientRecyclerView();
        initStepRecyclerView();

        return mView;
    }

    private void initIngredientRecyclerView() {
        RecyclerView recyclerView = mView.findViewById(R.id.rv_ingredients);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        mIngredientAdapter = new GenericAdapter(R.layout.ingredient_item);
        mIngredientAdapter.setDataSet(mRecipe.getIngredients());

        recyclerView.setAdapter(mIngredientAdapter);
    }

    private void initStepRecyclerView() {
        RecyclerView recyclerView = mView.findViewById(R.id.rv_steps);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        mStepAdapter = new GenericAdapter(R.layout.step_item, new GenericAdapter.ListItemClickListener<RecipeStep>() {
            @Override
            public void onListItemClick(RecipeStep obj) {
                showStepDetails(obj);
            }
        });
        mStepAdapter.setDataSet(mRecipe.getSteps());

        recyclerView.setAdapter(mStepAdapter);
    }

    public void showStepDetails(RecipeStep step) {
        Intent intent = new Intent(getContext(), StepActivity.class);
        intent.putExtra(StepActivity.EXTRA_STEP, Parcels.wrap(step));
        intent.putExtra(StepActivity.EXTRA_STEPS, Parcels.wrap(mRecipe.getSteps()));
        startActivity(intent);
    }
}
