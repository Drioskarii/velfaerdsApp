package dk.tec.velfaerdsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class customAvatar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_avatar);
    }

    public void forward(View view) {
        Intent intent = new Intent(this, questionsPage.class);

        startActivity(intent);
    }

    public void back(View view) {
        Intent intent = new Intent(this, introPage.class);

        startActivity(intent);
    }
}

// avatar skal ind her
// hej
// github over slack