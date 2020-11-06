package dk.tec.velfaerdsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import Adapter.wheelCycleAdapter;
import data.MenuItemData;
import github.hellocsl.cursorwheel.CursorWheelLayout;

public class selectPage extends AppCompatActivity implements CursorWheelLayout.OnMenuSelectedListener{
    CursorWheelLayout wheel_text;
    List<MenuItemData> lstText ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_page);


        initViews();
        loadData();

        wheel_text.setOnMenuSelectedListener(this);
    }

    private void loadData() {
        lstText = new ArrayList<>();
        for (int i = 0;i < 9;i++)
            lstText.add(new MenuItemData("" + i));
        lstText.add(new MenuItemData("OFF"));
        wheelCycleAdapter adapter = new wheelCycleAdapter(getBaseContext(), lstText);
        wheel_text.setAdapter(adapter);

    }

    private void initViews(){
        wheel_text = (CursorWheelLayout) findViewById(R.id.wheel_text);
}


    public void forward(View view) {
        Intent intent = new Intent(this, emailPage.class);

        startActivity(intent);
    }

    public void back(View view) {
        Intent intent = new Intent(this, questionsPage.class);

        startActivity(intent);
    }


    @Override
    public void onItemSelected(CursorWheelLayout parent, View view, int pos) {
        if(parent.getId() == R.id.wheel_text);
            Toast.makeText(getBaseContext(), "Selected: "+
                    lstText.get(pos), Toast.LENGTH_SHORT).show();
    }



}