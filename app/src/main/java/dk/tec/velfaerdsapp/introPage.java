package dk.tec.velfaerdsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class introPage extends AppCompatActivity
{
    private TextView showText;
    private EditText enterName;
    private EditText enterJob;

    String selectedItem = "";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // instantiating
        showText = findViewById(R.id.textView);
        enterName = findViewById(R.id.editTextName);
        enterJob = findViewById(R.id.editTextJob);

        // Calling the method to create the spinnerdropdown
        createSpinnerDropdown();
    }

    // creating and populating the dropdown
    private void createSpinnerDropdown() {

        Spinner spinner = (Spinner) findViewById(R.id.spinnerGender);

        List<String> list = new ArrayList<String>();
        list.add("Choose gender");
        list.add("Mand");
        list.add("kvinde");
        list.add("andet");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the ArrayAdapter to the spinner
        spinner.setAdapter(dataAdapter);

        // Attaching the listener to the spinner
        spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());

    }

    public class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            selectedItem = parent.getItemAtPosition(position).toString();
            if(selectedItem != "Choose gender" && selectedItem != null)
            {
                Toast.makeText(parent.getContext(), "gender selected is " + selectedItem, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    // Test til at se input er gemt i variablerne :)
    public void myButton(View view)
    {
        String name = enterName.getText().toString();
        String job = enterJob.getText().toString();
        String gender = selectedItem;
        showText.setText("Hello " + name + " job: " + job + " gender: " + gender);
    }


}