package Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import dk.tec.velfaerdsapp.R;
import dk.tec.velfaerdsapp.Strengths;
import dk.tec.velfaerdsapp.StrengthsAnswers;

import static android.content.Context.MODE_PRIVATE;

public class SelectAdapter extends RecyclerView.Adapter<SelectAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    public static ArrayList<Strengths> goodSelected = new ArrayList<>();
    public static ArrayList<Strengths> badSelected = new ArrayList<>();
    private static int goodConfirmCounter = 0;
    private static int badConfirmCounter = 0;

    //vars
    private ArrayList<Strengths> mStrengths;
    private Context mContext;
    private boolean misGood;


    public SelectAdapter(Context context, ArrayList<Strengths> strengths, boolean isGood){
        mContext = context;
        mStrengths = strengths;
        misGood = isGood;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_item, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");
        //Sorts selectpage arrayList
        Log.d(TAG, "POSITION: "+position);
        Log.d(TAG, "QUESTION: "+mStrengths.get(position).getQuestion());

        holder.answer.setText(String.valueOf(mStrengths.get(position).getAnswer()));
        holder.question.setText(mStrengths.get(position).getQuestion());
        holder.selectConfirm.setVisibility(View.GONE);

        SharedPreferences sharedPref = holder.answer.getContext().getSharedPreferences("selectArray", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();


        holder.btn.setOnClickListener(v -> {
            String answerValue = String.valueOf(mStrengths.get(position).getAnswer());
            String questionValue = mStrengths.get(position).getQuestion();

            //Ensures the data is saved in SharedPrefs and the answer is marked. It also ensures that a there is a maximum amount of selected the user can make.
            if(misGood){
                if (goodConfirmCounter <= 4){
                    if (holder.selectConfirm.isShown()){
                        holder.selectConfirm.setVisibility(View.GONE);
                        goodConfirmCounter--;

                        goodSelected.remove(mStrengths.get(position));
                        System.out.println("Remove "+mStrengths.get(position));
                    } else {

                        holder.selectConfirm.setVisibility(View.VISIBLE);
                        editor.putString(mStrengths.get(position).getQuestion()+"_selected",answerValue+questionValue);
                        editor.apply();

                        goodConfirmCounter++;
                        System.out.println(goodConfirmCounter);

                        goodSelected.add(mStrengths.get(position));
                        //System.out.println("added Good"+goodSelected);
                        System.out.println("added Good"+mStrengths.get(position));

                    }
                } else if (holder.selectConfirm.isShown()){

                    holder.selectConfirm.setVisibility(View.GONE);
                    goodConfirmCounter--;
                    System.out.println(goodConfirmCounter);

                    goodSelected.remove(mStrengths.get(position));
                    //System.out.println("Removed: "+goodSelected);
                    System.out.println("Remove "+mStrengths.get(position));
                } else {
                    Toast.makeText(mContext, "You have selected too many answers", Toast.LENGTH_SHORT).show();
                }
            } else {
                if (badConfirmCounter <= 4){
                    if (holder.selectConfirm.isShown()){
                        holder.selectConfirm.setVisibility(View.GONE);
                        badConfirmCounter--;

                        badSelected.remove(mStrengths.get(position));
                        System.out.println("Remove "+mStrengths.get(position));
                    } else {

                        holder.selectConfirm.setVisibility(View.VISIBLE);
                        editor.putString(mStrengths.get(position).getQuestion()+"_selected",answerValue+questionValue);
                        editor.apply();

                        badConfirmCounter++;
                        System.out.println(badConfirmCounter);

                        badSelected.add(mStrengths.get(position));
                        System.out.println("added Good"+mStrengths.get(position));
                    }
                } else if (holder.selectConfirm.isShown()){

                    holder.selectConfirm.setVisibility(View.GONE);
                    badConfirmCounter--;
                    System.out.println(badConfirmCounter);
                } else {
                    Toast.makeText(mContext, "You have selected too many answers", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return mStrengths.size();
    }

    public static int getCount(){
        int goodCount = goodConfirmCounter;
        int badCount = badConfirmCounter;
        System.out.println("getCount() = "+badConfirmCounter);
        System.out.println("getCount() = "+goodConfirmCounter);
        return (goodConfirmCounter + badConfirmCounter);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView image;
        RelativeLayout btn;
        TextView question;
        TextView answer;
        ImageView selectConfirm;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.onClickbtn);
            image = itemView.findViewById(R.id.imageIcon);
            question = itemView.findViewById(R.id.select_txtQuestion);
            answer = itemView.findViewById(R.id.select_txtAnswer);
            selectConfirm = itemView.findViewById(R.id.selectConfirm);
        }
    }
}