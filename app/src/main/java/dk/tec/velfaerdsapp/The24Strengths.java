package dk.tec.velfaerdsapp;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import java.util.ArrayList;

import Adapter.QuestionAdapter;

import static dk.tec.velfaerdsapp.R.layout.activity_the24_strength;

public class The24Strengths extends TouchActivityHandler {

    private static final String TAG = "The24Strength";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_the24_strength);

    }
}