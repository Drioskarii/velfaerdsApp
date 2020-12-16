package Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.PixelCopy;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Strengths.Points;
import de.hdodenhof.circleimageview.CircleImageView;
import dk.tec.velfaerdsapp.R;

import static android.content.Context.MODE_PRIVATE;

public class ResultAdapter extends RecyclerView.Adapter<SelectAdapter.ViewHolder> {


    public static ArrayList<String> goodSelected = new ArrayList<String>();
    public static ArrayList<String> badSelected = new ArrayList<String>();
    private static int goodConfirmCounter = 0;
    private static int badConfirmCounter = 0;

    private static final String TAG = "RecyclerViewAdapter";

    //vars
    private ArrayList<Points> mPoints;
    private Context mContext;
    private boolean misGood;




    public ResultAdapter(Context Context, ArrayList<Points> Points, Boolean isGood) {
        mPoints = Points;
        mContext = Context;
        misGood = isGood;
    }

    @NonNull
    @Override
    public SelectAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_item, parent, false);
        return new SelectAdapter.ViewHolder(view);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull SelectAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        //Sorts selectpage arrayList
        if (misGood) {
            mPoints.sort(Collections.reverseOrder());


        } else {
            Collections.sort(mPoints, Comparator.comparing(Points::getPoints));
        }
        Glide.with(mContext).asBitmap()
                .load(mPoints.get(position).getIcon())
                .into(holder.image);
        holder.answer.setText(mPoints.get(position).getPoints());
        holder.question.setText(mPoints.get(position).getQuestion());
        holder.selectConfirm.setVisibility(View.GONE);
        Glide.with(mContext).asBitmap()
                .load(mPoints.get(position).getIcon())
                .into(holder.image);

        holder.answer.setText(mPoints.get(position).getPoints());
        holder.question.setText(mPoints.get(position).getQuestion());
        holder.image.setImageResource(mPoints.get(position).getIcon());

    }


    @Override
    public int getItemCount() {
        return mPoints.size();
    }

    public static int getCount(){
        int goodCount = goodConfirmCounter;
        int badCount = badConfirmCounter;
        System.out.println("getCount() = "+badConfirmCounter);
        System.out.println("getCount() = "+goodConfirmCounter);
        return goodConfirmCounter + badConfirmCounter;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView image;
        RelativeLayout btn;
        TextView question;
        TextView answer;
        ImageView selectConfirm;
        TextView Title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.onClickbtn);
            image = itemView.findViewById(R.id.imageIcon);
            question = itemView.findViewById(R.id.select_txtQuestion);
            answer = itemView.findViewById(R.id.select_txtAnswer);
            selectConfirm = itemView.findViewById(R.id.selectConfirm);
            Title = itemView.findViewById(R.id.select_txtTitle);
        }
    }


}
