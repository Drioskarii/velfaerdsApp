package QuestionsAdapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import Strengths.Strengths;
import dk.tec.velfaerdsapp.ModPage;
import dk.tec.velfaerdsapp.R;

import static android.content.Context.MODE_PRIVATE;

public class ModAdapter extends BaseAdapter {

    private static final String TAG = "QuestionsAdapter";

    public static ArrayList<Strengths> strengths;
    private Context mContext;

    public ModAdapter(Context context, ArrayList<Strengths> strengths){
        mContext = context;
        this.strengths = strengths;
    }

    @Override
    public int getCount() {
        return strengths.size();
    }

    @Override
    public Object getItem(int position) {
        return strengths.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {

        itemView = LayoutInflater.from(mContext).inflate(R.layout.questions_item, parent, false);
        Strengths tempStrengths = (Strengths) getItem(position);

        TextView mTxtTitle = itemView.findViewById(R.id.questions_txtTitle);
        TextView mTxtQuestion = itemView.findViewById(R.id.questions_txtQuestion);
        ImageView questionsConfirm = itemView.findViewById(R.id.questionsConfirm);
        SeekBar mSeekBar = itemView.findViewById(R.id.seekBar);
        questionsConfirm.setImageResource(R.drawable.ic_baseline_remove_circle_20);
        mSeekBar.setProgress(tempStrengths.getAnswer());
        mSeekBar.setMax(5);

        mTxtTitle.setText(tempStrengths.getTitle());
        mTxtQuestion.setText(tempStrengths.getQuestion());

        SharedPreferences sharedPref = mSeekBar.getContext().getSharedPreferences("questionArray", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        //get data stored from seekBar and insert into
        String s1 = sharedPref.getString(tempStrengths.getIdentity(),"");
        if (!s1.isEmpty()){
            //insert data if not empty
            questionsConfirm.setImageResource(R.drawable.ic_baseline_check_circle_20);
            mSeekBar.setProgress(Integer.parseInt(s1));
            ModPage.checkPoints();
        }

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) { }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                tempStrengths.setAnswer(progress);
                //Insert data into the SharedPreferences
                if (strengths.contains(tempStrengths)) {
                    strengths.set(position,tempStrengths);
                }

                String s1 = sharedPref.getString(tempStrengths.getIdentity(),"");
                if (s1.isEmpty()){
                    questionsConfirm.setImageResource(R.drawable.ic_baseline_check_circle_20);
                    ModPage.answeredCount++;
                    ModPage.questionsProgressBar.setProgress(ModPage.answeredCount);
                }
                ModPage.checkPoints();


                editor.putString(tempStrengths.getIdentity(), String.valueOf  (progress));
                editor.apply();
            }
        });
        ModPage.checkPoints();
        return itemView;
    }

    public static int dpToPx(int dp, Context context){
        return dp * ((int) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
}
