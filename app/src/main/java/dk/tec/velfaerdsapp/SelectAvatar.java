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
    ArrayList<Points> topFive = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_avatar);
        btnBack = findViewById(R.id.btn_selectAvatar_back);
        btnForward = findViewById(R.id.btn_selectAvatar_forward);

        goodSelected = getIntent().getParcelableArrayListExtra("goodSelectedList");
        topFive = getIntent().getParcelableArrayListExtra("topFive");
        System.out.println(topFive);
        System.out.println("Above is topFive");
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
                if (SelectAvatarAdapter.goodConfirmCounter == 1){
                Intent intent = new Intent(SelectAvatar.this, ResultPage.class);
                    if (SelectAvatarAdapter.goodSelected.get(0).getTitle().contains("Mod")){ if (gKøn == 1){gAvatar = R.drawable.tndmand_mod;} else{gAvatar = R.drawable.tndkvinde_mod;}}
                    if (SelectAvatarAdapter.goodSelected.get(0).getTitle().contains("Nys")){ if (gKøn == 1){gAvatar = R.drawable.tndmand_nys;} else{gAvatar = R.drawable.tndkvinde_nys;}}
                    if (SelectAvatarAdapter.goodSelected.get(0).getTitle().contains("Bes")){ if (gKøn == 1){gAvatar = R.drawable.tndmand_bes;} else{gAvatar = R.drawable.tndkvinde_bes;}}
                    if (SelectAvatarAdapter.goodSelected.get(0).getTitle().contains("Tak")){ if (gKøn == 1){gAvatar = R.drawable.tndmand_tak;} else{gAvatar = R.drawable.tndkvinde_tak;}}
                    if (SelectAvatarAdapter.goodSelected.get(0).getTitle().contains("Sam")){ if (gKøn == 1){gAvatar = R.drawable.tndmand_sam;} else{gAvatar = R.drawable.tndkvinde_sam;}}
                    if (SelectAvatarAdapter.goodSelected.get(0).getTitle().contains("Soc")){ if (gKøn == 1){gAvatar = R.drawable.tndmand_soc;} else{gAvatar = R.drawable.tndkvinde_soc;}}
                    // denne liste bliver sendt vidre
                    intent.putParcelableArrayListExtra("goodSelectedList", goodSelected);
                    intent.putParcelableArrayListExtra("topFive", topFive);
                startActivity(intent);
                } else{
                    Toast.makeText(SelectAvatar.this, "vælg din avatar for at fortsætte", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

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