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

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private float x1, x2;
    private static int MIN_DISTANCE = 400;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init gestureDetector
        this.gestureDetector = new GestureDetector(MainActivity.this, this);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        switch (event.getAction()) {
            //press
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;

            //lift
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                //horizontal swipe
                float valueX = x2 - x1;
                if (Math.abs(valueX) > MIN_DISTANCE) {
                    if (x2 > x1) {
                        backward();
                    } else {
                        forward();
                    }
                }
        }
        return super.onTouchEvent(event);
    }

    //Forward er altid swipe ( højre mod venstre )
    public void forward() {
        Intent intent = new Intent(MainActivity.this, introPage.class);
        startActivity(intent);
    }

    //Backward er altid swipe ( venstre mod højre )
    public void backward() {
        Intent intent = new Intent(MainActivity.this, the24Strength.class);
        startActivity(intent);
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
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}