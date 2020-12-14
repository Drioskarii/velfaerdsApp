package dk.tec.velfaerdsapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;

import Adapter.The24StrengthsAdapter;
import Strengths.Strengths;

import static dk.tec.velfaerdsapp.R.layout.activity_the24_strength;

public class The24Strengths extends TouchActivityHandler {

    private static final String TAG = "The24Strength";

    ListView listView;
    ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(activity_the24_strength);

       imageView = findViewById(R.id.selectedListImage);
        listView = findViewById(R.id.listView);
        The24StrengthsAdapter modAdapter = new The24StrengthsAdapter(The24Strengths.this, Strengths.getModList());
        listView.setAdapter(modAdapter);
        imageView.setImageResource(R.drawable.iconmod);
    }



    public void modClick(View view) {
        The24StrengthsAdapter modAdapter = new The24StrengthsAdapter(The24Strengths.this, Strengths.getModList());
        listView.setAdapter(modAdapter);
        imageView.setImageResource(R.drawable.iconmod);
    }

    public void nysClick(View view) {
        The24StrengthsAdapter nysAdapter = new The24StrengthsAdapter(The24Strengths.this, Strengths.getNysList());
        listView.setAdapter(nysAdapter);
        imageView.setImageResource(R.drawable.iconnysgerrig);
    }

    public void besClick(View view) {
        The24StrengthsAdapter besAdapter = new The24StrengthsAdapter(The24Strengths.this, Strengths.getBesList());
        listView.setAdapter(besAdapter);
        imageView.setImageResource(R.drawable.iconbeskedenhed);
    }

    public void takClick(View view) {
        The24StrengthsAdapter takAdapter = new The24StrengthsAdapter(The24Strengths.this, Strengths.getTakList());
        listView.setAdapter(takAdapter);
        imageView.setImageResource(R.drawable.icontaknemmelighed);
    }

    public void samClick(View view) {
        The24StrengthsAdapter samAdapter = new The24StrengthsAdapter(The24Strengths.this, Strengths.getSamList());
        listView.setAdapter(samAdapter);
        imageView.setImageResource(R.drawable.iconsamarbejde);
    }

    public void socClick(View view) {
        The24StrengthsAdapter socAdapter = new The24StrengthsAdapter(The24Strengths.this, Strengths.getSocList());
        listView.setAdapter(socAdapter);
        imageView.setImageResource(R.drawable.iconsocialintelligens);
    }
}