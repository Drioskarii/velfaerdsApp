package dk.tec.velfaerdsapp;
import androidx.appcompat.app.AppCompatActivity;
import global.gIntro;

import android.content.Context;
import android.content.Intent;
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
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class emailPage extends touchActivityHandler {

    private float x1, x2;
    private static int MIN_DISTANCE = 400;
    private GestureDetector gestureDetector;
    List<EditText> TextList = new ArrayList<EditText>();
    TableLayout tableLayout;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_page);
        tableLayout = findViewById(R.id.tableLayout);

        //init gestureDetector
        this.gestureDetector = new GestureDetector(emailPage.this, this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        switch (event.getAction()) {
            //press
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                break;

            //lift
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                //horizontal swipe
                float valueX = x2 - x1;
                if (Math.abs(valueX) > MIN_DISTANCE) {
                    if (x2 > x1) {
                        backward();
                    } else {
                        forward();
                    }
                }
        }
        return super.onTouchEvent(event);
    }

    public void forward() {

    }

    public void backward() {
        finish();
    }

    public void addEmail(View view) {
        if (tableLayout.getChildCount() >= 8){

        }else  {
            ++id;
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            EditText etm = new EditText(this);
            etm.setHint(getString(R.string.editTextEmail));
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
                    if (TextList.contains(etm)){
                        TextList.remove(etm);
                    }
                    if (validate(etm.getText().toString())){
                      TextList.add(etm);
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

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //removes the specific one clicked.
                    tableLayout.removeView(tableRow);
                    if (TextList.contains(etm)){
                        TextList.remove(etm);
                    }
                }
            });
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

            emailList.add(TextList.get(i).getText().toString());
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
}