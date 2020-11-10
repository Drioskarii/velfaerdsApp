package dk.tec.velfaerdsapp;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Adapter.customavataradapter;
import Adapter.questionViewAdapter;
import Adapter.selectViewAdapter;


public class selectPage extends AppCompatActivity {

    private static final String TAG = "selectpage";

    //vars
    private ArrayList<String> GoodNames = new ArrayList<>();
    private ArrayList<String> GoodselectmImageUrls = new ArrayList<>();

    private ArrayList<String> BadNames = new ArrayList<>();
    private ArrayList<String> BadselectmImageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_page);

//        // select list good
//        ArrayList<selectBoxes> selectListGood = new ArrayList<>();
//        selectListGood.add(new selectBoxes(R.drawable.intellectual, "SelectGood 1"));
//        selectListGood.add(new selectBoxes(R.drawable.people_person, "SelectGood 2"));
//        selectListGood.add(new selectBoxes(R.drawable.sharp_brained, "SelectGood 3"));
//        selectListGood.add(new selectBoxes(R.drawable.intellectual, "SelectGood 4"));
//        selectListGood.add(new selectBoxes(R.drawable.people_person, "SelectGood 5"));
//
//        RecyclerView mSelectRecyclerViewGood = findViewById(R.id.selectRecyclerGood);
//        mSelectRecyclerViewGood.setHasFixedSize(true);
//        RecyclerView.LayoutManager mLayoutManagerGood = new LinearLayoutManager(this);
//        RecyclerView.Adapter mAdapterGood = new selectViewAdapter(selectListGood);
//
//        mSelectRecyclerViewGood.setLayoutManager(mLayoutManagerGood);
//        mSelectRecyclerViewGood.setAdapter(mAdapterGood);
//
//        // select list bad
//        ArrayList<selectBoxes> selectListBad = new ArrayList<>();
//        selectListBad.add(new selectBoxes(R.drawable.intellectual, "SelectBad 5"));
//        selectListBad.add(new selectBoxes(R.drawable.people_person, "SelectBad 2"));
//        selectListBad.add(new selectBoxes(R.drawable.sharp_brained, "SelectBad 3"));
//        selectListBad.add(new selectBoxes(R.drawable.intellectual, "SelectBad 4"));
//        selectListBad.add(new selectBoxes(R.drawable.people_person, "SelectBad 5"));
//        selectListBad.add(new selectBoxes(R.drawable.sharp_brained, "SelectBad 6"));
//
//        RecyclerView mSelectRecyclerViewBad = findViewById(R.id.selectRecyclerBad);
//        mSelectRecyclerViewBad.setHasFixedSize(true);
//        RecyclerView.LayoutManager mLayoutManagerBad = new LinearLayoutManager(this);
//        RecyclerView.Adapter mAdapterBad = new selectViewAdapter(selectListBad);
//
//        mSelectRecyclerViewBad.setLayoutManager(mLayoutManagerBad);
//        mSelectRecyclerViewBad.setAdapter(mAdapterBad);

        getGoodImages();
        getBadImages();
    }

    private void getGoodImages(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        GoodselectmImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        GoodNames.add("Good 1");

        GoodselectmImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        GoodNames.add("Good 2");

        GoodselectmImageUrls.add("https://i.redd.it/j6myfqglup501.jpg");
        GoodNames.add("Good 3");

        GoodselectmImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        GoodNames.add("Good 4");

        GoodselectmImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg");
        GoodNames.add("Good 5");

        initRecyclerView();
    }

    private void getBadImages(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        BadselectmImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        BadNames.add("Bad 1");

        BadselectmImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        BadNames.add("Bad 2");

        BadselectmImageUrls.add("https://i.redd.it/j6myfqglup501.jpg");
        BadNames.add("Bad 3");

        BadselectmImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        BadNames.add("Bad 4");

        BadselectmImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg");
        BadNames.add("Bad 5");

        initRecyclerView();

    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview");

        LinearLayoutManager layoutManagerGood = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerViewGod = findViewById(R.id.recyclerViewGood);
        recyclerViewGod.setLayoutManager(layoutManagerGood);
        customavataradapter Goodadapter = new customavataradapter(this, GoodNames, GoodselectmImageUrls);
        recyclerViewGod.setAdapter(Goodadapter);

        LinearLayoutManager layoutManagerBad = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerViewBad = findViewById(R.id.recyclerViewBad);
        recyclerViewBad.setLayoutManager(layoutManagerBad);
        customavataradapter Badadapter = new customavataradapter(this, BadNames, BadselectmImageUrls);
        recyclerViewBad.setAdapter(Badadapter);
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