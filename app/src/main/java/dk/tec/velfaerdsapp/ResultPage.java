package dk.tec.velfaerdsapp;

import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Adapter.ResultAdapter;
import Strengths.Points;

public class ResultPage extends TouchActivityHandler{

    private static final String TAG = "ResultatPage";
    //Initialising
    AnimationDrawable animation;
    ImageView characterPlaceholder;
    TextView questionTxt;
    //vars
    ArrayList<Points> goodSelected = new ArrayList<>();
    ArrayList<Points> badSelected = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);

        characterPlaceholder = findViewById(R.id.characterPlaceholder);
        characterPlaceholder.setBackgroundResource(R.drawable.animation);
        animation = (AnimationDrawable) characterPlaceholder.getBackground();
        questionTxt = findViewById(R.id.select_txtQuestion);

        goodSelected.clear();
        badSelected.clear();

        getValue();
        initRecyclerView();

        TextView txtSelectDinAvatar = findViewById(R.id.txtResultDinAvatar);
        txtSelectDinAvatar.setText(gJob + " " + gName);
    }

    private void getValue() {
        goodSelected = getIntent().getParcelableArrayListExtra("goodSelectedList");
        badSelected = getIntent().getParcelableArrayListExtra("badSelectedList");

        System.out.println("HELP");
        System.out.println(goodSelected.size());
        System.out.println(goodSelected);
        System.out.println(badSelected.size());
        System.out.println(badSelected);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        animation.start();
    }

    private void initRecyclerView() {

        LinearLayoutManager layoutManagerGood = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerViewGood = findViewById(R.id.recyclerViewGood);
        recyclerViewGood.setLayoutManager(layoutManagerGood);

        ResultAdapter goodAdapter = new ResultAdapter(this, goodSelected , true);
        recyclerViewGood.setAdapter(goodAdapter);


        LinearLayoutManager layoutManagerBad = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerViewBad = findViewById(R.id.recyclerViewBad);
        recyclerViewBad.setLayoutManager(layoutManagerBad);

        ResultAdapter badAdapter = new ResultAdapter(this, badSelected, false);
        recyclerViewBad.setAdapter(badAdapter);
    }
}