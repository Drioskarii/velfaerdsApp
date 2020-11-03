package dk.tec.velfaerdsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void forward(View view) {
        Intent intent = new Intent(this, introPage.class);

        startActivity(intent);
    }
    public void strenghts(View view) {
        Intent intent = new Intent(this, the24Strenght.class);

        startActivity(intent);
    }

    public void tec(View view){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("https://www.tec.dk/"));
        startActivity(intent);
    }

    public void vfvoe(View view){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("https://videnscenterportalen.dk/vtoe/"));
        startActivity(intent);
    }

    public void mindhelper(View view){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse("https://mindhelper.dk/"));
        startActivity(intent);
    }

}