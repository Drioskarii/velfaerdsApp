package dk.tec.velfaerdsapp;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends TouchActivityHandler {

    private static final String TAG = "mainActivity";
    final Handler handler = new Handler();
    boolean tutDone = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tutorialFrame();
            }
        }, 0);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tutorialText();
            }
        }, 5000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tutDone = true;
                tutorialFrame();
            }
        }, 8000);

        SharedPreferences settings1 = getSharedPreferences("introValues", Context.MODE_PRIVATE);
        settings1.edit().clear().apply();
        SharedPreferences settings2 = getSharedPreferences("questionArray", Context.MODE_PRIVATE);
        settings2.edit().clear().apply();
        SharedPreferences settings3 = getSharedPreferences("emailArray", Context.MODE_PRIVATE);
        settings3.edit().clear().apply();
    }

    public void logoClicked(View view) {
        if (tutDone) {
        String url = "";
        int id = view.getId();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);

            if (id == R.id.imgLogoVfvoe)
                url = "https://videnscenterportalen.dk/vtoe/";
            else if (id == R.id.imgLogoTec)
                url = "https://www.tec.dk/";


        intent.setData(Uri.parse(url));
        startActivity(intent);
        }
    }

    public void tutorialFrame(){
        RelativeLayout relativeLayout = findViewById(R.id.layoutTutorial);
        if (tutDone){
            relativeLayout.animate().alpha(0.0f).setDuration(1000);
        }
        else {
            relativeLayout.setAlpha(0.0f);
            relativeLayout.animate().alpha(1.0f).setDuration(1000);
            relativeLayout.setVisibility(View.VISIBLE);

            RelativeLayout relLeft = findViewById(R.id.swipeLeftView);
            RelativeLayout relRight = findViewById(R.id.swipeRightView);

            ObjectAnimator scaleLeft = ObjectAnimator.ofPropertyValuesHolder(
                    relLeft,
                    PropertyValuesHolder.ofFloat("scaleX", 0.8f),
                    PropertyValuesHolder.ofFloat("scaleY", 0.975f));
            ObjectAnimator scaleRight = ObjectAnimator.ofPropertyValuesHolder(
                    relRight,
                    PropertyValuesHolder.ofFloat("scaleX", 0.8f),
                    PropertyValuesHolder.ofFloat("scaleY", 0.975f));
            scaleLeft.setDuration(1000);
            scaleRight.setDuration(1000);

            scaleLeft.setRepeatCount(ValueAnimator.INFINITE);
            scaleLeft.setRepeatMode(ObjectAnimator.REVERSE);
            scaleRight.setRepeatCount(ValueAnimator.INFINITE);
            scaleRight.setRepeatMode(ObjectAnimator.REVERSE);

            scaleLeft.start();
            scaleRight.start();

            TextView txtTutorial = findViewById(R.id.txtTutorial);
            txtTutorial.setAlpha(0.0f);
            txtTutorial.animate().alpha(1.0f).setDuration(1000);
            txtTutorial.setText("De hvide streger indikere, hvor du kan slide!");
        }
    }

    public void tutorialText(){
        TextView txtTutorial = findViewById(R.id.txtTutorial);
        txtTutorial.animate().alpha(0.0f).setDuration(1000);
        txtTutorial.setText("Prøv det!");
        txtTutorial.animate().alpha(1.0f).setDuration(1000);
    }
}