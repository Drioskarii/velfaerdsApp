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
import dk.tec.velfaerdsapp.The24Strengths;
import dk.tec.velfaerdsapp.The24StrengthsBoxes;

import static android.content.Context.MODE_PRIVATE;

public class The24StrengthsAdapter extends RecyclerView.Adapter<The24StrengthsAdapter.the24StrengthViewHolder> {
    private final ArrayList<The24StrengthsBoxes> mThe24StrengthsBoxes;
    public static The24Strengths the24Strength;

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
        }
    }

    public The24StrengthsAdapter(ArrayList<The24StrengthsBoxes> the24StrengthsBoxesList){
        mThe24StrengthsBoxes = the24StrengthsBoxesList;
    }

    @NonNull
    public The24StrengthsAdapter.the24StrengthViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.the24strengths_item, parent, false);
        The24StrengthsAdapter.the24StrengthViewHolder svh = new The24StrengthsAdapter.the24StrengthViewHolder(v);

        return svh;
    }

    public void onBindViewHolder(@NonNull The24StrengthsAdapter.the24StrengthViewHolder holder, int position) {
        The24StrengthsBoxes currentItem = mThe24StrengthsBoxes.get(position);

        holder.mImageIcon.setImageResource(currentItem.getImageIcon());
        holder.mTxtExplanation.setText(currentItem.getTxtExplanation());
    }

    @Override
    public int getItemCount() {
            return mThe24StrengthsBoxes.size();
        }

}
