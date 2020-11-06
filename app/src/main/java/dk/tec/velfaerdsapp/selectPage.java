package dk.tec.velfaerdsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Adapter.questionViewAdapter;
import Adapter.selectViewAdapter;


public class selectPage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_page);

        // select list good
        ArrayList<selectBoxes> selectListGood = new ArrayList<>();
        selectListGood.add(new selectBoxes(R.drawable.intellectual, "SelectGood 1"));
        selectListGood.add(new selectBoxes(R.drawable.people_person, "SelectGood 2"));
        selectListGood.add(new selectBoxes(R.drawable.sharp_brained, "SelectGood 3"));
        selectListGood.add(new selectBoxes(R.drawable.intellectual, "SelectGood 4"));
        selectListGood.add(new selectBoxes(R.drawable.people_person, "SelectGood 5"));

        RecyclerView mSelectRecyclerViewGood = findViewById(R.id.selectRecyclerGood);
        mSelectRecyclerViewGood.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManagerGood = new LinearLayoutManager(this);
        RecyclerView.Adapter mAdapterGood = new selectViewAdapter(selectListGood);

        mSelectRecyclerViewGood.setLayoutManager(mLayoutManagerGood);
        mSelectRecyclerViewGood.setAdapter(mAdapterGood);

        // select list bad
        ArrayList<selectBoxes> selectListBad = new ArrayList<>();
        selectListBad.add(new selectBoxes(R.drawable.intellectual, "SelectBad 5"));
        selectListBad.add(new selectBoxes(R.drawable.people_person, "SelectBad 2"));
        selectListBad.add(new selectBoxes(R.drawable.sharp_brained, "SelectBad 3"));
        selectListBad.add(new selectBoxes(R.drawable.intellectual, "SelectBad 4"));
        selectListBad.add(new selectBoxes(R.drawable.people_person, "SelectBad 5"));
        selectListBad.add(new selectBoxes(R.drawable.sharp_brained, "SelectBad 6"));

        RecyclerView mSelectRecyclerViewBad = findViewById(R.id.selectRecyclerBad);
        mSelectRecyclerViewBad.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManagerBad = new LinearLayoutManager(this);
        RecyclerView.Adapter mAdapterBad = new selectViewAdapter(selectListBad);

        mSelectRecyclerViewBad.setLayoutManager(mLayoutManagerBad);
        mSelectRecyclerViewBad.setAdapter(mAdapterBad);
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