package dk.tec.velfaerdsapp;

import Adapter.NothingSelectedSpinnerAdapter;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import android.widget.VideoView;
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

public class introPage extends touchActivityHandler {
    //Initialising
    private float x1, x2;
    private static int MIN_DISTANCE = 400;
    private GestureDetector gestureDetector;
    private EditText enterName;
    private EditText enterJob;


    String selectedItem = "";
    PlayerView playerView;
    ImageView fullscreenButton;
    SimpleExoPlayer exoplayer;
    boolean fullscreen = false;
    FrameLayout.LayoutParams paramsNotFullscreen;
    LinearLayout clickpage;
    Spinner spinnerGender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Binding to ID's
        setContentView(R.layout.activity_intro_page);
        enterName = findViewById(R.id.editTextName);
        enterJob = findViewById(R.id.editTextJob);
        playerView = (PlayerView) findViewById(R.id.player_view);
        fullscreenButton = findViewById(R.id.exo_fullscreen_icon);
        clickpage = findViewById(R.id.clickPage);
        spinnerGender = findViewById(R.id.spinnerGender);


        //Print any old data stored in sharedPrefs
        SharedPreferences sharedPreferences = getSharedPreferences("introValues", MODE_PRIVATE);
        String s1 = sharedPreferences.getString("gName", "");
        String s2 = sharedPreferences.getString("gJob", "");
        int s3 = sharedPreferences.getInt("gGender", 0);
        System.out.println(s3);
        // Mand = ID 1
        // Kvinde = ID 2
        // Andet = ID 3

        enterName.setText(s1);
        enterJob.setText(s2);
        spinnerGender.setSelection(s3);





        //Videoplayer initialisation and binding to video file.
        exoplayer = ExoPlayerFactory.newSimpleInstance(this, new DefaultTrackSelector());
        DefaultDataSourceFactory defaultDataSourceFactory = new DefaultDataSourceFactory(this, Util.getUserAgent(this, "velfaerdsapp"));
        ExtractorMediaSource extractorMediaSource = new ExtractorMediaSource.Factory(defaultDataSourceFactory).createMediaSource(RawResourceDataSource.buildRawResourceUri(R.raw.styrker_video));
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

        this.gestureDetector = new GestureDetector(introPage.this, this);
        clickpage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
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
                return true;
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.spinnerGender);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.køn_array, R.layout.spinner_item_nomargin);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter,
                        R.layout.contact_spinner_row_nothing_selected,
                        this));


    }

    @Override
    protected void onPause() {
        super.onPause();
        exoplayer.setPlayWhenReady(false);
        exoplayer.getPlaybackState();

        //This garbage works that's pretty cool, this saves info in SharedPrefs so ye cool init fam
        //Make object.png
        String name = "" + enterName.getText();
        String job = "" + enterJob.getText();
        int selectedID = spinnerGender.getSelectedItemPosition();





        SharedPreferences sharedPref = getSharedPreferences("introValues", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        //Inset data into the SharedPreferences
        editor.putString("gName", name);
        editor.putString("gJob", job);
        editor.putInt("gGender" , selectedID);
        editor.apply();
    }


    @Override
    protected void onRestart() {
        super.onRestart();


    }

    public void forward() {
        Intent intent = new Intent(introPage.this, customAvatar.class);
        startActivity(intent);
    }

    public void backward() {

        finish();
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
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);


        //To fullscreen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            fullscreenButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_fullscreen_close));
            paramsNotFullscreen = (FrameLayout.LayoutParams) playerView.getLayoutParams();
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(paramsNotFullscreen);
            params.setMargins(0, 0, 0, 0);
            params.height = ViewGroup.LayoutParams.MATCH_PARENT;
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            //params.addRule(FrameLayout.CENTER_IN_PARENT);
            playerView.setLayoutParams(params);

        }

        //To portrait
        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            fullscreenButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_fullscreen_open));
            playerView.setLayoutParams(paramsNotFullscreen);
        }
    }
}