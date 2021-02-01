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
    String selectedItem = "";
    Spinner spinnerGender;

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

        if (gName != null){
            enterName.setText(gName);
        }
        if (gJob != null){
            enterJob.setText(gJob);
        }

        //Menu til at vælge sit køn
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.køn_array, R.layout.spinner_item_nomargin);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(new SpinnerAdapter(adapter, R.layout.contact_spinner_row_nothing_selected,this));

        if (gKøn != 0){
            spinnerGender.setSelection(gKøn);
        }

        spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if(position == 1){
                    gKøn = 1;

                } else if(position == 2) {
                    gKøn = 2;
                }
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

        gName = name;
        gJob = job;
    }
}



