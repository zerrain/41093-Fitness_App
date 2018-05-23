package grp2.fitness;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import grp2.fitness.Fragments.CalculatorFragment;

public class SetupActivity extends AppCompatActivity {

    private double TDEE;
    private double actLev;
    private EditText editWeight;
    private EditText editHeight;
    private EditText editAge;
    private RadioGroup genderButtons;
    private TextView tdeeSetup;
    private static final String[] activityLevel = new String[]{"Little or no Activity", "Light exercise 1-3 days a week", "Moderate Exercise 3-5 days a week", "Heavy Exercise 6-7 days a week", "Very Heavy Exercise 2x A Day/Physical Job"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        genderButtons = findViewById(R.id.genderBtns);


        Spinner dropdown = findViewById(R.id.spinner);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        actLev = 1.2;
                        break;
                    case 1:
                        actLev = 1.375;
                        break;
                    case 2:
                        actLev = 1.55;
                        break;
                    case 3:
                        actLev = 1.725;
                        break;
                    case 4:
                        actLev = 1.9;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        tdeeSetup = findViewById(R.id.tdeeSetup);
        Button nextButton = findViewById(R.id.nextBtn);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSetupGoals();
            }
        });

        editWeight = findViewById(R.id.editWeight);
        editHeight = findViewById(R.id.editHeight);
        editAge = findViewById(R.id.editAge);

        Button calculateButton = findViewById(R.id.calculateTDEEBtn);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editWeight.getText().toString().length() == 0)
                    Toast.makeText(SetupActivity.this, "Please enter a weight", Toast.LENGTH_SHORT).show();
                else if (editHeight.getText().toString().length() == 0)
                    Toast.makeText(SetupActivity.this, "Please enter a height", Toast.LENGTH_SHORT).show();
                else if (editAge.getText().toString().length() == 0)
                    Toast.makeText(SetupActivity.this, "Please enter an age", Toast.LENGTH_SHORT).show();
                else
                    calculateTDEE();
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, activityLevel);
        dropdown.setAdapter(adapter);
    }

    public void calculateTDEE() {
        int selectedRadio = genderButtons.getCheckedRadioButtonId();
        CalculatorFragment calculatorFragment = (CalculatorFragment) getSupportFragmentManager().findFragmentById(R.id.calculator);

        switch (selectedRadio) {
            case R.id.maleSelect:
                calculatorFragment.tdeeCalcMale(Double.parseDouble(editWeight.getText().toString()), Double.parseDouble(editHeight.getText().toString()), Double.parseDouble(editAge.getText().toString()), actLev);
                break;
            case R.id.femaleSelect:
                calculatorFragment.tdeeCalcFemale(Double.parseDouble(editWeight.getText().toString()), Double.parseDouble(editHeight.getText().toString()), Double.parseDouble(editAge.getText().toString()), actLev);
                break;
            default:
                Toast.makeText(this, "Please Select A Gender", Toast.LENGTH_SHORT).show();
                break;
        }
        tdeeSetup.setText(calculatorFragment.getTDEE() + "");
    }

    private void openSetupGoals() {
        Intent intent = new Intent(this, SetupGoals.class);

        if (!(TDEE > 500))
            Toast.makeText(this, "Please calculate a valid TDEE", Toast.LENGTH_SHORT).show();
        else
            startActivity(intent);
    }
}
