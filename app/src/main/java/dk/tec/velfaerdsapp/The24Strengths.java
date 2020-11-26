package dk.tec.velfaerdsapp;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import java.util.ArrayList;

import Adapter.QuestionAdapter;
import Adapter.The24StrengthsAdapter;

import static dk.tec.velfaerdsapp.R.layout.activity_the24_strength;

public class The24Strengths extends TouchActivityHandler {

    private static final String TAG = "the24Strength";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_the24_strength);

        // questions list
        ArrayList<The24StrengthsBoxes> Strengths = new ArrayList<>();
        Strengths.add(new The24StrengthsBoxes(R.drawable.intellectual, "Question 1"));
        Strengths.add(new The24StrengthsBoxes(R.drawable.people_person, "Question 2"));
        Strengths.add(new The24StrengthsBoxes(R.drawable.sharp_brained, "Question 3"));
        Strengths.add(new The24StrengthsBoxes(R.drawable.intellectual, "Question 4"));
        Strengths.add(new The24StrengthsBoxes(R.drawable.people_person, "Question 5"));
        Strengths.add(new The24StrengthsBoxes(R.drawable.sharp_brained, "Question 6"));

        RecyclerView mthe24StrenghtsRecyclerView = findViewById(R.id.the24StrenghtsRecyclerView);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        RecyclerView.Adapter mAdapter = new The24StrengthsAdapter(Strengths);

        mthe24StrenghtsRecyclerView.setLayoutManager(mLayoutManager);
        mthe24StrenghtsRecyclerView.setAdapter(mAdapter);
    }
}