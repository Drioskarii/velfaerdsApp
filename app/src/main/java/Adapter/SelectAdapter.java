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

import static android.content.Context.MODE_PRIVATE;

public class SelectAdapter extends RecyclerView.Adapter<SelectAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";

    //vars
    private ArrayList<String> questionList;
    private ArrayList<String> answerList;
    private ArrayList<String> imageList = new ArrayList<>();
    private Context mContext;
    boolean isClicked = false;



    public SelectAdapter(Context context, ArrayList<String> questions, ArrayList<String> answers, ArrayList<String> imageUrls) {
        answerList = answers;
        questionList = questions;
        imageList = imageUrls;
        mContext = context;
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

        Glide.with(mContext)
                .asBitmap()
                .load(imageList.get(position))
                .into(holder.image);

        holder.answer.setText(answerList.get(position));
        holder.question.setText(questionList.get(position));
        holder.selectConfirm.setVisibility(View.GONE);

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "onClick: clicked on a image: " + questionList.get(position) + answerList.get(position));
                Toast.makeText(mContext, questionList.get(position)+answerList.get(position), Toast.LENGTH_SHORT).show();

                String id = questionList.get(position)+answerList.get(position);
                String s1 = questionList.get(position)+answerList.get(position);

                if (!isClicked){
                    holder.selectConfirm.setVisibility(View.VISIBLE);
                    isClicked = true;
                } else {
                    holder.selectConfirm.setVisibility(View.GONE);
                    isClicked = !isClicked;
                }
                System.out.println(isClicked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageList.size();
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
