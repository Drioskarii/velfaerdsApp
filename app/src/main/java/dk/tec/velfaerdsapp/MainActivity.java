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

    private float x1, x2;
    private static int MIN_DISTANCE = 400;
    private GestureDetector gestureDetector;

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
        else if (id == R.id.imgLogoUg)
            url = "https://www.ug.dk/";

        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        SharedPreferences settings = getSharedPreferences("myKey", Context.MODE_PRIVATE);
        settings.edit().clear().commit();
        super.onStop();
    }

}