package com.rondao.ubakingapp.view.recipes;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rondao.ubakingapp.R;
import com.rondao.ubakingapp.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    private List<Recipe> mRecipeData;

    public RecipeAdapter() {
        mRecipeData = new ArrayList<>(0);
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {
        public final TextView mRecipeName;
        public final TextView mRecipeServings;

        public RecipeViewHolder(View itemView) {
            super(itemView);
            mRecipeName = (TextView) itemView.findViewById(R.id.tv_recipe_name);
            mRecipeServings = (TextView) itemView.findViewById(R.id.tv_recipe_servings);
        }

        public void bind(Recipe recipe) {
            mRecipeName.setText(recipe.getName());
            mRecipeServings.setText(recipe.getServings());
        }
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.recipe_card, parent, false);
        return new RecipeViewHolder(view);
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
