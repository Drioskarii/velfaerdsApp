package dk.tec.velfaerdsapp;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.ScrollView;

import Adapter.The24StrengthsAdapter;
import Strengths.Strengths;

import static dk.tec.velfaerdsapp.R.layout.activity_the24_strength;

public class The24Strengths extends TouchActivityHandler {

    private static final String TAG = "The24Strength";

    ListView modListView;
    ListView nysListView;
    ListView besListView;
    ListView takListView;
    ListView samListView;
    ListView socListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(activity_the24_strength);

        modListView = findViewById(R.id.modListView);

        nysListView = findViewById(R.id.nysListView);

        besListView = findViewById(R.id.besListView);

        takListView = findViewById(R.id.takListView);

        samListView = findViewById(R.id.samListView);

        socListView = findViewById(R.id.socListView);

        The24StrengthsAdapter modAdapter = new The24StrengthsAdapter(The24Strengths.this, Strengths.getModList());
        modListView.setAdapter(modAdapter);

        The24StrengthsAdapter nysAdapter = new The24StrengthsAdapter(The24Strengths.this, Strengths.getNysList());
        nysListView.setAdapter(nysAdapter);

        The24StrengthsAdapter besAdapter = new The24StrengthsAdapter(The24Strengths.this, Strengths.getBesList());
        besListView.setAdapter(besAdapter);

        The24StrengthsAdapter takAdapter = new The24StrengthsAdapter(The24Strengths.this, Strengths.getTakList());
        takListView.setAdapter(takAdapter);

        The24StrengthsAdapter samAdapter = new The24StrengthsAdapter(The24Strengths.this, Strengths.getSamList());
        samListView.setAdapter(samAdapter);

        The24StrengthsAdapter socAdapter = new The24StrengthsAdapter(The24Strengths.this, Strengths.getSocList());
        socListView.setAdapter(socAdapter);

    }
}