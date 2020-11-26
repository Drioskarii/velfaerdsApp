package dk.tec.velfaerdsapp;

import Adapter.QuestionAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ProgressBar;

//import com.google.android.material.slider.Slider;

import java.util.ArrayList;

public class QuestionsPage extends TouchActivityHandler {

    private static final String TAG = "questionsPage";

    public static ProgressBar questionsProgressBar;
    public static int count;
    public static int answered;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_page);
        questionsProgressBar = findViewById(R.id.questionsProgressBar);

        RecyclerView mQuestionRecyclerView = findViewById(R.id.recyclerView);
        mQuestionRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        RecyclerView.Adapter mAdapter = new QuestionAdapter(Strengths.getQuestionList());

        mQuestionRecyclerView.setLayoutManager(mLayoutManager);
        mQuestionRecyclerView.setAdapter(mAdapter);

        count = mAdapter.getItemCount();
        questionsProgressBar.setMax(mAdapter.getItemCount());
        questionsProgressBar.setProgress(answered);
    }
}