package dk.tec.velfaerdsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class the24Strength extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private float x1, x2;
    private static int MIN_DISTANCE = 100;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the24_strength);

        this.gestureDetector = new GestureDetector(the24Strength.this, this);
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

                    } else {
                        forward();
                    }
                }
        }
        return super.onTouchEvent(event);
    }

    public void forward() {
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
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