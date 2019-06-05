package com.aymanshehri.bakingapp.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aymanshehri.bakingapp.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;

public class VideoOrImageFragment extends Fragment {
    private String videoUrl;
    private SimpleExoPlayer mExoPlayer;
    private SimpleExoPlayerView mPlayerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_or_image,container,false);

        videoUrl = getArguments().getString("video");
        String imageUrl = getArguments().getString("image");

        if(!videoUrl.isEmpty()){
            mPlayerView = view.findViewById(R.id.pv_exo_player);
            initializePlayer(Uri.parse(videoUrl));
            mPlayerView.setVisibility(View.VISIBLE);

        }else if(!imageUrl.isEmpty()){
            ImageView imageView = view.findViewById(R.id.iv_image);
            Picasso.with(getActivity()).load(imageUrl).into(imageView);
            imageView.setVisibility(View.VISIBLE);
        }else {
            TextView textView = view.findViewById(R.id.tv_no_video_or_image);
            textView.setVisibility(View.VISIBLE);
        }




        return view;
    }

    private void initializePlayer(Uri parse) {
        if(mExoPlayer == null){
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector, loadControl);
            mPlayerView.setPlayer(mExoPlayer);
            String userAgent = Util.getUserAgent(getActivity(),"Baking App");
            MediaSource mediaSource = new ExtractorMediaSource(parse, new DefaultDataSourceFactory(
                    getActivity(), userAgent), new DefaultExtractorsFactory(), null , null);
            mExoPlayer.prepare(mediaSource);
            mExoPlayer.setPlayWhenReady(true);

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        releasePlayer();
    }

    private void releasePlayer() {
        mExoPlayer.stop();
        mExoPlayer.release();
        mExoPlayer = null;
    }

    //todo: release the player
}
