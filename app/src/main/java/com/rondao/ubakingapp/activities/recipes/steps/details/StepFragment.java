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
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.rondao.ubakingapp.R;
import com.rondao.ubakingapp.data.model.RecipeStep;
import com.rondao.ubakingapp.util.DrawableUtils;

import org.parceler.Parcels;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class StepFragment extends Fragment {
    private Unbinder mUnbinder;

    private RecipeStep mStep;
    private View mView;

    @BindView(R.id.exo_step_video)
    SimpleExoPlayerView mPlayerView;
    private SimpleExoPlayer mExoPlayer;

    public static StepFragment newInstance() {
        return new StepFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            mView = inflater.inflate(R.layout.step_video_fragment, container, false);
            mUnbinder = ButterKnife.bind(this, mView);

            mPlayerView.setDefaultArtwork(DrawableUtils.getBitmapFromVectorDrawable(getContext(), R.drawable.ic_no_video));

            setRecipeStep((RecipeStep) Parcels.unwrap(getArguments().getParcelable(StepActivity.EXTRA_STEP)));
        }

        return mView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (!getActivity().isChangingConfigurations()) {
            releasePlayer();
            mUnbinder.unbind();
        }
    }

    public void setRecipeStep(RecipeStep step) {
        mStep = step;
        setMediaToPlayer(mStep.getVideoURL());
    }

    private void setMediaToPlayer(String videoUrl) {
        Context context = getContext();
        initializePlayer(context);

        if (videoUrl == null || videoUrl.isEmpty()) {
            mPlayerView.setUseArtwork(true);
            mPlayerView.setUseController(false);

            mExoPlayer.prepare(createEmptySource());
        } else {
            mPlayerView.setUseArtwork(false);
            mPlayerView.setUseController(true);

            mExoPlayer.prepare(new ExtractorMediaSource(
                    Uri.parse(videoUrl),
                    new DefaultDataSourceFactory(context, Util.getUserAgent(context, getActivity().getTitle().toString())),
                    new DefaultExtractorsFactory(),
                    null,
                    null));
        }
    }

    private MediaSource createEmptySource() {
        return new MediaSource() {
            @Override public void prepareSource(ExoPlayer player, boolean isTopLevelSource, Listener listener) {}
            @Override public void maybeThrowSourceInfoRefreshError() throws IOException {}
            @Override public MediaPeriod createPeriod(int index, Allocator allocator, long positionUs) {return null;}
            @Override public void releasePeriod(MediaPeriod mediaPeriod) {}
            @Override public void releaseSource() {}
        };
    }

    private void initializePlayer(Context context) {
        if (mExoPlayer == null) {
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(context, new DefaultTrackSelector(), new DefaultLoadControl());
            mExoPlayer.setPlayWhenReady(true);

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
