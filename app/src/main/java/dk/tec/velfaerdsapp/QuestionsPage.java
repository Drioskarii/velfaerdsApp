package dk.tec.velfaerdsapp;

import Adapter.QuestionsAdapter;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

//import com.google.android.material.slider.Slider;


public class QuestionsPage extends TouchActivityHandler {

    private static final String TAG = "questionsPage";

    public static TextView trophyTxt;
    public static ProgressBar questionsProgressBar;
    public static ImageView imgNextPage;
    public static int count;
    public static int answeredCount;
    ListView listOfQuestions;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod_page);
        questionsProgressBar = findViewById(R.id.questionsProgressBar);
        listOfQuestions = findViewById(R.id.listOfQuestions);
        imgNextPage = findViewById(R.id.imgNextPage);
        QuestionsAdapter questionsAdapter = new QuestionsAdapter(QuestionsPage.this, Strengths.getModList());
        listOfQuestions.setAdapter(questionsAdapter);

        count = questionsAdapter.getCount();
        questionsProgressBar.setMax(questionsAdapter.getCount());
        questionsProgressBar.setProgress(answeredCount);
        checkPoints();
    }

    public static void checkPoints(){
        imgNextPage.setAlpha(0.0f);

        if (answeredCount == count){
            imgNextPage.setVisibility(View.VISIBLE);
            imgNextPage.animate().alpha(1.0f).setDuration(1000);
            imageBounce();
        }
        else {
            imgNextPage.setVisibility(View.GONE);
        }
    }

    public static void imageBounce(){
            ObjectAnimator pulse = ObjectAnimator.ofPropertyValuesHolder(
                    imgNextPage,
                    PropertyValuesHolder.ofFloat("scaleX", 1.2f),
                    PropertyValuesHolder.ofFloat("scaleY", 1.2f));
            pulse.setDuration(1500);

            pulse.setRepeatCount(ValueAnimator.INFINITE);
            pulse.setRepeatMode(ObjectAnimator.REVERSE);

            pulse.start();
    }
}