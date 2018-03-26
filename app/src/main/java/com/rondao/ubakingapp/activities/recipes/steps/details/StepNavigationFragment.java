package com.rondao.ubakingapp.activities.recipes.steps.details;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rondao.ubakingapp.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class StepNavigationFragment extends Fragment {
    StepNavigationListener mCallback;

    public interface StepNavigationListener {
        void onPrevious();
        void onNext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.step_navigation_fragment, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallback = (StepNavigationListener) context;
    }

    @OnClick(R.id.ib_navigation_left)
    public void onPrevious() {
        mCallback.onPrevious();
    }

    @OnClick(R.id.ib_navigation_right)
    public void onNext() {
        mCallback.onNext();
    }
}
