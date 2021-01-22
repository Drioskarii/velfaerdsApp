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

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePage();
            }
        });

        btnForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (true){
                Intent intent = new Intent(SelectAvatar.this, ResultPage.class);
                intent.putParcelableArrayListExtra("goodSelectedList", SelectAdapter.goodSelected);
                //intent.putParcelableArrayListExtra("badSelectedList", SelectAdapter.badSelected);
                startActivity(intent);
                } else{
                    Toast.makeText(SelectAvatar.this, "vælg din avatar for at fortsætte", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @SuppressLint("NewApi")
    private void initRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),1);
        RecyclerView recyclerViewGood = findViewById(R.id.recycleViewSelectAvatar);
        recyclerViewGood.setLayoutManager(gridLayoutManager);

        //SelectAvatarAdapter goodAdapter = new SelectAvatarAdapter(this, goodSelected, true);
        //recyclerViewGood.setAdapter(goodAdapter);
    }
}