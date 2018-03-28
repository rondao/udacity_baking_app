package com.rondao.ubakingapp.activities.recipes.steps.details;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.rondao.ubakingapp.R;
import com.rondao.ubakingapp.data.model.RecipeStep;
import com.rondao.ubakingapp.databinding.StepFragmentBinding;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepFragment extends Fragment {

    private RecipeStep mStep;
    private StepFragmentBinding mBinding;

    @BindView(R.id.exo_step_video)
    SimpleExoPlayerView mPlayerView;
    private SimpleExoPlayer mExoPlayer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = StepFragmentBinding.inflate(inflater, container, false);
        ButterKnife.bind(this, mBinding.getRoot());

        setRecipeStep((RecipeStep) Parcels.unwrap(getArguments().getParcelable(StepActivity.EXTRA_STEP)));

        return mBinding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        releasePlayer();
    }

    public void setRecipeStep(RecipeStep step) {
        mStep = step;
        setMediaToPlayer(Uri.parse(mStep.getVideoURL()));
        mBinding.setObj(mStep);
    }

    private void setMediaToPlayer(Uri mediaUri) {
        Context context = getContext();
        initializePlayer(context);

        mExoPlayer.prepare(new ExtractorMediaSource(
                mediaUri,
                new DefaultDataSourceFactory(context, Util.getUserAgent(context, getActivity().getTitle().toString())),
                new DefaultExtractorsFactory(),
                null,
                null));

        mExoPlayer.setPlayWhenReady(true);

    }
    private void initializePlayer(Context context) {
        if (mExoPlayer == null) {
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(context, new DefaultTrackSelector(), new DefaultLoadControl());

            mPlayerView.setPlayer(mExoPlayer);
            mPlayerView.setBackgroundColor(Color.parseColor("BLACK"));
        }
    }


    private void releasePlayer() {
        mExoPlayer.stop();
        mExoPlayer.release();
        mExoPlayer = null;
    }
}
