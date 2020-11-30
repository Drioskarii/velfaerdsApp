package dk.tec.velfaerdsapp;

import Adapter.QuestionsAdapter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

//import com.google.android.material.slider.Slider;


public class QuestionsPage extends TouchActivityHandler {

    private static final String TAG = "questionsPage";

    public static ProgressBar questionsProgressBar;
    public static int count;
    public static int answeredCount;
    ListView listOfQuestions;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_page);
        questionsProgressBar = findViewById(R.id.questionsProgressBar);
        listOfQuestions = findViewById(R.id.listOfQuestions);
        QuestionsAdapter questionsAdapter = new QuestionsAdapter(QuestionsPage.this, Strengths.getQuestionList());
        listOfQuestions.setAdapter(questionsAdapter);

        count = questionsAdapter.getCount();
        questionsProgressBar.setMax(questionsAdapter.getCount());
        questionsProgressBar.setProgress(answeredCount);
    }
}