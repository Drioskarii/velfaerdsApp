package Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dk.tec.velfaerdsapp.QuestionsPage;
import dk.tec.velfaerdsapp.R;
import dk.tec.velfaerdsapp.Strengths;
import dk.tec.velfaerdsapp.The24Strengths;
import dk.tec.velfaerdsapp.The24StrengthsBoxes;

import static android.content.Context.MODE_PRIVATE;

public class The24StrengthsAdapter extends BaseAdapter  {

    private static final String TAG = "The24StrengthsAdapter";

    private final ArrayList<Strengths> strengths;
    Context mContext;

    public The24StrengthsAdapter(Context context, ArrayList<Strengths> strengths){
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

        if (itemView == null) {
            itemView = LayoutInflater.from(mContext).inflate(R.layout.the24strengths_item, parent, false);
        }

        ImageView mImageIcon = itemView.findViewById(R.id.imageIcon24);
        TextView mTxtTitle = itemView.findViewById(R.id.the24_txtTitle);
        TextView mTxtQuestion = itemView.findViewById(R.id.the24_txtQuestion);

        Strengths tempStrengths = (Strengths) getItem(position);

        mTxtTitle.setText(tempStrengths.getTitle());
        mTxtQuestion.setText(tempStrengths.getQuestion());

        return itemView;
    }
}

