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

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;

import de.hdodenhof.circleimageview.CircleImageView;
import dk.tec.velfaerdsapp.R;

import static android.content.Context.MODE_PRIVATE;

public class SelectAdapter extends RecyclerView.Adapter<SelectAdapter.ViewHolder> {
    public static ArrayList<String> selected = new ArrayList<String>();
    private static int confirmCounter = 0;

    private static final String TAG = "RecyclerViewAdapter";

    //vars
    private ArrayList<String> questionList;
    private ArrayList<String> answerList;
    private ArrayList<String> imageList;
    private Context mContext;
    private boolean misGood;




    public SelectAdapter(Context context, ArrayList<String> questions, ArrayList<String> answers, ArrayList<String> imageUrls, Boolean isGood) {
        answerList = answers;
        questionList = questions;
        imageList = imageUrls;
        mContext = context;
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
        if (misGood) {
            answerList.sort(Collections.reverseOrder());


        } else {
            Collections.sort(answerList);
        }
        Glide.with(mContext).asBitmap()
                .load(imageList.get(position))
                .into(holder.image);
        holder.answer.setText(answerList.get(position));
        holder.question.setText(questionList.get(position));
        holder.selectConfirm.setVisibility(View.GONE);
        Glide.with(mContext).asBitmap()
                .load(imageList.get(position))
                .into(holder.image);

        holder.answer.setText(answerList.get(position));
        holder.question.setText(questionList.get(position));
        holder.selectConfirm.setVisibility(View.GONE);

        SharedPreferences sharedPref = holder.answer.getContext().getSharedPreferences("selectArray", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();


        holder.btn.setOnClickListener(v -> {
            String id = answerList.get(position) +" "+ questionList.get(position);


//Ensures the data is saved in SharedPrefs and the answer is marked. It also ensures that a there is a maximum amount of selected the user can make.
            if (confirmCounter <= 4){
                if (holder.selectConfirm.isShown()){
                    holder.selectConfirm.setVisibility(View.GONE);
                    confirmCounter--;

                    selected.remove(id);
                    System.out.println("Remove"+selected);
                } else {

                    holder.selectConfirm.setVisibility(View.VISIBLE);
                    editor.putString(questionList.get(position)+"_selected",id);
                    editor.apply();

                    confirmCounter++;
                    System.out.println(confirmCounter);
                    if (misGood){
                        selected.add("good: "+id);

                        System.out.println("added Good"+selected);
                    } else {
                        selected.add("bad: "+id);
                        System.out.println("added Bad"+selected);
                    }
                }
            } else if (holder.selectConfirm.isShown()){

                holder.selectConfirm.setVisibility(View.GONE);
                confirmCounter--;
                System.out.println(confirmCounter);

                selected.remove(id);
                System.out.println("Removed: "+selected);
            } else {
                Toast.makeText(mContext, "You have selected too many answers", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public static int getCount(){
        int count = confirmCounter;
        System.out.println("getCount() = "+count);
        return count;
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
