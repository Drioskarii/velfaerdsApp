package dk.tec.velfaerdsapp;

import Adapter.questionViewAdapter;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

//import com.google.android.material.slider.Slider;

import java.util.ArrayList;

public class questionsPage extends AppCompatActivity {


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
    }
    public void forward(View view) {
        Intent intent = new Intent(this, selectPage.class);

        startActivity(intent);
    }

    public void back(View view) {
        Intent intent = new Intent(this, customAvatar.class);

        startActivity(intent);
    }
}