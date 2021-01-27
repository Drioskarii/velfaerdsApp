package Adapter;

import android.content.Context;
import android.content.SharedPreferences;
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

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import dk.tec.velfaerdsapp.R;
import Strengths.Points;

import static android.content.Context.MODE_PRIVATE;

public class SelectAdapter extends RecyclerView.Adapter<SelectAdapter.ViewHolder> {

//////////////////////////////////////////////////////////
// Her opretter du ViewHolderen
// Indsætter data
// og tjekker dataen igennem for de forskellige kriterier
//////////////////////////////////////////////////////////

    private static final String TAG = "RecyclerViewAdapter";

    //Her laver du et array
    public static ArrayList<Points> goodSelected = new ArrayList<>();
    public static int goodConfirmCounter;


    //vars
    private ArrayList<Points> mStrengths;
    private Context mContext;
    private boolean misGood;

    //Her tager du de værdier ud som du får på selectPagen
    public SelectAdapter(Context context, ArrayList<Points> strengths, boolean isGood){
        mContext = context;
        mStrengths = strengths;
        misGood = isGood;

    }

    //Her bliver der lavet en ny ViewHolder.
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_item, parent, false);
        return new ViewHolder(view);
    }

    //Her binder du din data til ViewHolderen
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        //Sortere selectpage arrayList
        Log.d(TAG, "POSITION: "+position);
        Log.d(TAG, "QUESTION: "+mStrengths.get(position).getDescription());

        //Her vælger du hvad der skal være i den ViewHolder
        holder.answer.setText(String.valueOf(mStrengths.get(position).getPoints()));
        holder.description.setText(mStrengths.get(position).getDescription());
        holder.selectConfirm.setVisibility(View.GONE);
//
        holder.image.setImageResource(mStrengths.get(position).getIcon());

        SharedPreferences sharedPref = holder.answer.getContext().getSharedPreferences("selectArray", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        String answerValue = String.valueOf(mStrengths.get(position).getPoints());
        String questionValue = mStrengths.get(position).getDescription();

        //Her sikre vi os at result page ikke holder på de gamle værdier
        if (goodConfirmCounter == 2){
            goodSelected.clear();
        }

        goodConfirmCounter = 0;
        if (!misGood){
            goodConfirmCounter = 0;
            holder.selectConfirm.setVisibility(View.GONE);
            editor.putString(mStrengths.get(position).getDescription()+"_selected",answerValue+questionValue);
            editor.apply();
        }

        holder.btn.setOnClickListener(v -> {
            //Her sikre du dig at din data gemmes i en SharedPrefs og at svaret bliver makeret. Her kan du også vælge hvor mange max svar som brugeren kan svare på
            if(misGood) {
                if (goodConfirmCounter < 2) {
                    if (holder.selectConfirm.isShown()) {
                        holder.selectConfirm.setVisibility(View.GONE);
                        goodConfirmCounter--;

                        goodSelected.remove(mStrengths.get(position));
                        System.out.println("Remove " + mStrengths.get(position));
                    } else {

                        holder.selectConfirm.setVisibility(View.VISIBLE);
                        editor.putString(mStrengths.get(position).getDescription() + "_selected", answerValue + questionValue);
                        editor.apply();

                        goodConfirmCounter++;
                        System.out.println(goodConfirmCounter);

                        goodSelected.add(mStrengths.get(position));
                        System.out.println("added Good" + mStrengths.get(position));

                    }
                } else if (holder.selectConfirm.isShown()) {

                    holder.selectConfirm.setVisibility(View.GONE);
                    goodConfirmCounter--;
                    System.out.println(goodConfirmCounter);

                    goodSelected.remove(mStrengths.get(position));
                    System.out.println("Remove " + mStrengths.get(position));
                } else {
                    Toast.makeText(mContext, "du kan ikke vælge flere svar", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return mStrengths.size();
    }

    public static int getCount(){
        System.out.println("getCount() = "+goodConfirmCounter);
        return (goodConfirmCounter);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        RelativeLayout btn;
        TextView description;
        TextView answer;
        ImageView selectConfirm;
        //TextView title;

    //Her sætter du Ider på de ting du lavede i ViewHolderen overn over
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.onClickbtn);
            image = itemView.findViewById(R.id.imageIcon);
            description = itemView.findViewById(R.id.select_txtQuestion);
            answer = itemView.findViewById(R.id.select_txtAnswer);
            selectConfirm = itemView.findViewById(R.id.selectConfirm);
        }
    }
}