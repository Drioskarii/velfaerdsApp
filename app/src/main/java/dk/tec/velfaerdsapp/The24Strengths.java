package dk.tec.velfaerdsapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;

import Adapter.The24StrengthsAdapter;
import Strengths.Strengths;

import static dk.tec.velfaerdsapp.R.layout.activity_the24_strength;

public class The24Strengths extends TouchActivityHandler {

    private static final String TAG = "The24Strength";

    ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(activity_the24_strength);

        listView = findViewById(R.id.listView);
        The24StrengthsAdapter modAdapter = new The24StrengthsAdapter(The24Strengths.this, Strengths.getModList());
        listView.setAdapter(modAdapter);
    }

    public void modClick(View view) {
        The24StrengthsAdapter modAdapter = new The24StrengthsAdapter(The24Strengths.this, Strengths.getModList());
        listView.setAdapter(modAdapter);
    }

    public void nysClick(View view) {
        The24StrengthsAdapter nysAdapter = new The24StrengthsAdapter(The24Strengths.this, Strengths.getNysList());
        listView.setAdapter(nysAdapter);
    }

    public void besClick(View view) {
        The24StrengthsAdapter besAdapter = new The24StrengthsAdapter(The24Strengths.this, Strengths.getBesList());
        listView.setAdapter(besAdapter);
    }

    public void takClick(View view) {
        The24StrengthsAdapter takAdapter = new The24StrengthsAdapter(The24Strengths.this, Strengths.getTakList());
        listView.setAdapter(takAdapter);
    }

    public void samClick(View view) {
        The24StrengthsAdapter samAdapter = new The24StrengthsAdapter(The24Strengths.this, Strengths.getSamList());
        listView.setAdapter(samAdapter);
    }

    public void socClick(View view) {
        The24StrengthsAdapter socAdapter = new The24StrengthsAdapter(The24Strengths.this, Strengths.getSocList());
        listView.setAdapter(socAdapter);
    }
}