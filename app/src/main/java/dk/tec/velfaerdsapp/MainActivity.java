package dk.tec.velfaerdsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends touchActivityHandler {

    private static final String TAG = "mainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void logoClicked(View view) {
        String url = "";
        int id = view.getId();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);

        if (id == R.id.imgLogoVfvoe)
            url = "https://videnscenterportalen.dk/vtoe/";
        else if (id == R.id.imgLogoTec)
            url = "https://www.tec.dk/";
        //De Træk sig
        //else if (id == R.id.imgLogoUg)
        //url = "https://www.ug.dk/";

        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences settings1 = getSharedPreferences("introValues", Context.MODE_PRIVATE);
        settings1.edit().clear().commit();
        SharedPreferences settings2 = getSharedPreferences("questionArray", Context.MODE_PRIVATE);
        settings2.edit().clear().commit();
        SharedPreferences settings3 = getSharedPreferences("emailArray", Context.MODE_PRIVATE);
        settings3.edit().clear().commit();
    } 
}