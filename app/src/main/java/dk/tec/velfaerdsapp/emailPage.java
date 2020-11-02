package dk.tec.velfaerdsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.view.View;

public class emailPage extends AppCompatActivity {
    LinearLayout emailPage = (LinearLayout) findViewById(R.id.emailPage);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_page);
    }
    public void addEmail(View view) {
        int id = 0;

        //EditText is created here
        EditText editTextMail = new EditText(this);

        //This is where the parameter goes.
        editTextMail.setHint(getString(R.string.editTextEmail));
        editTextMail.setId(Integer.parseInt("newMail"+id));
        String s = "";
        editTextMail.setInputType(Integer.parseInt(s));
        editTextMail.requestLayout();


        //This is where the EditText gets added to activity_email_page.xml
        emailPage.addView(editTextMail);
        ++id;
    }


    public void back(View view) {
        Intent intent = new Intent(this, selectPage.class);

        startActivity(intent);
    }

    public void send(View view) {
    }
}