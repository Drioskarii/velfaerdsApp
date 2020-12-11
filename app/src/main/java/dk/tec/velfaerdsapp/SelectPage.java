package dk.tec.velfaerdsapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Adapter.SelectAdapter;
import Strengths.Strengths;


public class SelectPage extends TouchActivityHandler {


    private static final String TAG = "selectPage";
    //vars
    ArrayList<Strengths> strengths = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_page);
        strengths = getIntent().getParcelableArrayListExtra("ObjectList");

        getGoodImages();
        getBadImages();

        TextView txtSelectDinAvatar = findViewById(R.id.txtSelectDinAvatar);
        txtSelectDinAvatar.setText(gJob + " " + gName);
    }


    private void getGoodImages() {
        initRecyclerView();
    }

    private void getBadImages() {
        initRecyclerView();
    }

    @SuppressLint("NewApi")
    private void initRecyclerView() {
        LinearLayoutManager layoutManagerGood = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerViewGood = findViewById(R.id.recyclerViewGood);
        recyclerViewGood.setLayoutManager(layoutManagerGood);

        SelectAdapter goodAdapter = new SelectAdapter(this, strengths, true);
        recyclerViewGood.setAdapter(goodAdapter);


        LinearLayoutManager layoutManagerBad = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerViewBad = findViewById(R.id.recyclerViewBad);
        recyclerViewBad.setLayoutManager(layoutManagerBad);

        SelectAdapter badAdapter = new SelectAdapter(this, strengths, false);
        recyclerViewBad.setAdapter(badAdapter);

    }
}