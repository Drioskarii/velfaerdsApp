package dk.tec.velfaerdsapp;

import Adapter.SpinnerAdapter;
import Adapter.AvatarAdapter;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;

public class IntroPage extends TouchActivityHandler {

    private static final String TAG = "IntroPage";
    //Initialising
    private EditText enterName;
    private EditText enterJob;
    private ArrayList<String> mNames1 = new ArrayList<>();
    private ArrayList<String> mNames2 = new ArrayList<>();
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls1 = new ArrayList<>();
    private ArrayList<String> mImageUrls2 = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    AnimationDrawable animation;
    ImageView characterPlaceholder;

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
        spinnerGender = findViewById(R.id.spinnerGender);

        //Insert specific data stored in sharedPrefs
        SharedPreferences sharedPreferences = getSharedPreferences("introValues", MODE_PRIVATE);
        String s1 = sharedPreferences.getString("gName", "");
        String s2 = sharedPreferences.getString("gJob", "");
        enterName.setText(s1);
        enterJob.setText(s2);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.køn_array, R.layout.spinner_item_nomargin);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(
                new SpinnerAdapter(
                        adapter,
                        R.layout.contact_spinner_row_nothing_selected,
                        this));

        characterPlaceholder = findViewById(R.id.characterPlaceholder);
        characterPlaceholder.setBackgroundResource(R.drawable.animation);
        animation = (AnimationDrawable) characterPlaceholder.getBackground();

        getImages();

        spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                System.out.println("Positionen er: " + position);
                initRecyclerView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

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

    public class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            System.out.println("Positionen er:" + position);
            selectedItem = parent.getItemAtPosition(position).toString();
            if (!selectedItem.equals("Vælg køn")) {
                Toast.makeText(parent.getContext(), "Køn valgt: " + selectedItem, Toast.LENGTH_LONG).show();

                initRecyclerView();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private void getImages() {
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mImageUrls1.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mNames.add("Trondheim");
        mNames1.add("Trondheim");

        mImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mImageUrls1.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mNames.add("Portugal");
        mNames1.add("Portugal");

        mImageUrls.add("https://i.redd.it/j6myfqglup501.jpg");
        mImageUrls1.add("https://i.redd.it/j6myfqglup501.jpg");
        mNames.add("Rocky Mountain National Park");
        mNames1.add("Rocky Mountain National Park");


        mImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mImageUrls2.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mNames.add("Mahahual");
        mNames2.add("Mahahual");

        mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg");
        mImageUrls2.add("https://i.redd.it/k98uzl68eh501.jpg");
        mNames.add("Frozen Lake");
        mNames2.add("Frozen Lake");


        mImageUrls.add("https://i.redd.it/glin0nwndo501.jpg");
        mImageUrls2.add("https://i.redd.it/glin0nwndo501.jpg");
        mNames.add("White Sands Desert");
        mNames2.add("White Sands Desert");


    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);

        int selectedID =  spinnerGender.getSelectedItemPosition();
        if(selectedID == 1){
            AvatarAdapter adapter = new AvatarAdapter(this, mNames1, mImageUrls1);
            recyclerView.setAdapter(adapter);
        } else if(selectedID == 2){
            AvatarAdapter adapter = new AvatarAdapter(this, mNames2, mImageUrls2);
            recyclerView.setAdapter(adapter);
        }else if(selectedID == 3){
            AvatarAdapter adapter = new AvatarAdapter(this, mNames, mImageUrls);
            recyclerView.setAdapter(adapter);
        } else{
            System.out.println("Nothing Selected");
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        animation.start();
    }
}



