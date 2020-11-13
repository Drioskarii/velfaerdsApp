package dk.tec.velfaerdsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.os.IResultReceiver;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.widget.VideoView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;
import com.google.android.exoplayer2.util.Util;

public class introPage extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private float x1, x2;
    private static int MIN_DISTANCE = 400;
    private GestureDetector gestureDetector;
    private TextView showText;
    private EditText enterName;
    private EditText enterJob;
    private VideoView videoView;
    private static Context context;

    String selectedItem = "";
    PlayerView playerView;
    ImageView fullscreenButton;
    SimpleExoPlayer exoplayer;
    boolean fullscreen = false;
    FrameLayout.LayoutParams paramsNotFullscreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_page);
        enterName = findViewById(R.id.editTextName);
        enterJob = findViewById(R.id.editTextJob);
        playerView = (PlayerView)findViewById(R.id.player_view);
        fullscreenButton = findViewById(R.id.exo_fullscreen_icon);


       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
        //        , WindowManager.LayoutParams.FLAG_FULLSCREEN);



       // LoadControl loadControl = new DefaultLoadControl();
       // BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
       // TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));

       // exoplayer = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(introPage.this), new DefaultTrackSelector(), new DefaultLoadControl());

       // String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.styrker_video;
      //  Uri videoUrl = Uri.parse(videoPath);

        //DefaultHttpDataSourceFactory factory = new DefaultHttpDataSourceFactory("exoplayer_video");
       //ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
       //MediaSource mediaSource = new ExtractorMediaSource(videoUrl, factory, extractorsFactory, null, null);



       // playerView.setPlayer(exoplayer);
      //  playerView.setKeepScreenOn(true);
       // exoplayer.prepare(audioSource);
       // exoplayer.setPlayWhenReady(true);


        exoplayer= ExoPlayerFactory.newSimpleInstance(this,new DefaultTrackSelector());
        DefaultDataSourceFactory defaultDataSourceFactory=new DefaultDataSourceFactory(this, Util.getUserAgent(this,"velfaerdsapp"));
        exoplayer.setPlayWhenReady(true);
        ExtractorMediaSource extractorMediaSource = new ExtractorMediaSource.Factory(defaultDataSourceFactory).createMediaSource(RawResourceDataSource.buildRawResourceUri(R.raw.styrker_video));
        exoplayer.prepare(extractorMediaSource);
        playerView.setPlayer(exoplayer);
        playerView.setKeepScreenOn(true);
        exoplayer.setPlayWhenReady(true);



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

        fullscreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (fullscreen) {
                    fullscreenButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_fullscreen_open));
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    fullscreen = false;
                } else {
                    fullscreenButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_fullscreen_close));
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    fullscreen = true;
                }
            }
        });





        // videoView = findViewById(R.id.videoView);

       // String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.styrker_video;
        //Uri uri = Uri.parse(videoPath);
        //videoView.setVideoURI(uri);

       // MediaController mediaController = new MediaController(this);
       // videoView.setMediaController(mediaController);
      //  mediaController.setAnchorView(videoView);
       // videoView.setFocusable(true);
       // setVolumeControlStream(AudioManager.STREAM_MUSIC);
        // Calling the method to create the spinnerdropdown
       // createSpinnerDropdown();

        //init gestureDetector
        this.gestureDetector = new GestureDetector(introPage.this, this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        exoplayer.setPlayWhenReady(false);
        exoplayer.getPlaybackState();

    }


    @Override
    protected void onRestart() {
        super.onRestart();
        exoplayer.setPlayWhenReady(true);
        exoplayer.getPlaybackState();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        switch (event.getAction()) {
            //press
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;

            //lift
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                //horizontal swipe
                float valueX = x2 - x1;
                if (Math.abs(valueX) > MIN_DISTANCE) {
                    if (x2 > x1) {
                        backward();
                    } else {
                        forward();
                    }
                }
        }
        return super.onTouchEvent(event);
    }



    public void forward() {
        Intent intent = new Intent(introPage.this, customAvatar.class);

        startActivity(intent);
    }

    public void backward() {
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    // creating and populating the dropdown
    private void createSpinnerDropdown() {

        Spinner spinner = (Spinner) findViewById(R.id.spinnerGender);

        List<String> list = new ArrayList<>();
        list.add("Vælg køn");
        list.add("Mand");
        list.add("Kvinde");
        list.add("Andet");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the ArrayAdapter to the spinner
        spinner.setAdapter(dataAdapter);

        // Attaching the listener to the spinner
        spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());

    }

    public class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            selectedItem = parent.getItemAtPosition(position).toString();
            if (!selectedItem.equals("Vælg køn")) {
                Toast.makeText(parent.getContext(), "Køn valgt: " + selectedItem, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);



        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) //To fullscreen
        {
            paramsNotFullscreen=(FrameLayout.LayoutParams)playerView.getLayoutParams();
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(paramsNotFullscreen);
            params.setMargins(0, 0, 0, 0);
            params.height= ViewGroup.LayoutParams.MATCH_PARENT;
            params.width=ViewGroup.LayoutParams.MATCH_PARENT;
            //params.addRule(FrameLayout.CENTER_IN_PARENT);
            playerView.setLayoutParams(params);

        }
        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            playerView.setLayoutParams(paramsNotFullscreen);
        }
    }
}