package dk.tec.velfaerdsapp;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

import Adapter.QuestionsAdapter;
import Adapter.SelectAdapter;


public class SelectPage extends TouchActivityHandler {

    public static ProgressBar goodProgressBar;
    public static ProgressBar badProgressBar;
    private static final String TAG = "selectPage";
    //vars
    private ArrayList<String> questions = new ArrayList<>();
    private ArrayList<String> answers = new ArrayList<>();
    private ArrayList<String> selectImageUrls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_page);

        getImages();
        getGoodImages();
        getBadImages();


    }

    public class question {
        String name;
        String formula;
    }

    private void getImages(){
        int size = QuestionsAdapter.answers.size();
        int i = 0;
        Iterator itr = QuestionsAdapter.answers.iterator();
        while (i < size ){
/*            System.out.println(size + "Test");
            System.out.println(i);*/

            //Den her metode virker kun med billeder som ikke er i xml form
            selectImageUrls.add(String.valueOf(Uri.parse("android.resource://dk.tec.velfaerdsapp/"+ QuestionsAdapter.answers.get(i).getIcon())));
            questions.add(QuestionsAdapter.answers.get(i).getQuestion());
            answers.add(String.valueOf(QuestionsAdapter.answers.get(i).getAnswer()));

/*          System.out.println(QuestionsAdapter.answers.get(i).getIcon());
            System.out.println(QuestionsAdapter.answers.get(i).getQuestion());
            System.out.println(QuestionsAdapter.answers.get(i).getAnswer());*/
            i++;
        }



    }


    private void getGoodImages() {
       // badProgressBar = findViewById(R.id.badProgressBar);
       // badProgressBar.setProgress(badAdapter.getCount());
        initRecyclerView();




    }

    private void getBadImages() {
       // goodProgressBar = findViewById(R.id.goodProgressBar);
       // goodProgressBar.setProgress(goodAdapter.getCount());
        initRecyclerView();

    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManagerGood = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerViewGood = findViewById(R.id.recyclerViewGood);
        recyclerViewGood.setLayoutManager(layoutManagerGood);
        SelectAdapter goodAdapter = new SelectAdapter(this, questions, answers, selectImageUrls);

        recyclerViewGood.setAdapter(goodAdapter);




        LinearLayoutManager layoutManagerBad = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerViewBad = findViewById(R.id.recyclerViewBad);
        recyclerViewBad.setLayoutManager(layoutManagerBad);
        SelectAdapter badAdapter = new SelectAdapter(this, questions, answers, selectImageUrls);
        recyclerViewBad.setAdapter(badAdapter);



    }
}