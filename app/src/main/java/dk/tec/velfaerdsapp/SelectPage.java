package dk.tec.velfaerdsapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.GridLayoutManager;
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
//////////////////////////////////////////////////////////
//Her bliver point talt op og soteret
//Og dataen bliver sendt til SelectAdapter
//Der er nogle Clears i programmet og de er det for at sikre sig at det forkerte data ikke bliver sendt flere gange
//////////////////////////////////////////////////////////

    private static final String TAG = "selectPage";

    //vars
    Button btnBack, btnForward;
    List<Points> points = new ArrayList<Points>();

    ArrayList<Points> strengths = new ArrayList<>();
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //her bliver dataen i strengths slettet for at sikre at den gamle data ikke bliver sendt flere gange
        strengths.clear();
        setContentView(R.layout.activity_select_page);
        btnBack = findViewById(R.id.btn_select_back);
        btnForward = findViewById(R.id.btn_select_forward);
        getLists();
        getListPoints();
        addAndSort();
        getGoodImages();

        TextView txtSelectDinAvatar = findViewById(R.id.txtSelectDinAvatar);
        txtSelectDinAvatar.setText(gJob + " " + gName);

        //Her lukker du siden
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePage();
            }
        });

        //Her bliver der tjekket hvis der er 2 af elementerne på SelectPagen som er valgt
        btnForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SelectAdapter.goodConfirmCounter == 2){
                    Intent intent;
                    if (SelectAdapter.goodSelected.get(0).getPoints() == SelectAdapter.goodSelected.get(1).getPoints()) {
                        intent = new Intent(SelectPage.this, SelectAvatar.class);
                    }else{
                        intent = new Intent(SelectPage.this, ResultPage.class);
                    }
                    //goodSelecter bliver puttet ind i et array og bliver sendre vidre
                    intent.putParcelableArrayListExtra("goodSelectedList", SelectAdapter.goodSelected);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(SelectPage.this, "vælg 2 styrker for at fortsætte", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //her henter den listerne fra et intent
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

    //Her henter den svarende
    private void getListPoints(){
        for (int i = 0; i < mod.size(); i++){
            modPoints = modPoints + mod.get(i).getAnswer();
        }
        for (int i = 0; i < nys.size(); i++){
            nysPoints = nysPoints + nys.get(i).getAnswer();
        }
        for (int i = 0; i < bes.size(); i++){
            besPoints = besPoints + bes.get(i).getAnswer();
        }
        for (int i = 0; i < tak.size(); i++){
            takPoints = takPoints + tak.get(i).getAnswer();
        }
        for (int i = 0; i < sam.size(); i++){
            samPoints = samPoints + sam.get(i).getAnswer();
        }
        for (int i = 0; i < soc.size(); i++){
            socPoints = socPoints + soc.get(i).getAnswer();
        }

        //Her regner den gennemsnitted ud og indsætter dem ind i deres variabler
        modPoints = modPoints / mod.size();
        nysPoints = nysPoints / nys.size();
        besPoints = besPoints / bes.size();
        takPoints = takPoints / tak.size();
        samPoints = samPoints / tak.size();
        socPoints = socPoints / soc.size();
    }

    private void addAndSort(){
        //her putter du værdierne ind i deres objekter
        Points p1 = new Points("Mod", "Du er ikke bange for at kaste dig ud i nye udfordringer. Du siger din mening og tør at gå dine egne veje.", modPoints, R.drawable.iconmod, 20);
        Points p2 = new Points("Nysgerrighed ", "Du stiller altid spørgsmål og er god til fordybe dig. Ligesom Spørge Jørgen, der altid spørger “hvorfor dit og hvorfor dat”.", nysPoints, R.drawable.iconnysgerrig, 20);
        Points p3 = new Points("Beskedenhed", "Du er ikke “Se mig! Se mig!”-typen. Heller ikke når du er for sej og alting kører for dig.", besPoints, R.drawable.iconbeskedenhed, 20);
        Points p4 = new Points("Taknemmelighed", "Du sætter pris på både det store og de små ting i livet og ‘Tak’ er et ord, du bruger rigtig tit. Folk omkring dig ved, at de betyder noget for dig.", takPoints, R.drawable.icontaknemmelighed,25);
        Points p5 = new Points("Samarbejde", "Andre kan altid regne med dig. Du er god til at få gruppearbejde til at fungere og nyder fællesskaber.", samPoints, R.drawable.iconsamarbejde, 20);
        Points p6 = new Points("Social Intelligens", "Du er god til at sætte dig ind i andres tanker og idéer. Folk omkring dig føler sig godt tilpas i dit selskab.", socPoints, R.drawable.iconsocialintelligens, 15);
        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.add(p4);
        points.add(p5);
        points.add(p6);

        //her bliver det soteret det højeste tal først
        Collections.sort(points, new Comparator<Points>(){
            public int compare(Points obj1, Points obj2) {
                return Integer.valueOf(obj2.getPoints()).compareTo(Integer.valueOf(obj1.getPoints()));
            }
        });

        Points g5 = new Points(points.get(5).getTitle(), points.get(5).getDescription(), points.get(5).getPoints(), points.get(5).getIcon(), points.get(5).getMaxpoints());
        Points g4 = new Points(points.get(4).getTitle(), points.get(4).getDescription(), points.get(4).getPoints(), points.get(4).getIcon(), points.get(4).getMaxpoints());
        Points g3 = new Points(points.get(3).getTitle(), points.get(3).getDescription(), points.get(3).getPoints(), points.get(3).getIcon(), points.get(3).getMaxpoints());
        Points g2 = new Points(points.get(2).getTitle(), points.get(2).getDescription(), points.get(2).getPoints(), points.get(2).getIcon(), points.get(2).getMaxpoints());
        Points g1 = new Points(points.get(1).getTitle(), points.get(1).getDescription(), points.get(1).getPoints(), points.get(1).getIcon(), points.get(1).getMaxpoints());
        Points g0 = new Points(points.get(0).getTitle(), points.get(0).getDescription(), points.get(0).getPoints(), points.get(0).getIcon(), points.get(0).getMaxpoints());
        strengths.add(g0);
        strengths.add(g1);
        strengths.add(g2);
        if (points.get(3).getPoints() == 5){
            strengths.add(g3);
        }
        if (points.get(4).getPoints() == 5){
            strengths.add(g4);
        }
        if (points.get(5).getPoints() == 5) {
            strengths.add(g5);
        }
    }

    private void getGoodImages() {
        initRecyclerView();
    }


    @SuppressLint("NewApi")
    private void initRecyclerView() {
        //Her bliver Recyclerviewet oprettet
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),1);
        RecyclerView recyclerViewGood = findViewById(R.id.recyclerViewGood);
        recyclerViewGood.setLayoutManager(gridLayoutManager);

        //her bliver dataen sendt til SelectAdapter
        SelectAdapter goodAdapter = new SelectAdapter(this, strengths, true);
        recyclerViewGood.setAdapter(goodAdapter);
    }
}