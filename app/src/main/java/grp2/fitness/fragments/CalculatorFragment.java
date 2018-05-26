package grp2.fitness.fragments;

import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import grp2.fitness.NavigationActivity;
import grp2.fitness.R;
import grp2.fitness.helpers.UnitConverter;

public class CalculatorFragment extends Fragment implements
        TextWatcher,
        View.OnClickListener,
        AdapterView.OnItemSelectedListener{

    private int TDEE;
    private TextView bmiValue;
    private TextView tdeeValue;
    private EditText bmiWeight;
    private EditText bmiHeight;
    private EditText energyKJ;
    private EditText energyCal;
    private EditText height;
    private EditText weight;
    private EditText age;
    private Spinner activityLevel;
    private RadioGroup gender;
    private NavigationActivity activity;
    private boolean hasConvertedEnergy;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(getActivity() == null || getContext() == null){
            return null;
        }

        View view = inflater.inflate(R.layout.fragment_calculator, container, false);
        activity = (NavigationActivity) getActivity();
        activity.hideLoadingIcon();

        bmiValue = view.findViewById(R.id.bmiValue);
        bmiWeight = view.findViewById(R.id.bmiWeight);
        bmiHeight = view.findViewById(R.id.bmiHeight);

        tdeeValue = view.findViewById(R.id.tdeeResult);
        energyKJ = view.findViewById(R.id.energyKJ);
        energyCal = view.findViewById(R.id.energyCal);

        gender = view.findViewById(R.id.genderBtns);
        height = view.findViewById(R.id.tdeeHeight);
        weight = view.findViewById(R.id.tdeeWeight);
        age = view.findViewById(R.id.tdeeAge);

        activityLevel = view.findViewById(R.id.activityLevelSp);
        activityLevel.setAdapter(
                new ArrayAdapter<>(
                        activity,
                        android.R.layout.simple_spinner_item,
                        UnitConverter.PhysicalActivity.values()
                )
        );

        addListeners();

        return view;
    }

    private void addListeners(){
        activityLevel.setOnItemSelectedListener(this);
        gender.setOnClickListener(this);

        bmiHeight.addTextChangedListener(this);
        bmiWeight.addTextChangedListener(this);
        energyKJ.addTextChangedListener(this);
        energyCal.addTextChangedListener(this);
        height.addTextChangedListener(this);
        weight.addTextChangedListener(this);
        age.addTextChangedListener(this);
    }

    private void bmiCalc() {
        if (bmiWeight.length() != 0 && bmiHeight.length() != 0) {
            double weight = Double.parseDouble(bmiWeight.getText().toString());
            double height = Double.parseDouble(bmiHeight.getText().toString());
            bmiValue.setText(String.format("= %.1f", UnitConverter.getBMI(weight, height / 100)));
        }
    }

    private void tdeeCalc(){
        if(age.length() != 0 && age.length() != 0){
            UnitConverter.Gender gender = UnitConverter.Gender.MALE;
            UnitConverter.PhysicalActivity activityLevel = (UnitConverter.PhysicalActivity) this.activityLevel.getSelectedItem();

            if(this.gender.getCheckedRadioButtonId() == R.id.femaleSelect){
                gender = UnitConverter.Gender.FEMALE;
            }

            try{
                int age = Integer.parseInt(this.age.getText().toString());
                Double height = Double.parseDouble(this.height.getText().toString());
                Double weight = Double.parseDouble(this.weight.getText().toString());

                Double requiredEnergy = UnitConverter.getRequiredKj(gender, activityLevel, weight, height / 100, age);
                String energyString = String.format(Locale.US,"Required Energy: %.2f kJ", requiredEnergy);

                tdeeValue.setText(energyString);
            }catch (NumberFormatException e){
                Toast.makeText(activity, "Invalid age", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void kjCalc() {
        if (energyCal.length() != 0 && energyCal.hasFocus()) {
            hasConvertedEnergy = true;
            double calories = Double.parseDouble(energyCal.getText().toString());
            energyKJ.setText(Double.toString(Math.round(UnitConverter.getKjFromCal(calories))), TextView.BufferType.EDITABLE);
        }

    }

    public void calCalc() {
        if (energyKJ.length() != 0 && energyKJ.hasFocus()) {
            hasConvertedEnergy = true;
            double kilojoules = Double.parseDouble(energyKJ.getText().toString());
            energyCal.setText(Double.toString(Math.round(UnitConverter.getCalFromKj(kilojoules))), TextView.BufferType.EDITABLE);
        }
    }

    // Text field updates
    @Override
    public void afterTextChanged(Editable editable){
        bmiCalc();
        tdeeCalc();
        if(!hasConvertedEnergy){
            kjCalc();
            calCalc();
        }else{
            hasConvertedEnergy = false;
        }
    }

    //Spinner changes
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        tdeeCalc();
    }

    //Radio pressed
    @Override
    public void onClick(View view) {
        tdeeCalc();
    }
    //Unused callbacks
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}


}
