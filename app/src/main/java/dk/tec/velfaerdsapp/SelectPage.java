package dk.tec.velfaerdsapp;

import android.annotation.SuppressLint;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Adapter.SelectAdapter;
import Strengths.Strengths;
import Strengths.Points;

public class SelectPage extends TouchActivityHandler {


    private static final String TAG = "selectPage";
    //vars
    List<Points> points = new ArrayList<Points>();
    ArrayList<Strengths> strengths = new ArrayList<>();
    ArrayList<Strengths> weaknesses = new ArrayList<>();
    ArrayList<Strengths> mod = new ArrayList<>();
    ArrayList<Strengths> nys = new ArrayList<>();
    ArrayList<Strengths> bes = new ArrayList<>();
    ArrayList<Strengths> tak = new ArrayList<>();
    ArrayList<Strengths> sam = new ArrayList<>();
    ArrayList<Strengths> soc = new ArrayList<>();
    int modPoints;
    int nysPoints;
    int besPoints;
    int takPoints;
    int samPoints;
    int socPoints;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_page);
        getLists();
        getListPoints();
        addAndSort();
        getGoodImages();
        getBadImages();

        TextView txtSelectDinAvatar = findViewById(R.id.txtSelectDinAvatar);
        txtSelectDinAvatar.setText(gJob + " " + gName);
    }

    private void getLists(){
        for (Parcelable item : getIntent().getParcelableArrayListExtra("ModList")){
            mod.add((Strengths)item);
        }
        for (Parcelable item : getIntent().getParcelableArrayListExtra("NysList")){
            nys.add((Strengths)item);
        }
        for (Parcelable item : getIntent().getParcelableArrayListExtra("BesList")){
            bes.add((Strengths)item);
        }
        for (Parcelable item : getIntent().getParcelableArrayListExtra("TakList")){
            tak.add((Strengths)item);
        }
        for (Parcelable item : getIntent().getParcelableArrayListExtra("SamList")){
            sam.add((Strengths)item);
        }
        for (Parcelable item : getIntent().getParcelableArrayListExtra("SocList")){
            soc.add((Strengths)item);
        }
    }

    private void getListPoints(){
        for (int i = 0; i < mod.size(); i++){
            modPoints = modPoints + mod.get(i).getAnswer();
            Log.d(TAG, "Mod "+modPoints);
        }
        for (int i = 0; i < nys.size(); i++){
            nysPoints = nysPoints + nys.get(i).getAnswer();
            Log.d(TAG, "Nys "+nysPoints);
        }
        for (int i = 0; i < bes.size(); i++){
            besPoints = besPoints + bes.get(i).getAnswer();
            Log.d(TAG, "Bes "+besPoints);
        }
        for (int i = 0; i < tak.size(); i++){
            takPoints = takPoints + tak.get(i).getAnswer();
            Log.d(TAG, "Tak "+takPoints);
        }
        for (int i = 0; i < sam.size(); i++){
            samPoints = samPoints + sam.get(i).getAnswer();
            Log.d(TAG, "Sam "+samPoints);
        }
        for (int i = 0; i < soc.size(); i++){
            socPoints = socPoints + soc.get(i).getAnswer();
            Log.d(TAG, "Soc "+socPoints);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addAndSort(){
        Points p1 = new Points("Mod", "Du tør være dig selv", modPoints, R.drawable.iconmod);
        Points p2 = new Points("Nysgerrighed ", "Du tænker over tingene", nysPoints, R.drawable.iconnysgerrig);
        Points p3 = new Points("Beskedenhed", "Du er disciplineret", besPoints, R.drawable.iconbeskedenhed);
        Points p4 = new Points("Taknemmelighed", "Verden er større end dig", takPoints, R.drawable.icontaknemmelighed);
        Points p5 = new Points("Samarbejde", "Du tænker på fællesskabet", samPoints, R.drawable.iconsamarbejde);
        Points p6 = new Points("Social Intelligens", "Du er noget for andre", socPoints, R.drawable.iconsocialintelligens);
        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.add(p4);
        points.add(p5);
        points.add(p6);
        Collections.sort(points, Comparator.comparing(Points::getPoints));

        Strengths g1 = new Strengths("strengths1", points.get(5).getTitle(), points.get(5).getQuestion(), points.get(5).getPoints());
        Strengths g2 = new Strengths("strengths2", points.get(4).getTitle(), points.get(4).getQuestion(), points.get(4).getPoints());
        Strengths g3 = new Strengths("strengths3", points.get(3).getTitle(), points.get(3).getQuestion(), points.get(3).getPoints());
        Strengths b1 = new Strengths("weakness", points.get(0).getTitle(), points.get(0).getQuestion(), points.get(0).getPoints());
        strengths.add(g1);
        strengths.add(g2);
        strengths.add(g3);
        weaknesses.add(b1);
    }

    private void getGoodImages() {
        initRecyclerView();
    }

    private void getBadImages() {
        initRecyclerView();
    }

    @SuppressLint("NewApi")
    private void initRecyclerView() {
        LinearLayoutManager layoutManagerGood = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerViewGood = findViewById(R.id.recyclerViewGood);
        recyclerViewGood.setLayoutManager(layoutManagerGood);

        SelectAdapter goodAdapter = new SelectAdapter(this, strengths, true);
        recyclerViewGood.setAdapter(goodAdapter);


        LinearLayoutManager layoutManagerBad = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerViewBad = findViewById(R.id.recyclerViewBad);
        recyclerViewBad.setLayoutManager(layoutManagerBad);

        SelectAdapter badAdapter = new SelectAdapter(this, weaknesses, false);
        recyclerViewBad.setAdapter(badAdapter);

    }
}