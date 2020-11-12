package dk.tec.velfaerdsapp;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class emailPage extends AppCompatActivity {

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
            etm.setGravity(Gravity.LEFT);
            etm.setTag("mails");
            etm.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

            //This is where the EditText gets added to activity_email_page.xml
            LinearLayout emailPage = (LinearLayout) findViewById(R.id.emailPlacer);
            emailPage.addView(etm);
        }
    }

    public void back(View view) {
        Intent intent = new Intent(this, selectPage.class);

        startActivity(intent);
    }
    //lortet virker ikke kun delvist
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
}