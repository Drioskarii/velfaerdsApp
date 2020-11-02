package dk.tec.velfaerdsapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class questionsPage extends AppCompatActivity {

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