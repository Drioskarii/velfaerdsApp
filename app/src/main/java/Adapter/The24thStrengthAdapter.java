package Adapter;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dk.tec.velfaerdsapp.R;
import dk.tec.velfaerdsapp.QuestionBoxes;
import dk.tec.velfaerdsapp.The24Strength;
import dk.tec.velfaerdsapp.The24StrengthsBoxes;

import static android.content.Context.MODE_PRIVATE;

public class The24thStrengthAdapter {
    private final ArrayList<The24StrengthsBoxes> mThe24StrengthsBoxes;
    public static The24Strength the24Strength;

    public static class the24StrengthViewHolder extends RecyclerView.ViewHolder{

        public TextView mTxtExplanation;
        public ImageView mImageIcon;
        public ImageView questionsConfirm;
        public SeekBar mSeekBar;

        public the24StrengthViewHolder(@NonNull View itemView) {
            super(itemView);

            mImageIcon = itemView.findViewById(R.id.imageIcon);
            mTxtExplanation = itemView.findViewById(R.id.txtExplanation);
            questionsConfirm = itemView.findViewById(R.id.questionsConfirm);
            mSeekBar = itemView.findViewById(R.id.seekBar);
            mSeekBar.setProgress(3);
            mSeekBar.setMax(5);

            //Get SharedPreference shared_Pref myKey.xml
            SharedPreferences sharedPref = mSeekBar.getContext().getSharedPreferences("questionArray", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();



            mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    int progress = mSeekBar.getProgress();
                    String id = String.valueOf(mTxtExplanation.getText());
                    //Insert data into the SharedPreferences
                    String s1 = sharedPref.getString(String.valueOf(mTxtExplanation.getText()),"");
                    if (s1.isEmpty()){
                        questionsConfirm.setImageResource(R.drawable.ic_baseline_check_circle_20);
                        questionsPage.answered++;
                        questionsPage.questionsProgressBar.setProgress(questionsPage.answered);
                    }
                    editor.putString(id+"_answer", String.valueOf  (progress));
                    editor.putString(id, id);
                    editor.apply();
                }
            });
        }
    }

    public The24thStrengthAdapter(ArrayList<The24StrengthsBoxes> <the24StrengthsBoxesList){
        mThe24StrengthsBoxes = mThe24StrengthsBoxes;
    }

    @NonNull
    @Override
    public QuestionViewAdapter.questionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.questions_item, parent, false);
        QuestionViewAdapter.questionViewHolder qvh = new QuestionViewAdapter.questionViewHolder(v);

        return qvh;
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewAdapter.questionViewHolder holder, int position) {
        QuestionBoxes currentItem = mThe24StrengthsBoxes.get(position);


        holder.mImageIcon.setImageResource(currentItem.getImageIcon());
        holder.mTxtExplanation.setText(currentItem.getTxtExplanation());

        //get view from seekbar id
        View view = holder.itemView.findViewById(holder.mSeekBar.getId());

        //Get SharedPreference shared_Pref myKey.xml
        SharedPreferences sharedPref = view.getContext().getSharedPreferences("questionArray", MODE_PRIVATE);

        //get data stored from seekBar and insert into
        String s1 = sharedPref.getString(String.valueOf(holder.mTxtExplanation.getText()+"_answer"),"");
        if (!s1.isEmpty()){
            //insert data if empty
            holder.questionsConfirm.setImageResource(R.drawable.ic_baseline_check_circle_20);
            holder.mSeekBar.setProgress(Integer.parseInt(s1));
        }
    }

    @Override
    public int getItemCount() {
        return mThe24StrengthsBoxes.size();
    }
}
