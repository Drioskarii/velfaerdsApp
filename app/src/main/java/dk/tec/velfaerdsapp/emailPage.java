package dk.tec.velfaerdsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

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
        etm.setHint(getString(R.string.editTextEmail));
        etm.setTag("mails");
        etm.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        //This is where the EditText gets added to activity_email_page.xml
        LinearLayout emailPage = (LinearLayout) findViewById(R.id.emailPage);
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
            etm.setTag("mails");
            etm.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

            //This is where the EditText gets added to activity_email_page.xml
            LinearLayout emailPage = (LinearLayout) findViewById(R.id.emailPage);
            emailPage.addView(etm);
        }
    }

    public void back(View view) {
        Intent intent = new Intent(this, selectPage.class);

        startActivity(intent);
    }

    public void sendMail(View view) {
        //For loop that fills in and sends mail I suppose
        for (int i = 0; i <= id; i++){
            System.out.println(editTextList.get(i).getText().toString());

            Intent email = new Intent(Intent.ACTION_SEND);
            //to
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{"williamchinh@gmail.com"});
            //Subject
            email.putExtra(Intent.EXTRA_SUBJECT, "subject");
            //Message
            email.putExtra(Intent.EXTRA_TEXT, "message");

            //need this to prompts email client only
            email.setType("message/rfc822");

            startActivity(Intent.createChooser(email, "kagekrigeren@mail.com"));
        }
    }
}