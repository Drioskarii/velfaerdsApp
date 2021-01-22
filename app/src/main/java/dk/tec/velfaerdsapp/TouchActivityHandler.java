package dk.tec.velfaerdsapp;

import Adapter.ResultAdapter;
import QuestionsAdapter.BesAdapter;
import QuestionsAdapter.ModAdapter;
import QuestionsAdapter.NysAdapter;
import QuestionsAdapter.SamAdapter;
import Adapter.SelectAdapter;
import QuestionsAdapter.SocAdapter;
import QuestionsAdapter.TakAdapter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class TouchActivityHandler extends AppCompatActivity {

    public static String gName;
    public static String gJob;
    public static int gKøn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //public static int dpToPx(int dp, Context context){
    //    return dp * ((int) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    //}

    //Forward er altid swipe ( højre mod venstre )
    public Intent newPage(Context context, Class toClass) {
        return new Intent(context, toClass);
    }

    //Backward er altid swipe ( venstre mod højre )
    public void closePage() {
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}