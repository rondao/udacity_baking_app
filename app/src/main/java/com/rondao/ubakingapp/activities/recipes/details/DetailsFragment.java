package com.rondao.ubakingapp.activities.recipes.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rondao.ubakingapp.R;
import com.rondao.ubakingapp.data.model.Ingredient;
import com.rondao.ubakingapp.data.model.Recipe;
import com.rondao.ubakingapp.data.model.RecipeStep;
import com.rondao.ubakingapp.util.GenericAdapter;

import org.parceler.Parcels;

public class DetailsFragment extends Fragment {
    private GenericAdapter<Ingredient> mIngredientAdapter;
    private GenericAdapter<RecipeStep> mStepAdapter;

    private View mView;
    private Recipe mRecipe;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_details, container, false);

        mRecipe = Parcels.unwrap(getArguments().getParcelable(DetailsActivity.EXTRA_RECIPE));

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
                Log.e("RONDAO", obj.getShortDescription());
            }
        });
        mStepAdapter.setDataSet(mRecipe.getSteps());

        recyclerView.setAdapter(mStepAdapter);
    }
}
