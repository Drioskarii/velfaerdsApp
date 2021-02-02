package dk.tec.velfaerdsapp;

import Adapter.ResultAdapter;
import QuestionsAdapter.BesAdapter;
import QuestionsAdapter.ModAdapter;
import QuestionsAdapter.NysAdapter;
import QuestionsAdapter.SamAdapter;
import Adapter.SelectAdapter;
import QuestionsAdapter.SocAdapter;
import QuestionsAdapter.TakAdapter;
import Strengths.Points;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class TouchActivityHandler extends AppCompatActivity {

    //////////////////////////////////////////////////////////
    // TouchActivityHandler håndtere variabler og metoder der genbruges på diverse sider.
    // alle sider extender TouchActivityHandler, sådan at de alle kan tilgå metoderne
    //////////////////////////////////////////////////////////

    //gName indeholder personens navn fra IntroPage
    public static String gName;
    //gJob indeholder personens job fra IntroPage
    public static String gJob;
    //gKøn indeholder personens køn fra IntroPage
    public static int gKøn;

    public static int gAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //Denne metode bruges til at åbne nye sider.
    public Intent newPage(Context context, Class toClass) {
        return new Intent(context, toClass);
    }

    //Denne metode bruges til at lukke sider.
    public void closePage() {
        finish();
    }

    //Denne metode gør at hvis en person bruger tilbage knappen på deres telefon, så lukkes siden korrekt.
    @Override
    public void onBackPressed() {
        finish();
    }

}