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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
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
        //This garbage works that's pretty cool, this saves info in SharedPrefs
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

        mImageUrls.add("https://sigurdbarrett.dk/wp-content/uploads/2020/09/Syng-julen-ind-med-Sigurd-og-Bjornen-Bjorn-FORSIDE.jpg");
        mImageUrls1.add("https://sigurdbarrett.dk/wp-content/uploads/2020/09/Syng-julen-ind-med-Sigurd-og-Bjornen-Bjorn-FORSIDE.jpg");
        mNames.add("Sigurd");
        mNames1.add("Sigurd");

        mImageUrls.add("https://spejder.dk/sites/default/files/styles/page_width_16_9/public/laks_track.jpg?itok=VN5da_ab");
        mImageUrls1.add("https://spejder.dk/sites/default/files/styles/page_width_16_9/public/laks_track.jpg?itok=VN5da_ab");
        mNames.add("Lakserytteren");
        mNames1.add("Lakserytteren");

        mImageUrls.add("https://upload.wikimedia.org/wikipedia/commons/thumb/9/9f/Hr._Sk%C3%A6g_som_%C3%A5rets_%C3%A6resjulemand_2015.jpg/1920px-Hr._Sk%C3%A6g_som_%C3%A5rets_%C3%A6resjulemand_2015.jpg");
        mImageUrls1.add("https://upload.wikimedia.org/wikipedia/commons/thumb/9/9f/Hr._Sk%C3%A6g_som_%C3%A5rets_%C3%A6resjulemand_2015.jpg/1920px-Hr._Sk%C3%A6g_som_%C3%A5rets_%C3%A6resjulemand_2015.jpg");
        mNames.add("Hr. Skæg");
        mNames1.add("Hr. Skæg");


        mImageUrls.add("https://musikhuzet.dk/nonsec/Pix05/20121212114418.jpg.ashx?Width=760");
        mImageUrls2.add("https://musikhuzet.dk/nonsec/Pix05/20121212114418.jpg.ashx?Width=760");
        mNames.add("Katrine");
        mNames2.add("Katrine");

        mImageUrls.add("https://images.sn.dk/37/1327237_0_398_0_0_0_0_4.jpg");
        mImageUrls2.add("https://images.sn.dk/37/1327237_0_398_0_0_0_0_4.jpg");
        mNames.add("Mille");
        mNames2.add("Mille");


        mImageUrls.add("https://asset.dr.dk/imagescaler/?protocol=http&server=dr-billeder-s3-cdn.dr.dk&file=%2FDR%2FPublic%2F97110746b7a5da1930088ab986ba0bd4dab7af63.jpg&scaleAfter=crop&quality=70&w=720&h=480");
        mImageUrls2.add("https://asset.dr.dk/imagescaler/?protocol=http&server=dr-billeder-s3-cdn.dr.dk&file=%2FDR%2FPublic%2F97110746b7a5da1930088ab986ba0bd4dab7af63.jpg&scaleAfter=crop&quality=70&w=720&h=480");
        mNames.add("Silja");
        mNames2.add("Silja");


    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        TextView textView = findViewById(R.id.txtCustomAvatarPreset);


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);

        int selectedID =  spinnerGender.getSelectedItemPosition();
        if(selectedID == 1){
            AvatarAdapter adapter = new AvatarAdapter(this, mNames1, mImageUrls1);
            textView.setVisibility(TextView.VISIBLE);
            recyclerView.setAdapter(adapter);
        } else if(selectedID == 2){
            AvatarAdapter adapter = new AvatarAdapter(this, mNames2, mImageUrls2);
            textView.setVisibility(TextView.VISIBLE);
            recyclerView.setAdapter(adapter);
        }else if(selectedID == 3){
            AvatarAdapter adapter = new AvatarAdapter(this, mNames, mImageUrls);
            textView.setVisibility(TextView.VISIBLE);
            recyclerView.setAdapter(adapter);
        } else{ }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        animation.start();
    }
}



