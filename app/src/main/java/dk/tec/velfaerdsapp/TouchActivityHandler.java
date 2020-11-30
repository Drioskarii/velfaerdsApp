package dk.tec.velfaerdsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class TouchActivityHandler extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private float x1, x2;
    private static final int MIN_DISTANCE = 500;
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
                        if (this.toString().contains("MainActivity")) {startActivity(/* "Backward" */forward(this, The24Strengths.class));}
                        else{backward(); }
                    } else {
                        //swipe right / forward
                        if (this.toString().contains("MainActivity")){startActivity(forward(this, IntroPage.class));}
                        else if (this.toString().contains("The24Strength")){backward();}
                        else if (this.toString().contains("IntroPage")){startActivity(forward(this, QuestionsPage.class));}
                        else if (this.toString().contains("QuestionsPage")) {
                            startActivity(forward(this, SelectPage.class));}
                            //if (QuestionsPage.answered == QuestionsPage.count){ startActivity(forward(this, SelectPage.class)); }
                            //else{ Toast.makeText(this, "Besvar alle spørgsmål for at fortsætte", Toast.LENGTH_SHORT).show(); } }
                        else if (this.toString().contains("SelectPage")){startActivity(forward(this, ResultPage.class));}
                        else if (this.toString().contains("ResultPage")){startActivity(forward(this, EmailPage.class));}
                    }
                }
        }
        return super.onTouchEvent(event);
    }

    //Forward er altid swipe ( højre mod venstre )
    public Intent forward(Context context, Class toClass) {
        return new Intent(context, toClass);
    }

    //Backward er altid swipe ( venstre mod højre )
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