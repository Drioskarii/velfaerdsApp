package Adapter;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dk.tec.velfaerdsapp.R;
import dk.tec.velfaerdsapp.QuestionBoxes;
import dk.tec.velfaerdsapp.QuestionsPage;

import static android.content.Context.MODE_PRIVATE;

public class QuestionViewAdapter extends RecyclerView.Adapter<QuestionViewAdapter.questionViewHolder> {

    private final ArrayList<QuestionBoxes> mQuestionBoxes;
    public static QuestionsPage questionsPage;

   public static class questionViewHolder extends RecyclerView.ViewHolder{

       public TextView mTxtExplanation;
       public ImageView mImageIcon;
       public ImageView questionsConfirm;
       public SeekBar mSeekBar;

       public questionViewHolder(@NonNull View itemView) {
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

   public QuestionViewAdapter(ArrayList<QuestionBoxes> questionBoxesList){
        mQuestionBoxes = questionBoxesList;
   }

    @NonNull
    @Override
    public questionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.questions_item, parent, false);
        questionViewHolder qvh = new questionViewHolder(v);

        return qvh;
    }

    @Override
    public void onBindViewHolder(@NonNull questionViewHolder holder, int position) {
        QuestionBoxes currentItem = mQuestionBoxes.get(position);


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
        return mQuestionBoxes.size();
    }
}


