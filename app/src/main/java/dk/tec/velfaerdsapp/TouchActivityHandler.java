package dk.tec.velfaerdsapp;

import QuestionsAdapter.BesAdapter;
import QuestionsAdapter.ModAdapter;
import QuestionsAdapter.NysAdapter;
import QuestionsAdapter.SamAdapter;
import Adapter.SelectAdapter;
import QuestionsAdapter.SocAdapter;
import QuestionsAdapter.TakAdapter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class TouchActivityHandler extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private float x1, x2;
    private static int MIN_DISTANCE = 0;
    private GestureDetector gestureDetector;
    public static String gName;
    public static String gJob;


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
                        if (this.toString().contains("MainActivity")) {
                            if (!MainActivity.tutDone) {
                                tutorialDone();
                            }
                            startActivity(forward(this, IntroPage.class));
                        } else if (this.toString().contains("The24Strength")) {
                            backward();
                        } else if (this.toString().contains("IntroPage")) {
                            startActivity(forward(this, ModPage.class));
                        } else if (this.toString().contains("ModPage")) {
                            if (ModPage.answeredCount == ModPage.count) {
                                startActivity(forward(this, NysPage.class));
                            } else {
                                Toast.makeText(this, "Besvar alle spørgsmål for at fortsætte", Toast.LENGTH_SHORT).show();
                            }
                        } else if (this.toString().contains("NysPage")) {
                            if (NysPage.answeredCount == NysPage.count) {
                                startActivity(forward(this, BesPage.class));
                            } else {
                                Toast.makeText(this, "Besvar alle spørgsmål for at fortsætte", Toast.LENGTH_SHORT).show();
                            }
                        } else if (this.toString().contains("BesPage")) {
                            if (BesPage.answeredCount == BesPage.count) {
                                startActivity(forward(this, TakPage.class));
                            } else {
                                Toast.makeText(this, "Besvar alle spørgsmål for at fortsætte", Toast.LENGTH_SHORT).show();
                            }
                        } else if (this.toString().contains("TakPage")) {
                            if (TakPage.answeredCount == TakPage.count) {
                                startActivity(forward(this, SamPage.class));
                            } else {
                                Toast.makeText(this, "Besvar alle spørgsmål for at fortsætte", Toast.LENGTH_SHORT).show();
                            }
                        } else if (this.toString().contains("SamPage")) {
                            if (SamPage.answeredCount == SamPage.count) {
                                startActivity(forward(this, SocPage.class));
                            } else {
                                Toast.makeText(this, "Besvar alle spørgsmål for at fortsætte", Toast.LENGTH_SHORT).show();
                            }
                        } else if (this.toString().contains("SocPage")) {
                            if (SocPage.answeredCount == SocPage.count) {
                                Intent intent = new Intent(this, SelectPage.class);
                                intent.putParcelableArrayListExtra("ModList", ModAdapter.strengths);
                                intent.putParcelableArrayListExtra("NysList", NysAdapter.strengths);
                                intent.putParcelableArrayListExtra("BesList", BesAdapter.strengths);
                                intent.putParcelableArrayListExtra("TakList", TakAdapter.strengths);
                                intent.putParcelableArrayListExtra("SamList", SamAdapter.strengths);
                                intent.putParcelableArrayListExtra("SocList", SocAdapter.strengths);
                                startActivity(intent);
                            } else {
                                Toast.makeText(this, "Besvar alle spørgsmål for at fortsætte", Toast.LENGTH_SHORT).show();
                            }
                        } else if (this.toString().contains("SelectPage")) {
                            if (SelectAdapter.goodConfirmCounter == 2){
                                Intent intent = new Intent(this, ResultPage.class);
                                intent.putParcelableArrayListExtra("goodSelectedList", SelectAdapter.goodSelected);
                                //intent.putParcelableArrayListExtra("badSelectedList", SelectAdapter.badSelected);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(this, "vælg 2 styrker for at fortsætte", Toast.LENGTH_SHORT).show();
                            }
                        }
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