package dk.tec.velfaerdsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class touchActivityHandler extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private float x1, x2;
    private static int MIN_DISTANCE = 400;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.gestureDetector = new GestureDetector(this, this);
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
                        //swipe left / back
                        if (this.toString().contains("MainActivity")) {startActivity(backward(this, the24Strength.class));}
                        else if (this.toString().contains("the24Strength")){backward(); }
                        //else if (this.toString().contains("introPage")){backward(); }
                        else if (this.toString().contains("customAvatar")){backward(); }
                        else if (this.toString().contains("questionsPage")){backward(); }
                        else if (this.toString().contains("selectPage")){backward(); }
                        else if (this.toString().contains("emailPage")){backward(); }
                    } else {
                        //swipe right / forward
                        if (this.toString().contains("MainActivity")){startActivity(forward(this, introPage.class));}
                        else if (this.toString().contains("the24Strength")){backward();}
                        //else if (this.toString().contains("introPage")){startActivity(forward(this, customAvatar.class));}
                        else if (this.toString().contains("customAvatar")){startActivity(forward(this, questionsPage.class));}
                        else if (this.toString().contains("questionsPage")){startActivity(forward(this, selectPage.class));}
                        else if (this.toString().contains("selectPage")){startActivity(forward(this, emailPage.class));}
                    }
                }
        }
        return super.onTouchEvent(event);
    }

    //Forward er altid swipe ( højre mod venstre )
    public Intent forward(Context context, Class toClass) {
        Intent intent = new Intent(context, toClass);
        return intent;
    }

    //Backward er altid swipe ( venstre mod højre )
    public Intent backward(Context context, Class toClass) {
        Intent intent = new Intent(context, toClass);
        return intent;
    }

    public void backward() {
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