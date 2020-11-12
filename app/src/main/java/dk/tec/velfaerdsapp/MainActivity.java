package dk.tec.velfaerdsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void tomail(View view) //TEMP!!!!!!!!!!!!!!!!!!
    {
        Intent intent = new Intent(this, emailPage.class);

        startActivity(intent);
    }

    public void forward(View view)
    {
        Intent intent = new Intent(this, introPage.class);

        startActivity(intent);
    }

    public void strengths(View view)
    {
        Intent intent = new Intent(this, the24Strength.class);

        startActivity(intent);
    }

    public void logoClicked(View view)
    {
        String url = "";
        int id = view.getId();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);

        if(id == R.id.imgLogoVfvoe)
            url = "https://videnscenterportalen.dk/vtoe/";
        else if (id == R.id.imgLogoTec)
            url = "https://www.tec.dk/";
        else if (id == R.id.imgLogoUg)
            url = "https://www.ug.dk/";

        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}