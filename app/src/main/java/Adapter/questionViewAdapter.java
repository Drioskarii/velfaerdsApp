package Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dk.tec.velfaerdsapp.R;
import dk.tec.velfaerdsapp.questionboxes;

public class questionViewAdapter extends RecyclerView.Adapter<questionViewAdapter.questionViewHolder> {

    private final ArrayList<questionboxes> mQuestionBoxes;

   public static class questionViewHolder extends RecyclerView.ViewHolder{

       public ImageView mImageIcon;
       public TextView mTxtExplanation;

       public questionViewHolder(@NonNull View itemView) {
           super(itemView);

           mImageIcon = itemView.findViewById(R.id.imageIcon);
           mTxtExplanation = itemView.findViewById(R.id.txtExplanation);
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
    }

    @Override
    public int getItemCount() {
        return mQuestionBoxes.size();
    }
}
