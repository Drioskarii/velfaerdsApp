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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class IntroPage extends TouchActivityHandler {

    //////////////////////////////////////////////////////////
    // Intropage er til at indsætte sine informationer
    // samt få vist sin avatar
    //////////////////////////////////////////////////////////

    //Vars
    private static final String TAG = "IntroPage";
    Button btnBack, btnForward;
    private EditText enterName;
    private EditText enterJob;
    private ArrayList<String> mNames1 = new ArrayList<>();
    private ArrayList<String> mNames2 = new ArrayList<>();
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<Integer> mImageUrls1 = new ArrayList<Integer>();
    private ArrayList<Integer> mImageUrls2 = new ArrayList<Integer>();
    private ArrayList<Integer> mImageUrls = new ArrayList<Integer>();
    String selectedItem = "";
    Spinner spinnerGender;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Binding til XML objekter
        setContentView(R.layout.activity_intro_page);
        enterName = findViewById(R.id.editTextName);
        enterJob = findViewById(R.id.editTextJob);
        spinnerGender = findViewById(R.id.spinnerGender);
        btnBack = findViewById(R.id.btn_intro_back);
        btnForward = findViewById(R.id.btn_intro_forward);

        //Hent data fra Shared prefs
        SharedPreferences sharedPreferences = getSharedPreferences("introValues", MODE_PRIVATE);
        String s1 = sharedPreferences.getString("gName", "");
        String s2 = sharedPreferences.getString("gJob", "");
        enterName.setText(s1);
        enterJob.setText(s2);

        //Menu til at vælge sit køn
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.køn_array, R.layout.spinner_item_nomargin);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(
                new SpinnerAdapter(
                        adapter,
                        R.layout.contact_spinner_row_nothing_selected,
                        this));


        //Indsæt avatar baseret på køn, på siden
        getImages();


        spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                System.out.println("Positionen er: " + position);
                initRecyclerView();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        // Listener til back button (Gå tilbage til forrige page)
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePage();
            }
        });

        // Listener til forward button (Gå frem til næste page)
        btnForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(newPage(IntroPage.this, QuestionTutorial.class));
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        //Indsætter data til shared prefs
        String name = "" + enterName.getText();
        String job = "" + enterJob.getText();
        int selectedID = spinnerGender.getSelectedItemPosition();

        SharedPreferences sharedPref = getSharedPreferences("introValues", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString("gName", name);
        editor.putString("gJob", job);
        editor.putInt("gGender" , selectedID);
        editor.apply();

        gName = name;
        gJob = job;

    }

    public class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            System.out.println("Positionen er:" + position);
            selectedItem = parent.getItemAtPosition(position).toString();
            if (!selectedItem.equals("Vælg køn")) {
                //Toast.makeText(parent.getContext(), "Køn valgt: " + selectedItem, Toast.LENGTH_LONG).show();

                initRecyclerView();
            }
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    }

    //Indsætter avatar på siden
    private void getImages() {
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls.add(R.drawable.tndmand_mod);
        mImageUrls1.add(R.drawable.tndmand_mod);
        mNames.add("");
        mNames1.add("Mand");

        mImageUrls.add(R.drawable.tndkvinde_mod);
        mImageUrls2.add(R.drawable.tndkvinde_mod);
        mNames.add("");
        mNames2.add("Kvinde");
    }

    // Jeg har ingen ide hva den her gør...
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

            gKøn = 1;

        } else if(selectedID == 2) {
            AvatarAdapter adapter = new AvatarAdapter(this, mNames2, mImageUrls2);
            textView.setVisibility(TextView.VISIBLE);
            recyclerView.setAdapter(adapter);

            gKøn = 2;
        }
    }
}



