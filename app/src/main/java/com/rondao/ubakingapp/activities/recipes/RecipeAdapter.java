package com.rondao.ubakingapp.activities.recipes;

import com.rondao.ubakingapp.R;
import com.rondao.ubakingapp.data.model.Recipe;
import com.rondao.ubakingapp.util.GenericAdapter;

import java.util.List;

public class RecipeAdapter extends GenericAdapter {
    private List<Recipe> mRecipeData;

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
        return mRecipeData == null ? 0 : mRecipeData.size();
    }
}
