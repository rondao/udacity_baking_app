package com.rondao.ubakingapp.activities.recipes.details;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rondao.ubakingapp.R;

public class DetailsActivity extends AppCompatActivity {
    public static final String EXTRA_RECIPE = "recipe";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        if (savedInstanceState == null) {
            DetailsFragment detailsFragment = new DetailsFragment();
            detailsFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_details, detailsFragment).commit();
        }
    }
}