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
    //Tilbage knap
    Button btnBack;
    //Dette er en liste der indeholder "EditText" bokse.
    List<EditText> editTextList = new ArrayList<>();
    //XML TableLayout
    TableLayout tableLayout;
    //Max emails, så knappen ikke kan spammes.
    int maxEmails = 0;
    ArrayList<Points> goodSelected = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_page);
        //Her sættes variablerne til elementer på XML siden
        tableLayout = findViewById(R.id.tableLayout);
        btnBack = findViewById(R.id.btn_email_back);
        //Gemmer intent fra forrige side til array variablen.
        goodSelected = getIntent().getParcelableArrayListExtra("goodSelectedList");

        //Her laves en knap ud af et Imageview.
        ImageView btnNewMail = findViewById(R.id.btnNewMail);
        btnNewMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEmail(null);
            }
        });

        //Her kaldes metoden for at initiere den første Email.
        addEmail(null);

        //Her findes dataen i sharedPreferences og loopes igennem for at se om der er oprættede nogen objekter tidligere.
        //Dette gør det muligt at lukke siden og åbne den igen, uden at miste alt det indtastede data.
        SharedPreferences sharedPreferences = getSharedPreferences("emailArray", MODE_PRIVATE);
        for (int i = 0; i < sharedPreferences.getAll().size(); i++){
            String s1 = sharedPreferences.getString("email_"+i, "");
            if (s1.length() > 0) {
                addEmail(s1);
            }
        }

        //Her sættes tilbage knappen
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePage();
            }
        });
    }

    //addEmail Metode der modtager en Email tekst.
    public void addEmail(String email) {
        //Check for at se om vi er nået max emails, ellers opret ny.
        if (tableLayout.getChildCount() >= 8){

        }else  {
            ++maxEmails;
            TableRow tableRow = new TableRow(this);
            //Her opretter vi et nyt Tablerow og lægger det som child til parent.
            tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            //Her oprettes en ny EditText og sættes teksten.
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
            //Her sættes parametre på EditText, såsom at den skal verificere at det er en rigtig Email og at teksten skal sættes i centrum.
            editText.setTag("mails");
            editText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            editText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            //Her sætter vi igen layout parameterne, denne gang til EditText og lægger den inde i tableRow længere nede.
            editText.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1));
            int maxLength = 30;
            //Her sættes et indputfilter der skal forhindre at emails bliver længere end 30 karaktere.
            InputFilter[] filterArr = new InputFilter[1];
            filterArr[0] = new InputFilter.LengthFilter(maxLength);
            //Her sættes filteret på EditText.
            editText.setFilters(filterArr);
            //Her Tilføjes editText til tableRow.
            tableRow.addView(editText);
            //Her sættes en "TextWatcher" på editText, der skal udfører nogle handlinger når teksten ændres.
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    //her checker vi om editTextList listen indeholder dette view, og så fjerner vi det.
                    if (editTextList.contains(editText)){
                        editTextList.remove(editText);
                    }
                    //Her checker vi om editText texten er valideret som en Email, ved brug af "validate" metoden længere nede.
                    //Hvis den er godkendt bliver den tilføjet til editText listen.
                    if (validate(editText.getText().toString())){
                        editTextList.add(editText);
                    }
                }
            });

            //Her oprettes slet knappen ved siden af email teksten, så emailen kan fjernes.
            Button btnDelete = new Button(this);
            //Her sættes knappens parametere, her bruges metoden dpToPx til at sætte knappens størrelse.
            btnDelete.setBackgroundResource(R.drawable.ic_baseline_cancel_24);
            btnDelete.setLayoutParams(new TableRow.LayoutParams(dpToPx(24, this), dpToPx(24, EmailPage.this)));
            //Her tilføjes knappen til tableRow.
            tableRow.addView(btnDelete);
            //Her tilføjer vi til sidst tableRow to Tablelayout
            tableLayout.addView(tableRow, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
            //Her sættes en "Listener" på btnDelete, der sletter email objektet
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
    
    //pixels til dp metode.
    public static int dpToPx(int dp, Context context){
        return dp * ((int) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }

    //validate metode.
    public static boolean validate(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    //Send Mail Metode.
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



        //Udskrivning af Arraylisten fra intent

        if(goodSelected.isEmpty()){
            System.out.println("Arraylisten er tom");
            System.out.println("Find mig her");
            System.out.print(goodSelected);
        }else{
            System.out.println("Der er Data i Arraylisten");
            for(int int1 = 0; int1 < goodSelected.size(); int1++)
            {
                System.out.println(goodSelected.get(int1).getPoints());
                System.out.println(goodSelected.get(int1).getDescription());
                System.out.println(goodSelected.get(int1).getIcon());
                System.out.println(goodSelected.get(int1).getTitle());
            }
        }



        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "De 24 værdier");
        //Skal laves om til at indeholde svar fra shared preferences ELLER intent, hvad end vi går videre med
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Navn: "+ gName + "\n Job: " + gJob +"\n\n\nFørste Karaktertræk: \n Title: " + goodSelected.get(0).getTitle() + "\n Spørgsmål: " + goodSelected.get(0).getDescription() + "\n Svar: " + goodSelected.get(0).getPoints() + "\n\n Anden Karaktertræk: \n Title: " + goodSelected.get(1).getTitle() + "\n Spørgsmål: " + goodSelected.get(1).getDescription() + "\n Svar: " + goodSelected.get(1).getPoints());

        

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