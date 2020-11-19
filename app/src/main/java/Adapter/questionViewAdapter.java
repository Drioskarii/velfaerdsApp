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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dk.tec.velfaerdsapp.R;
import dk.tec.velfaerdsapp.questionboxes;

import static android.content.Context.MODE_PRIVATE;

public class questionViewAdapter extends RecyclerView.Adapter<questionViewAdapter.questionViewHolder> {

    private final ArrayList<questionboxes> mQuestionBoxes;

   public static class questionViewHolder extends RecyclerView.ViewHolder{

       public ImageView mImageIcon;
       public TextView mTxtExplanation;
       public SeekBar mSeekBar;

       public questionViewHolder(@NonNull View itemView) {
           super(itemView);

           mImageIcon = itemView.findViewById(R.id.imageIcon);
           mTxtExplanation = itemView.findViewById(R.id.txtExplanation);
           mSeekBar = itemView.findViewById(R.id.seekBar);
           mSeekBar.setProgress(0);
           mSeekBar.setMax(5);

           //Get SharedPreference shared_Pref myKey.xml
           SharedPreferences sharedPref = mSeekBar.getContext().getSharedPreferences("myKey", MODE_PRIVATE);
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
                   editor.putString(id, String.valueOf  (progress));
                   editor.apply();
               }
           });
       }
   }

   public questionViewAdapter(ArrayList<questionboxes> questionBoxesList){
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
        questionboxes currentItem = mQuestionBoxes.get(position);


        holder.mImageIcon.setImageResource(currentItem.getImageIcon());
        holder.mTxtExplanation.setText(currentItem.getTxtExplanation());

        //get view from seekbar id
       View view = holder.itemView.findViewById(holder.mSeekBar.getId());

        //Get SharedPreference shared_Pref myKey.xml
        SharedPreferences sharedPref = view.getContext().getSharedPreferences("myKey", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        //get data stored from seekBar
        String s1 = sharedPref.getString(String.valueOf(holder.mTxtExplanation.getText()),"");
        if (!s1.isEmpty()){
            holder.mSeekBar.setProgress(Integer.parseInt(s1));
        }

    }

    @Override
    public int getItemCount() {
        return mQuestionBoxes.size();
    }
}


