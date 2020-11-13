package dk.tec.velfaerdsapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.InputType;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TableRow;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class emailPage extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private float x1, x2;
    private static int MIN_DISTANCE = 400;
    private GestureDetector gestureDetector;
    List<EditText> TextList = new ArrayList<EditText>();
    LinearLayout.LayoutParams tbrParams;
    TableRow.LayoutParams txtParams, btnParams;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_page);





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

    @Override
    public void onBackPressed() {
        finish();
    }

    public void addEmail(View view) {
        if (id == 4){

        }else  {
            ++id;
            LinearLayout emailPage = (LinearLayout) findViewById(R.id.emailPlacer);

            TableRow tbr = new TableRow(this);
            emailPage.addView(tbr);
            tbrParams = (LinearLayout.LayoutParams) tbr.getLayoutParams();
            LinearLayout.LayoutParams tbrParam = new LinearLayout.LayoutParams(tbrParams);
            tbrParam.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            tbrParam.width = ViewGroup.LayoutParams.MATCH_PARENT;
            tbr.setBackgroundResource(R.drawable.logo_ug);
            tbr.setLayoutParams(tbrParam);

            //EditText is created here
            EditText etm = new EditText(this);
            tbr.addView(etm);
            //EditText Parameters
            etm.setHint(getString(R.string.editTextEmail));
            etm.setTag("mails");
            etm.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            etm.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            txtParams = (TableRow.LayoutParams) etm.getLayoutParams();
            TableRow.LayoutParams txtParam = new TableRow.LayoutParams(txtParams);
            txtParam.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            txtParam.width = ViewGroup.LayoutParams.MATCH_PARENT;
            txtParam.weight=1;
            etm.setLayoutParams(txtParam);

            //Button is created here
            Button btn = new Button(this);
            tbr.addView(btn);
            //Button Parameters
            btn.setBackgroundResource(R.drawable.ic_baseline_cancel_24);
            btnParams=(TableRow.LayoutParams)btn.getLayoutParams();
            TableRow.LayoutParams btnParam = new TableRow.LayoutParams(btnParams);
            btnParam.height = dpToPx(24, this);
            btnParam.width = dpToPx(24, this);
            btn.setLayoutParams(btnParam);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tbr.removeAllViews();
                    emailPage.removeView(tbr);
                    --id;
                }
            });
            //This is where the EditText gets added to activity_email_page.xml
            tbr.getChildAt(1);
            tbr.getChildAt(2);
        }
    }

    public static int dpToPx(int dp, Context context) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

    //lortet er ikke testet
    public void sendMail(View view) {
        ArrayList<String> emailList = new ArrayList<String>();
        //For loop that fills in and sends mail I suppose

        for (int i = 0; i <= id; i++) {
            emailList.add(TextList.get(i).getText().toString());
            //System.out.println(emailList);
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
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}