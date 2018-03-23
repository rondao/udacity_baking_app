package com.rondao.ubakingapp.view.recipes;

import com.rondao.ubakingapp.R;
import com.rondao.ubakingapp.model.Recipe;
import com.rondao.ubakingapp.util.GenericAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends GenericAdapter {
    private List<Recipe> mRecipeData;

    public RecipeAdapter() {
        mRecipeData = new ArrayList<>(0);
    }

    public void setRecipeData(List<Recipe> mRecipeData) {
        this.mRecipeData = mRecipeData;
        notifyDataSetChanged();
    }

    @Override
    protected Object getObjForPosition(int position) {
        return mRecipeData.get(position);
    }

    @Override
    protected int getLayoutIdForPosition(int position) {
        return R.layout.recipe_card;
    }

    @Override
    public int getItemCount() {
        return mRecipeData.size();
    }
}
