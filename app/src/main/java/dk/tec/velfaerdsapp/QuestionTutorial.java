package dk.tec.velfaerdsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class QuestionTutorial extends TouchActivityHandler {
    //////////////////////////////////////////////////////////
    // QuestionTutorial bliver brugt til at fremvise
    // hvordan brugeren navigere igennem question pages.
    //////////////////////////////////////////////////////////

    // vars
    Button btnBack, btnForward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_tutorial);

        // Binding af vars til komponenter i XML
        btnBack = findViewById(R.id.btn_questionstut_back);
        btnForward = findViewById(R.id.btn_questionstut_forward);

        // Listener til back button (Gå tilbage til forrige page)
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePage();
            }
        });

        // Listener til forward button (Gå frem til næste page)
        btnForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(newPage(QuestionTutorial.this, ModPage.class));
            }
        });
    }
}