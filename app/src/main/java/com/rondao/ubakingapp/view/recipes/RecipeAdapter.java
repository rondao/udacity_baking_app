package com.rondao.ubakingapp.view.recipes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.rondao.ubakingapp.databinding.RecipeCardBinding;
import com.rondao.ubakingapp.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    private List<Recipe> mRecipeData;

    public RecipeAdapter() {
        mRecipeData = new ArrayList<>(0);
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {
        private final RecipeCardBinding mRecipeCardBinding;

        public RecipeViewHolder(RecipeCardBinding binding) {
            super(binding.getRoot());
            mRecipeCardBinding = binding;
        }

        public void bind(Recipe recipe) {
            mRecipeCardBinding.setRecipe(recipe);
            mRecipeCardBinding.executePendingBindings();
        }
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new RecipeViewHolder(RecipeCardBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        Recipe recipe = mRecipeData.get(position);
        holder.bind(recipe);
    }

    @Override
    public int getItemCount() {
        return mRecipeData.size();
    }

    public void setRecipeData(List<Recipe> mRecipeData) {
        this.mRecipeData = mRecipeData;
        notifyDataSetChanged();
    }
}
