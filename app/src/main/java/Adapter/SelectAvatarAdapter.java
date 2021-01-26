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

        private static final String TAG = "RecyclerViewAdapter";

        public static ArrayList<Points> goodSelected = new ArrayList<>();
        public static int goodConfirmCounter;


        //vars
        private ArrayList<Points> mStrengths;
        private Context mContext;
        private boolean misGood;


        public SelectAvatarAdapter(Context context, ArrayList<Points> strengths, boolean isGood){
            mContext = context;
            mStrengths = strengths;
            misGood = isGood;

        }

        @NonNull
        @Override
        public Adapter.SelectAvatarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_item, parent, false);
            return new Adapter.SelectAvatarAdapter.ViewHolder(view);
        }


        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onBindViewHolder(@NonNull Adapter.SelectAvatarAdapter.ViewHolder holder, int position) {
            Log.d(TAG, "onBindViewHolder: called.");
            //Sorts selectpage arrayList
            Log.d(TAG, "POSITION: "+position);
            Log.d(TAG, "QUESTION: "+mStrengths.get(position).getDescription());

            TouchActivityHandler tah = new TouchActivityHandler();
            holder.selectConfirm.setVisibility(View.GONE);
            holder.title.setText(mStrengths.get(position).getTitle());
            if (mStrengths.get(position).getTitle().contains("Mod")){ if (tah.gKøn == 1){holder.image.setImageResource(R.drawable.tndmand_mod);} else{holder.image.setImageResource(R.drawable.tndkvinde_mod);}}
            if (mStrengths.get(position).getTitle().contains("Nys")){ if (tah.gKøn == 1){holder.image.setImageResource(R.drawable.tndmand_nys);} else{holder.image.setImageResource(R.drawable.tndkvinde_nys);}}
            if (mStrengths.get(position).getTitle().contains("Bes")){ if (tah.gKøn == 1){holder.image.setImageResource(R.drawable.tndmand_bes);} else{holder.image.setImageResource(R.drawable.tndkvinde_bes);}}
            if (mStrengths.get(position).getTitle().contains("Tak")){ if (tah.gKøn == 1){holder.image.setImageResource(R.drawable.tndmand_tak);} else{holder.image.setImageResource(R.drawable.tndkvinde_tak);}}
            if (mStrengths.get(position).getTitle().contains("Sam")){ if (tah.gKøn == 1){holder.image.setImageResource(R.drawable.tndmand_sam);} else{holder.image.setImageResource(R.drawable.tndkvinde_sam);}}
            if (mStrengths.get(position).getTitle().contains("Soc")){ if (tah.gKøn == 1){holder.image.setImageResource(R.drawable.tndmand_soc);} else{holder.image.setImageResource(R.drawable.tndkvinde_soc);}}

            SharedPreferences sharedPref = holder.title.getContext().getSharedPreferences("selectAvatarArray", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();

            String answerValue = String.valueOf(mStrengths.get(position).getPoints());
            String questionValue = mStrengths.get(position).getDescription();

            //ensures that the ResultPage doesn't store old values
            if (goodConfirmCounter == 1){
                goodSelected.clear();

                // badSelected.clear();
            }

            goodConfirmCounter = 0;
            if (!misGood){
                //  badSelected.clear();
                goodConfirmCounter = 0;
                holder.selectConfirm.setVisibility(View.GONE);
                //  badSelected.add(mStrengths.get(position));
                editor.putString(mStrengths.get(position).getDescription()+"_selected",answerValue+questionValue);
                editor.apply();
            }

            holder.btn.setOnClickListener(v -> {
                //Ensures the data is saved in SharedPrefs and the answer is marked. It also ensures that a there is a maximum amount of selected the user can make.
                if(misGood) {
                    if (goodConfirmCounter < 1) {
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
            ImageView selectConfirm;
            TextView title;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                btn = itemView.findViewById(R.id.onClickbtn);
                image = itemView.findViewById(R.id.imageIcon);
                selectConfirm = itemView.findViewById(R.id.selectConfirm);
                title = itemView.findViewById(R.id.select_txtAnswer);
            }
        }
    }
