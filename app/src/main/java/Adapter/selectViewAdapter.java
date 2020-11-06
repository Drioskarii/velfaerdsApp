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
import dk.tec.velfaerdsapp.selectBoxes;

public class selectViewAdapter extends RecyclerView.Adapter<selectViewAdapter.selectViewHolder> {

    private final ArrayList<selectBoxes> mselectBoxes;

    public static class selectViewHolder extends RecyclerView.ViewHolder{

        public ImageView mImageIcon;
        public TextView mTxtExplanation;

        public selectViewHolder(@NonNull View itemView) {
            super(itemView);

            mImageIcon = itemView.findViewById(R.id.imageIcon);
            mTxtExplanation = itemView.findViewById(R.id.txtExplanation);
        }
    }

    public selectViewAdapter(ArrayList<selectBoxes> selectBoxesList){
        mselectBoxes = selectBoxesList;
    }

    @NonNull
    @Override
    public selectViewAdapter.selectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_item, parent, false);
        selectViewAdapter.selectViewHolder qvh = new selectViewAdapter.selectViewHolder(v);
        return qvh;
    }

    @Override
    public void onBindViewHolder(@NonNull selectViewAdapter.selectViewHolder holder, int position) {
        selectBoxes currentItem = mselectBoxes.get(position);

        holder.mImageIcon.setImageResource(currentItem.getImageIcon());
        holder.mTxtExplanation.setText(currentItem.getTxtExplanation());
    }

    @Override
    public int getItemCount() {
        return mselectBoxes.size();
    }
}
