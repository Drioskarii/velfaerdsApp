package dk.tec.velfaerdsapp;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class emailPage extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private float x1, x2;
    private static int MIN_DISTANCE = 100;
    private GestureDetector gestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_page);

        EditText etm = new EditText(this);
        editTextList.add(etm);

        //This is where the parameter goes.
        etm.setGravity(Gravity.CENTER);
        etm.setHint(getString(R.string.editTextEmail));
        etm.setTag("mails");
        etm.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        //This is where the EditText gets added to activity_email_page.xml
        LinearLayout emailPage = (LinearLayout) findViewById(R.id.emailPlacer);
        emailPage.addView(etm);
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

    List<EditText> editTextList = new ArrayList<EditText>();

    int id = 0;
    public void addEmail(View view) {


        if (id == 4){
    System.out.println("ree");

        }else  {
            ++id;


            //EditText is created here (Etm is Edit text mail)

            EditText etm = new EditText(this);
            editTextList.add(etm);
            //This is where the parameter goes.
            etm.setHint(getString(R.string.editTextEmail));
            etm.setGravity(Gravity.CENTER);
            etm.setTag("mails");
            etm.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

            //This is where the EditText gets added to activity_email_page.xml
            LinearLayout emailPage = (LinearLayout) findViewById(R.id.emailPlacer);
            emailPage.addView(etm);
        }
    }

    //lortet er ikke testet
    public void sendMail(View view) {
        ArrayList<String> emailList = new ArrayList<String>();
        //For loop that fills in and sends mail I suppose

        for (int i = 0; i <= id; i++) {
            emailList.add(editTextList.get(i).getText().toString());
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