package dk.tec.velfaerdsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.MediaController;
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
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

public class introPage extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private float x1, x2;
    private static int MIN_DISTANCE = 100;
    private GestureDetector gestureDetector;
    private TextView showText;
    private EditText enterName;
    private EditText enterJob;
    private VideoView videoView;
    String selectedItem = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_page);

        // instantiating
        enterName = findViewById(R.id.editTextName);
        enterJob = findViewById(R.id.editTextJob);
        videoView = findViewById(R.id.videoView);

        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.styrker_video;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        videoView.setFocusable(true);
        // Calling the method to create the spinnerdropdown
        createSpinnerDropdown();

        //init gestureDetector
        this.gestureDetector = new GestureDetector(introPage.this, this);
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
}