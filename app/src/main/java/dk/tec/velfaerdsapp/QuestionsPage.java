package dk.tec.velfaerdsapp;

import Adapter.QuestionsAdapter;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

//import com.google.android.material.slider.Slider;


public class QuestionsPage extends TouchActivityHandler {

    private static final String TAG = "questionsPage";

    public static ImageView imageViewMod, imageViewNys, imageViewBes, imageViewTak, imageViewSam, imageViewSoc;
    public static TextView trophyTxt;
    public static ProgressBar questionsProgressBar;
    public static int count;
    public static int answeredCount;
    public static int modPoints, nysPoints, besPoints, takPoints, samPoints, socPoints;
    public static int modMax = 15, nysMax = 15, besMax = 15, takMax = 15, samMax = 15, socMax = 15;
    ListView listOfQuestions;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_page);
        questionsProgressBar = findViewById(R.id.questionsProgressBar);
        listOfQuestions = findViewById(R.id.listOfQuestions);
        initiate();

        QuestionsAdapter questionsAdapter = new QuestionsAdapter(QuestionsPage.this, Strengths.getQuestionList());
        listOfQuestions.setAdapter(questionsAdapter);

        for (int i = 0; i<QuestionsAdapter.strengths.size(); i++){
            SharedPreferences sharedPref = getSharedPreferences("questionArray", MODE_PRIVATE);
            String s1 = sharedPref.getString(QuestionsAdapter.strengths.get(i).getIdentity(),"");
            if (!s1.isEmpty()){
                setModPointsPlus((Strengths)questionsAdapter.getItem(i), Integer.parseInt(s1));
            }
            else{
                setModPointsPlus((Strengths)questionsAdapter.getItem(i), 3);
            }
        }

        count = questionsAdapter.getCount();
        questionsProgressBar.setMax(questionsAdapter.getCount());
        questionsProgressBar.setProgress(answeredCount);
        checkPoints();
    }

    public static void setModPointsPlus(Strengths strengths, int value){
        if (strengths.getIdentity().contains("mod")){modPoints = modPoints + value;}
        if (strengths.getIdentity().contains("nys")){nysPoints = nysPoints + value;}
        if (strengths.getIdentity().contains("bes")){besPoints = besPoints + value;}
        if (strengths.getIdentity().contains("tak")){takPoints = takPoints + value;}
        if (strengths.getIdentity().contains("sam")){samPoints = samPoints + value;}
        if (strengths.getIdentity().contains("soc")){socPoints = socPoints + value;}
    }

    public static void setModPointsMinus(Strengths strengths, int value){
        if (strengths.getIdentity().contains("mod")){modPoints = modPoints - value;}
        if (strengths.getIdentity().contains("nys")){nysPoints = nysPoints - value;}
        if (strengths.getIdentity().contains("bes")){besPoints = besPoints - value;}
        if (strengths.getIdentity().contains("tak")){takPoints = takPoints - value;}
        if (strengths.getIdentity().contains("sam")){samPoints = samPoints - value;}
        if (strengths.getIdentity().contains("soc")){socPoints = socPoints - value;}
    }

    public void initiate(){
        trophyTxt = findViewById(R.id.txtTrophy);
        imageViewMod = findViewById(R.id.trophyMod);
        imageViewNys = findViewById(R.id.trophyNys);
        imageViewBes = findViewById(R.id.trophyBes);
        imageViewTak = findViewById(R.id.trophyTak);
        imageViewSam = findViewById(R.id.trophySam);
        imageViewSoc = findViewById(R.id.trophySoc);
        modPoints = 0;
        nysPoints = 0;
        besPoints = 0;
        takPoints = 0;
        samPoints = 0;
        socPoints = 0;
    }

    public static void checkPoints(){
        trophyTxt.setVisibility(View.VISIBLE);
        imageViewMod.setVisibility(View.VISIBLE);
        imageViewNys.setVisibility(View.VISIBLE);
        imageViewBes.setVisibility(View.VISIBLE);
        imageViewTak.setVisibility(View.VISIBLE);
        imageViewSam.setVisibility(View.VISIBLE);
        imageViewSoc.setVisibility(View.VISIBLE);

        if (modPoints >= modMax || nysPoints >= nysMax || besPoints >= besMax || takPoints >= takMax || samPoints >= samMax || socPoints >= socMax){ trophyTxt.setVisibility(View.VISIBLE);}
        else {trophyTxt.setVisibility(View.GONE);}
        if (modPoints >= modMax){ imageViewMod.setVisibility(View.VISIBLE);}
        else{imageViewMod.setVisibility(View.GONE);}
        if (nysPoints >= nysMax){ imageViewNys.setVisibility(View.VISIBLE);}
        else{imageViewNys.setVisibility(View.GONE);}
        if (besPoints >= besMax){imageViewBes.setVisibility(View.VISIBLE);}
        else{imageViewBes.setVisibility(View.GONE);}
        if (takPoints >= takMax){imageViewTak.setVisibility(View.VISIBLE);}
        else{imageViewTak.setVisibility(View.GONE);}
        if (samPoints >= samMax){imageViewSam.setVisibility(View.VISIBLE);}
        else{imageViewSam.setVisibility(View.GONE);}
        if (socPoints >= socMax){ imageViewSoc.setVisibility(View.VISIBLE);}
        else{ imageViewSoc.setVisibility(View.GONE);}
    }
}