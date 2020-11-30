package dk.tec.velfaerdsapp;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class ResultPage extends TouchActivityHandler{

    private static final String TAG = "ResultatPage";
    //Initialising
    AnimationDrawable animation;
    ImageView characterPlaceholder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);

        characterPlaceholder = findViewById(R.id.characterPlaceholder);
        characterPlaceholder.setBackgroundResource(R.drawable.animation);
        animation = (AnimationDrawable) characterPlaceholder.getBackground();

    }







    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        animation.start();
    }
}