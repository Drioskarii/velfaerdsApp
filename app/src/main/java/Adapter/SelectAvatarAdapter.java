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

import Strengths.Points;
import dk.tec.velfaerdsapp.R;
import dk.tec.velfaerdsapp.TouchActivityHandler;

import static android.content.Context.MODE_PRIVATE;

    public class SelectAvatarAdapter extends RecyclerView.Adapter<Adapter.SelectAvatarAdapter.ViewHolder> {

//////////////////////////////////////////////////////////
// Her opretter du ViewHolderen
// Indsætter data fra SelectAvatar
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
        public SelectAvatarAdapter(Context context, ArrayList<Points> strengths, boolean isGood){
            mContext = context;
            mStrengths = strengths;
            misGood = isGood;
        }

        //Her bliver der lavet en ny ViewHolder.
        @NonNull
        @Override
        public Adapter.SelectAvatarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_item, parent, false);
            return new Adapter.SelectAvatarAdapter.ViewHolder(view);
        }

        //Her binder du din data til ViewHolderen
        @Override
        public void onBindViewHolder(@NonNull Adapter.SelectAvatarAdapter.ViewHolder holder, int position) {
            //Her vælger du hvad der skal stå i den ViewHolder
            holder.selectConfirm.setVisibility(View.GONE);
            holder.title.setText(mStrengths.get(position).getTitle());
            holder.setNullQuestion.setText("");
            if (mStrengths.get(position).getTitle().contains("Mod")){ if (TouchActivityHandler.gKøn == 1){holder.image.setImageResource(R.drawable.tndmand_mod);} else{holder.image.setImageResource(R.drawable.tndkvinde_mod);}}
            if (mStrengths.get(position).getTitle().contains("Nys")){ if (TouchActivityHandler.gKøn == 1){holder.image.setImageResource(R.drawable.tndmand_nys);} else{holder.image.setImageResource(R.drawable.tndkvinde_nys);}}
            if (mStrengths.get(position).getTitle().contains("Bes")){ if (TouchActivityHandler.gKøn == 1){holder.image.setImageResource(R.drawable.tndmand_bes);} else{holder.image.setImageResource(R.drawable.tndkvinde_bes);}}
            if (mStrengths.get(position).getTitle().contains("Tak")){ if (TouchActivityHandler.gKøn == 1){holder.image.setImageResource(R.drawable.tndmand_tak);} else{holder.image.setImageResource(R.drawable.tndkvinde_tak);}}
            if (mStrengths.get(position).getTitle().contains("Sam")){ if (TouchActivityHandler.gKøn == 1){holder.image.setImageResource(R.drawable.tndmand_sam);} else{holder.image.setImageResource(R.drawable.tndkvinde_sam);}}
            if (mStrengths.get(position).getTitle().contains("Soc")){ if (TouchActivityHandler.gKøn == 1){holder.image.setImageResource(R.drawable.tndmand_soc);} else{holder.image.setImageResource(R.drawable.tndkvinde_soc);}}

            //Her sikre vi os at result page ikke holder på de gamle værdier
            if (goodConfirmCounter == 1){
                goodSelected.clear();

            }

            goodConfirmCounter = 0;
            if (!misGood){

                goodConfirmCounter = 0;
                holder.selectConfirm.setVisibility(View.GONE);
            }

            holder.btn.setOnClickListener(v -> {
                //Her sikre du dig at din data gemmes i en SharedPrefs og at svaret bliver makeret. Her kan du også vælge hvor mange max svar som brugeren kan svare på
                if(misGood) {
                    if (goodConfirmCounter < 1) {
                        if (holder.selectConfirm.isShown()) {
                            holder.selectConfirm.setVisibility(View.GONE);
                            goodConfirmCounter--;

                            goodSelected.remove(mStrengths.get(position));
                        } else {

                            holder.selectConfirm.setVisibility(View.VISIBLE);

                            goodConfirmCounter++;
                            System.out.println(goodConfirmCounter);

                            goodSelected.add(mStrengths.get(position));

                        }
                    } else if (holder.selectConfirm.isShown()) {

                        holder.selectConfirm.setVisibility(View.GONE);
                        goodConfirmCounter--;
                        System.out.println(goodConfirmCounter);

                        goodSelected.remove(mStrengths.get(position));
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
            return (goodConfirmCounter);
        }

        public static class ViewHolder extends RecyclerView.ViewHolder{
            ImageView image;
            RelativeLayout btn;
            ImageView selectConfirm;
            TextView title;
            TextView setNullQuestion;

            //Her sætter du Id'er på de ting du lavede i ViewHolderen overn over
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                btn = itemView.findViewById(R.id.onClickbtn);
                image = itemView.findViewById(R.id.imageIcon);
                selectConfirm = itemView.findViewById(R.id.selectConfirm);
                title = itemView.findViewById(R.id.select_txtQuestion);
                setNullQuestion = itemView.findViewById(R.id.select_txtAnswer);
            }
        }
    }
