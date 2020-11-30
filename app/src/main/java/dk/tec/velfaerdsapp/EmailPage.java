package dk.tec.velfaerdsapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.List;

public class EmailPage extends TouchActivityHandler {

    private static final String TAG = "emailPage";

    List<EditText> editTextList = new ArrayList<>();
    TableLayout tableLayout;
    int maxEmails = 0;

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
                editText.setHint("Indtast Email");
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

    //lortet er ikke testet
    public void sendMail(View view) {
        ArrayList<String> emailList = new ArrayList<String>();
        //For loop that fills in and sends mail I suppose

        for (int i = 0; i <= maxEmails; i++) {

            emailList.add(editTextList.get(i).getText().toString());
            //System.out.println(emailList);
            for (int x=0; x<emailList.size(); x++) {
                System.out.println(emailList.get(x));
            }
        }
            Intent intent = new Intent(Intent.ACTION_SEND);
            //to
            intent.putExtra(android.content.Intent.EXTRA_EMAIL,
                        emailList.toArray(new String[emailList.size()]));
            //Subject
            intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
            //Message
            intent.putExtra(Intent.EXTRA_TEXT, "message");

            //need this to prompts email client only
            intent.setType("message/rfc822");

            startActivity(Intent.createChooser(intent, "TestMail+fisk@gmail.com"));
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