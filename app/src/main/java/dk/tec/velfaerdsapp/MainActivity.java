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

    public void logoClicked(View view){
        String url = "";

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);

        switch(view.getId())
        {
            case R.id.imgLogoTec:
                url = "https://www.tec.dk/";
                break;
            case R.id.imgLogoVfvoe:
                url = "https://videnscenterportalen.dk/vtoe/";
                break;
            case R.id.imgLogoMindhelper:
                url = "https://mindhelper.dk/";
                break;
        }

        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

}