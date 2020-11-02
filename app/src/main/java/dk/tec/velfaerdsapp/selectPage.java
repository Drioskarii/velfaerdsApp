package dk.tec.velfaerdsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class selectPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_page);
    }

    public void forward(View view) {
        Intent intent = new Intent(this, emailPage.class);

        startActivity(intent);
    }

    public void back(View view) {
        Intent intent = new Intent(this, questionsPage.class);

        startActivity(intent);
    }
}