package dk.tec.velfaerdsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import Adapter.SelectAdapter;
import Adapter.SelectAvatarAdapter;
import Strengths.Points;

public class SelectAvatar extends TouchActivityHandler {

 //////////////////////////////////////////////////////////
//Her bliver point talt op og soteret
//Og dataen bliver sendt til SelectAvatar
//////////////////////////////////////////////////////////
    private static final String TAG = "selectAvatar";
    Button btnBack, btnForward;
    ArrayList<Points> goodSelected = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_avatar);
        btnBack = findViewById(R.id.btn_selectAvatar_back);
        btnForward = findViewById(R.id.btn_selectAvatar_forward);

        goodSelected = getIntent().getParcelableArrayListExtra("goodSelectedList");

        initRecyclerView();

        //her er tilbage knappen
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePage();
            }
        });

        //her er fremad knappen.
        btnForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (true){
                Intent intent = new Intent(SelectAvatar.this, ResultPage.class);
                //Den valgte avatar bliver puttet ind i et intent
                intent.putParcelableArrayListExtra("singleSelected", SelectAvatarAdapter.goodSelected);
                // denne liste bliver sendt vidre
                intent.putParcelableArrayListExtra("goodSelectedList", goodSelected);
                startActivity(intent);
                } else{
                    Toast.makeText(SelectAvatar.this, "vælg din avatar for at fortsætte", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @SuppressLint("NewApi")
    private void initRecyclerView() {
        //Her bliver Recyclerviewet oprettet
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),1);
        RecyclerView recyclerViewGood = findViewById(R.id.recycleViewSelectAvatar);
        recyclerViewGood.setLayoutManager(gridLayoutManager);

        //her bliver dataen sendt til SelectAvatarAdapter
        SelectAvatarAdapter goodAdapter = new SelectAvatarAdapter(this, goodSelected, true);
        recyclerViewGood.setAdapter(goodAdapter);
    }
}