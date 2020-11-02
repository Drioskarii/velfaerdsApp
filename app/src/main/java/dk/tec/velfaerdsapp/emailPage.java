package dk.tec.velfaerdsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

public class emailPage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_page);

    }
    int id = 0;
    public void addEmail(View view) {
        ++id;



        //EditText is created here
        EditText editTextMail = new EditText(this);

        //This is where the parameter goes.
        editTextMail.setHint(getString(R.string.editTextEmail));
        editTextMail.setId(id);
        editTextMail.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        //This is where the EditText gets added to activity_email_page.xml
        LinearLayout emailPage = (LinearLayout) findViewById(R.id.emailPage);
        emailPage.addView(editTextMail);

        System.out.println(id);
    }


    public void back(View view) {
        Intent intent = new Intent(this, selectPage.class);

        startActivity(intent);
    }

    public void send(View view) {
    }
}