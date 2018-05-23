package grp2.fitness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import grp2.fitness.Fragments.CalculatorFragment;
import grp2.fitness.Fragments.DiaryFragment;
import grp2.fitness.Fragments.PedometerFragment;

public class SetupGoals extends AppCompatActivity {

    private EditText goalStepsEdit;
    private TextView goalCalText;
    DiaryFragment diaryFragment = (DiaryFragment) getSupportFragmentManager().findFragmentById(R.id.diary);
    CalculatorFragment calculatorFragment = new CalculatorFragment();
    private static final String[] goals = new String[]{"Maintain my weight", "Lose 0.5 kgs every week", "Lose 1 kg every week"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_goals);

        Spinner goalsList = findViewById(R.id.goalsSpinner);

        goalsList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        diaryFragment.setCalCut(0);
                        break;
                    case 1:
                        diaryFragment.setCalCut(550);
                        break;
                    case 2:
                        diaryFragment.setCalCut(1100);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        goalStepsEdit = findViewById(R.id.goalStepsEdit);

        goalCalText = findViewById(R.id.goalCalText);

        Button calculateGoalBtn = findViewById(R.id.calculateGoalBtn);
        calculateGoalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (goalStepsEdit.getText().toString().length() == 0)
                    Toast.makeText(SetupGoals.this, "Please enter your goal steps", Toast.LENGTH_SHORT).show();
                else {
                    diaryFragment.setCalGoal((int) (calculatorFragment.getTDEE() - diaryFragment.getCalCut()));
                    goalCalText.setText("" + calculatorFragment.getTDEE());
                }
            }
        });

        Button loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, goals);
        goalsList.setAdapter(adapter);
    }

    private void openLogin(){
        finish();
    }
}
