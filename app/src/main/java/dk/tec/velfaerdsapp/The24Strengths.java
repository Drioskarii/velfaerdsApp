package dk.tec.velfaerdsapp;

import Adapter.QuestionsAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.ProgressBar;


import java.util.ArrayList;

import Adapter.The24StrengthsAdapter;

import static dk.tec.velfaerdsapp.R.layout.activity_the24_strength;

public class The24Strengths extends TouchActivityHandler {

    private static final String TAG = "The24Strength";

    ListView the24StrenghtsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_the24_strength);

        the24StrenghtsListView = findViewById(R.id.the24StrenghtsListView);
        The24StrengthsAdapter the24Adapter = new The24StrengthsAdapter(The24Strengths.this, Strengths.getQuestionList());
        the24StrenghtsListView.setAdapter(the24Adapter);
    }
}