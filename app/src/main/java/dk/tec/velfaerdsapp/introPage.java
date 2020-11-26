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

    private static final String TAG = "introPage";
    //Initialising
    private EditText enterName;
    private EditText enterJob;


    String selectedItem = "";
    FrameLayout.LayoutParams paramsNotFullscreen;
    Spinner spinnerGender;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Binding to ID's
        setContentView(R.layout.activity_intro_page);
        enterName = findViewById(R.id.editTextName);
        enterJob = findViewById(R.id.editTextJob);
        //playerView = (PlayerView) findViewById(R.id.player_view);
        spinnerGender = findViewById(R.id.spinnerGender);


        //Insert specific data stored in sharedPrefs
        SharedPreferences sharedPreferences = getSharedPreferences("introValues", MODE_PRIVATE);
        String s1 = sharedPreferences.getString("gName", "");
        String s2 = sharedPreferences.getString("gJob", "");
        //int s3 = sharedPreferences.getInt("gGender", -1);

        enterName.setText(s1);
        enterJob.setText(s2);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.køn_array, R.layout.spinner_item_nomargin);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerGender.setAdapter(
                new NothingSelectedSpinnerAdapter(
                        adapter,
                        R.layout.contact_spinner_row_nothing_selected,
                        this));


    }

    @Override
    protected void onPause() {
        super.onPause();
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
}