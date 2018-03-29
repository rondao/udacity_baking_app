package com.rondao.ubakingapp.activities.recipes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rondao.ubakingapp.R;
import com.rondao.ubakingapp.activities.recipes.details.RecipeActivity;
import com.rondao.ubakingapp.data.model.Recipe;
import com.rondao.ubakingapp.util.GenericAdapter;

import org.parceler.Parcels;

import java.util.List;

public class RecipesActivity extends AppCompatActivity implements RecipesContract.View {
    private final RecipesContract.Presenter mPresenter = new RecipesPresenter(this);
    private GenericAdapter<Recipe> mRecipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipes_activity);

        initRecipesRecyclerView();
        mPresenter.loadRecipes();
    }

    private void initRecipesRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.rv_recipes);

        int gridColumns = getResources().getBoolean(R.bool.isTablet) ? 2 : 1;

        recyclerView.setLayoutManager(new GridLayoutManager(this, gridColumns));
        recyclerView.setHasFixedSize(true);

        mRecipeAdapter = new GenericAdapter(R.layout.recipe_card, new GenericAdapter.ListItemClickListener<Recipe>() {
            @Override
            public void onListItemClick(Recipe obj) {
                mPresenter.onRecipeClicked(obj);
            }
        });
        recyclerView.setAdapter(mRecipeAdapter);
    }

    @Override
    public void onLoadRecipes(List<Recipe> recipes) {
        mRecipeAdapter.setDataSet(recipes);
    }

    @Override
    public void showRecipeDetails(Recipe recipe) {
        Intent intent = new Intent(this, RecipeActivity.class);
        intent.putExtra(RecipeActivity.EXTRA_RECIPE, Parcels.wrap(recipe));
        startActivity(intent);
    }
}
