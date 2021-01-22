package dk.tec.velfaerdsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class QuestionTutorial extends TouchActivityHandler {

    Button btnBack, btnForward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_tutorial);
        btnBack = findViewById(R.id.btn_questionstut_back);
        btnForward = findViewById(R.id.btn_questionstut_forward);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePage();
            }
        });

        btnForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(newPage(QuestionTutorial.this, ModPage.class));
            }
        });
    }
}