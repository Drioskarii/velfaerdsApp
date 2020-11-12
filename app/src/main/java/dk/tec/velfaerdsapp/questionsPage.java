package dk.tec.velfaerdsapp;

import Adapter.questionViewAdapter;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

//import com.google.android.material.slider.Slider;

import java.util.ArrayList;

public class questionsPage extends AppCompatActivity implements GestureDetector.OnGestureListener{

    private float x1, x2;
    private static int MIN_DISTANCE = 100;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_page);



        // questions list
        ArrayList<questionboxes> questionList = new ArrayList<>();
        questionList.add(new questionboxes(R.drawable.intellectual, "Question 1"));
        questionList.add(new questionboxes(R.drawable.people_person, "Question 2"));
        questionList.add(new questionboxes(R.drawable.sharp_brained, "Question 3"));
        questionList.add(new questionboxes(R.drawable.intellectual, "Question 4"));
        questionList.add(new questionboxes(R.drawable.people_person, "Question 5"));
        questionList.add(new questionboxes(R.drawable.sharp_brained, "Question 6"));

        RecyclerView mQuestionRecyclerView = findViewById(R.id.recyclerView);
        mQuestionRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        RecyclerView.Adapter mAdapter = new questionViewAdapter(questionList);

        mQuestionRecyclerView.setLayoutManager(mLayoutManager);
        mQuestionRecyclerView.setAdapter(mAdapter);


//        String[] questionArray = {"One", "Two", "Three", "Four", "Two", "Three", "Four", "Two", "Three", "Four", "Two", "Three", "Four", "Two", "Three", "Four", "Two", "Three", "Four", "Two", "Three", "Four"};
//        Object answersToQuestions = new Object();
//
//        LinearLayout llContainer = (LinearLayout) findViewById(R.id.llContainer);
//        LinearLayout linearLayout = new LinearLayout(this);
//        linearLayout.setGravity(LinearLayout.TEXT_ALIGNMENT_CENTER);
//        linearLayout.setOrientation(LinearLayout.VERTICAL);
//
//        TableLayout.LayoutParams params = new TableLayout.LayoutParams();
//
//
//        params.setMargins(5, 5, 5, 100);
//        for( int i = 0; i < questionArray.length; i++ )
//        {
//            //button are created
//            TextView etq = new TextView(this);
//            SeekBar slider = new SeekBar(this);
//            TextView fisk = new TextView(this);
//
//            //This is where editText the parameter goes.
//            etq.setText(questionArray[i]);
//            etq.setGravity(Gravity.CENTER);
//            fisk.setLayoutParams(params);
//
//            //This is where SeekBar the parameter goes.
//
//
//            //This is where the EditText gets added to activity_email_page.xml
//            llContainer.addView(etq);
//            llContainer.addView(slider);
//
//        }
        //init gestureDetector
        this.gestureDetector = new GestureDetector(questionsPage.this, this);
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

    public void forward() {
        Intent intent = new Intent(questionsPage.this, selectPage.class);

        startActivity(intent);
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