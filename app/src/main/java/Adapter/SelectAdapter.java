package Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import dk.tec.velfaerdsapp.R;
import dk.tec.velfaerdsapp.Strengths;

import static android.content.Context.MODE_PRIVATE;

public class SelectAdapter extends RecyclerView.Adapter<SelectAdapter.ViewHolder> {
    public static ArrayList<String> selected = new ArrayList<>();
    private ArrayList<Strengths> strengths;

    private static final String TAG = "RecyclerViewAdapter";

    //vars
    private ArrayList<String> questionList;
    private ArrayList<String> answerList;
    private ArrayList<String> imageList = new ArrayList<>();
    private Context mContext;
    private boolean misGood;
    int confirmCounter = 0;


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

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");


        Glide.with(mContext).asBitmap()
                .load(imageList.get(position))
                .into(holder.image);

        holder.answer.setText(answerList.get(position));
        holder.question.setText(questionList.get(position));
        holder.selectConfirm.setVisibility(View.GONE);

        SharedPreferences sharedPref = holder.answer.getContext().getSharedPreferences("selectArray", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();


        holder.btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean isClicked = true;
                //Log.d(TAG, "onClick: clicked on a image: " + questionList.get(position) + answerList.get(position));
                //Toast.makeText(mContext, questionList.get(position)+answerList.get(position), Toast.LENGTH_SHORT).show();

                String id = answerList.get(position) + questionList.get(position);
                int i = 1;

                if (confirmCounter <= 4){
                    if (holder.selectConfirm.isShown()){
                        holder.selectConfirm.setVisibility(View.GONE);
                        confirmCounter--;
                        //System.out.println(confirmCounter);

                        selected.remove(id);
                        System.out.println("Remove"+selected);
                    } else {

                        holder.selectConfirm.setVisibility(View.VISIBLE);
                        editor.putString(id+"_selected",id);
                        editor.apply();

                        confirmCounter++;
                        System.out.println(confirmCounter);
                        if (misGood == true){
                            selected.add("good: "+id);

                            System.out.println("added Good"+selected);
                        } else {
                            selected.add("bad: "+id);
                            System.out.println("added Bad"+selected);
                        }
                        //selected.add(id);

                        //System.out.println("added"+selected);
                    }
                   // System.out.println(isClicked);

                } else if (holder.selectConfirm.isShown()){

                    holder.selectConfirm.setVisibility(View.GONE);
                    confirmCounter--;
                    System.out.println(confirmCounter);

                    selected.remove(id);
                    System.out.println("Remove"+selected);
                } else {
                    Toast.makeText(mContext, "You have selected too many answers", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public int getCount(){
        int count = confirmCounter;
        System.out.println("getCount() = "+count);
        return count;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
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
