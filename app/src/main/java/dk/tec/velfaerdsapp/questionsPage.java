package dk.tec.velfaerdsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class questionsPage extends AppCompatActivity {




    //This should be moved to its own file
    List<String> askedQuestions = new ArrayList<String>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_page);

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