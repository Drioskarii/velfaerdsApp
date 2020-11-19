package dk.tec.velfaerdsapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.security.AccessController;
import java.util.ArrayList;
import java.util.List;

public class emailPage extends touchActivityHandler {

    private float x1, x2;
    private static int MIN_DISTANCE = 400;
    private GestureDetector gestureDetector;
    List<EditText> EditTextList = new ArrayList<>();
    TableLayout tableLayout;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_page);
        tableLayout = findViewById(R.id.tableLayout);

        ImageView imgBtn = findViewById(R.id.btnNewMail);
        imgBtn.setOnClickListener(new View.OnClickListener() {
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
            ++id;
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            EditText etm = new EditText(this);
            if (email != null){
                etm.setText(email);
            }
            else{
                etm.setHint("Indtast Email");
            }
            etm.setTag("mails");
            etm.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            etm.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            etm.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1));
            int maxLength = 30;
            InputFilter[] filterArr = new InputFilter[1];
            filterArr[0] = new InputFilter.LengthFilter(maxLength);
            etm.setFilters(filterArr);
            tableRow.addView(etm);
            etm.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (EditTextList.contains(etm)){
                        EditTextList.remove(etm);
                    }
                    if (validate(etm.getText().toString())){
                        EditTextList.add(etm);
                    }
                }
            });

            //creating button
            Button btn = new Button(this);
            //button Parameters
            btn.setBackgroundResource(R.drawable.ic_baseline_cancel_24);
            btn.setLayoutParams(new TableRow.LayoutParams(dpToPx(24, this), dpToPx(24, emailPage.this)));
            //adding button
            tableRow.addView(btn);
            //adds tableRow to Tablelayout
            tableLayout.addView(tableRow, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
            //remove email row
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //removes the specific one clicked.
                    tableLayout.removeView(tableRow);
                    if (EditTextList.contains(etm)){
                        EditTextList.remove(etm);
                    }
                }
            });
            if (email != null){
                EditTextList.add(etm);
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

        for (int i = 0; i <= id; i++) {

            emailList.add(EditTextList.get(i).getText().toString());
            //System.out.println(emailList);
            for (int x=0; x<emailList.size(); x++) {
                System.out.println(emailList.get(x));
            }
        }
            Intent email = new Intent(Intent.ACTION_SEND);
            //to
            email.putExtra(android.content.Intent.EXTRA_EMAIL,
                    emailList.toArray(new String[emailList.size()]));
            //Subject
            email.putExtra(Intent.EXTRA_SUBJECT, "subject");
            //Message
            email.putExtra(Intent.EXTRA_TEXT, "message");

            //need this to prompts email client only
            email.setType("message/rfc822");

            startActivity(Intent.createChooser(email, "TestMail+fisk@gmail.com"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPref = getSharedPreferences("emailArray", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear().commit();
            for (int i = 0; i < EditTextList.size(); i++) {
                if (validate(EditTextList.get(i).getText().toString()) && !sharedPref.contains("email_"+i)) {
                    editor.putString("email_"+i, EditTextList.get(i).getText().toString());
                    editor.apply();
                }
            }
        }
}