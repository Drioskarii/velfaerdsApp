package dk.tec.velfaerdsapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Strengths.Points;

public class EmailPage extends TouchActivityHandler {

    private static final String TAG = "emailPage";

    List<EditText> editTextList = new ArrayList<>();
    TableLayout tableLayout;
    int maxEmails = 0;
    ArrayList<Points> goodSelected = new ArrayList<>();
    String questions;
    String title;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_page);
        tableLayout = findViewById(R.id.tableLayout);

        ImageView btnNewMail = findViewById(R.id.btnNewMail);
        btnNewMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEmail(v, null);
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("emailArray", MODE_PRIVATE);
        for (int i = 0; i < sharedPreferences.getAll().size(); i++){
            String s1 = sharedPreferences.getString("email_"+i, "");
            if (s1.length() > 0) {
                addEmail(this.tableLayout, s1);
            }
        }
        goodSelected = getIntent().getParcelableArrayListExtra("goodSelectedList");
    }

    public void addEmail(View view, String email) {
        if (tableLayout.getChildCount() >= 8){

        }else  {
            ++maxEmails;
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            EditText editText = new EditText(this);
            if (email != null){
                editText.setText(email);
            }
            else{
                if(maxEmails == 1){
                    editText.setHint("Indtast din Email");
                } else{
                    editText.setHint("Indtast Email");
                }

            }
            editText.setTag("mails");
            editText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            editText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            editText.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1));
            int maxLength = 30;
            InputFilter[] filterArr = new InputFilter[1];
            filterArr[0] = new InputFilter.LengthFilter(maxLength);
            editText.setFilters(filterArr);
            tableRow.addView(editText);
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (editTextList.contains(editText)){
                        editTextList.remove(editText);
                    }
                    if (validate(editText.getText().toString())){
                        editTextList.add(editText);
                    }
                }
            });

            //creating button
            Button btnDelete = new Button(this);
            //button Parameters
            btnDelete.setBackgroundResource(R.drawable.ic_baseline_cancel_24);
            btnDelete.setLayoutParams(new TableRow.LayoutParams(dpToPx(24, this), dpToPx(24, EmailPage.this)));
            //adding button
            tableRow.addView(btnDelete);
            //adds tableRow to Tablelayout
            tableLayout.addView(tableRow, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
            //remove email row
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //removes the specific one clicked.
                    tableLayout.removeView(tableRow);
                    if (editTextList.contains(editText)){
                        editTextList.remove(editText);
                    }
                }
            });
            if (email != null){
                editTextList.add(editText);
            }
        }
    }



    //pixels til dp
    public static int dpToPx(int dp, Context context){
        return dp * ((int) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    public static boolean validate(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public void sendMail(View view) {


        String[] TO = new String[tableLayout.getChildCount()];
        int i=0;
        while (i < tableLayout.getChildCount())
        {
            TableRow row = (TableRow)  tableLayout.getChildAt(i);
            EditText childtext = (EditText) row.getChildAt(0);
            TO[i] = childtext.getText().toString();
            i++;
        }
        Log.i("Send email", "");
        Intent emailIntent = new Intent(Intent.ACTION_SEND);



        if(goodSelected.isEmpty()){
            System.out.println("Arraylisten er tom");
            System.out.println("Find mig her");
            System.out.print(goodSelected);
        }else{
            System.out.println("Der er Data i Arraylisten");
            for(int int1 = 0; int1 < goodSelected.size(); int1++)
            {
                System.out.println(goodSelected.get(int1).getPoints());
                System.out.println(goodSelected.get(int1).getQuestion());
                System.out.println(goodSelected.get(int1).getIcon());
                System.out.println(goodSelected.get(int1).getTitle());
            }
        }



        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "De 24 værdier");
        //Skal laves om til at indeholde svar fra shared preferences ELLER intent, hvad end vi går videre med
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Question 1: 4 \nQuestion 2: 2\nQuestion 3: 2 \nQuestion 4: 2 \nQuestion 5: 1");

        

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            Log.i("Sent email", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(EmailPage.this, "Der er ikke nogen email applikation installeret.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPref = getSharedPreferences("emailArray", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear().apply();
            for (int i = 0; i < editTextList.size(); i++) {
                if (validate(editTextList.get(i).getText().toString()) && !sharedPref.contains("email_"+i)) {
                    editor.putString("email_"+i, editTextList.get(i).getText().toString());
                    editor.apply();
                }
            }
        }
}