package dk.tec.velfaerdsapp;

import Adapter.QuestionsAdapter;
import Adapter.SelectAdapter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class TouchActivityHandler extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private float x1, x2;
    private static int MIN_DISTANCE = 0;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MIN_DISTANCE = dpToPx(125,this);
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
                    if (x2 > x1) { //-----------SWIPE LEFT TO RIGHT-----------
                        //swipe left / back
                        if (this.toString().contains("MainActivity")) {
                            if (!MainActivity.tutDone){
                                tutorialDone();
                            }
                            startActivity(forward(this, The24Strengths.class));
                        } else{backward(); }
                    } else { //-----------SWIPE RIGHT TO LEFT-----------
                        //swipe right / forward
                        if (this.toString().contains("MainActivity")){
                            if (!MainActivity.tutDone){
                                tutorialDone();
                            }
                            startActivity(forward(this, IntroPage.class));
                        }
                        else if (this.toString().contains("The24Strength")){
                            backward();
                        }
                        else if (this.toString().contains("IntroPage")){
                            startActivity(forward(this, QuestionsPage.class));
                        }
                        else if (this.toString().contains("QuestionsPage")) {
                            if (QuestionsPage.answeredCount == QuestionsPage.count){
                                Intent intent = new Intent(this, SelectPage.class);
                                intent.putParcelableArrayListExtra("ObjectList",QuestionsAdapter.strengths);
                                startActivity(intent);
                            }
                            else{ Toast.makeText(this, "Besvar alle spørgsmål for at fortsætte", Toast.LENGTH_SHORT).show(); } }
                        else if (this.toString().contains("SelectPage")){
                            Intent intent = new Intent(this, ResultPage.class);
                            intent.putStringArrayListExtra("SelectedList", SelectAdapter.selected);
                            startActivity(intent);}
                        else if (this.toString().contains("ResultPage")){startActivity(forward(this, EmailPage.class));}
                    }
                }
        }
        return super.onTouchEvent(event);
    }

    public void tutorialDone(){
        MainActivity.tutDone = true;
        RelativeLayout relativeLayout = findViewById(R.id.layoutTutorial);
        relativeLayout.animate().alpha(0.0f).setDuration(1000);
        SharedPreferences sharedPref = getSharedPreferences("tutorialState", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("tutorialState", ""+MainActivity.tutDone);
        editor.apply();
    }

    public static int dpToPx(int dp, Context context){
        return dp * ((int) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
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