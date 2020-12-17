package Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;
import Strengths.Strengths;
import dk.tec.velfaerdsapp.ModPage;
import dk.tec.velfaerdsapp.R;

import static android.content.Context.MODE_PRIVATE;

public class VideoAdapter {

    private static final String TAG = "QuestionsAdapter";

    public static ArrayList<Strengths> strengths;
    private Context mContext;
    int Raw;
    SimpleExoPlayer exoplayer;
    PlayerView playerView;
    ImageView fullscreenButton;
    boolean fullscreen = false;
    FrameLayout.LayoutParams paramsNotFullscreen;


    public VideoAdapter(Context context, int video, PlayerView pView){
        mContext = context;
        Raw = video;
        playerView = pView;


    }



    public void play() {


        //Videoplayer initialisation and binding to video file.
        exoplayer = ExoPlayerFactory.newSimpleInstance(mContext, new DefaultTrackSelector());
        DefaultDataSourceFactory defaultDataSourceFactory = new DefaultDataSourceFactory(mContext, Util.getUserAgent(mContext, "velfaerdsapp"));
        ExtractorMediaSource extractorMediaSource = new ExtractorMediaSource.Factory(defaultDataSourceFactory).createMediaSource(RawResourceDataSource.buildRawResourceUri(Raw));
        exoplayer.prepare(extractorMediaSource);
        playerView.setPlayer(exoplayer);
        playerView.setKeepScreenOn(true);

        //Pause on initialise
        exoplayer.setPlayWhenReady(false);


        exoplayer.addListener(new Player.EventListener() {
            @Override
            public void onTimelineChanged(Timeline timeline, Object manifest, int reason) {

            }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

            }

            @Override
            public void onLoadingChanged(boolean isLoading) {

            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {


            }

            @Override
            public void onRepeatModeChanged(int repeatMode) {

            }

            @Override
            public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {

            }

            @Override
            public void onPositionDiscontinuity(int reason) {

            }

            @Override
            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

            }

            @Override
            public void onSeekProcessed() {

            }
        });

       /* fullscreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (fullscreen) {
                    fullscreenButton.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_fullscreen_open));
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    fullscreen = false;
                } else {
                    fullscreenButton.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_fullscreen_close));
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    fullscreen = true;
                }
            }
        });*/

    }
}
