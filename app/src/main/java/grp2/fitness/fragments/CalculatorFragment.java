package grp2.fitness.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import grp2.fitness.NavigationActivity;
import grp2.fitness.R;
import grp2.fitness.helpers.UnitConverter;

public class CalculatorFragment extends Fragment implements
        TextWatcher{

    private int TDEE;
    private TextView bmiValue;
    private EditText bmiWeight;
    private EditText bmiHeight;
    private EditText energyKJ;
    private EditText energyCal;
    private Spinner activityLevel;
    private NavigationActivity activity;

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

        energyKJ = view.findViewById(R.id.energyKJ);
        energyCal = view.findViewById(R.id.energyCal);

        Button calcKj = view.findViewById(R.id.calcKJ);
        Button calcCal = view.findViewById(R.id.calcCal);
        Button calcTDEE = view.findViewById(R.id.calcTDEEBtn);

        activityLevel     = view.findViewById(R.id.activityLevelSp);

        activityLevel.setAdapter(
                new ArrayAdapter<>(
                        activity,
                        android.R.layout.simple_spinner_item,
                        UnitConverter.PhysicalActivity.values()
                )
        );

        calcKj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kjCalc();
            }
        });

        calcCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calCalc();
            }
        });

        calcTDEE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });

        //TODO - TDEE CALCULATOR

        bmiHeight.addTextChangedListener(this);
        energyKJ.addTextChangedListener(this);
        energyCal.addTextChangedListener(this);

        return view;
    }

    public void tdeeCalcMale(double weight, double height, double age, double activityLevel){
        TDEE = (int) ((66 + (13.7 * weight) + (5 * height) - (6.8 * age)) * activityLevel);
    }

    public void tdeeCalcFemale(double weight, double height, double age, double activityLevel){
        TDEE = (int) ((655 + (9.6 * weight) + (1.8 * height) - (4.7 * age)) * activityLevel);
    }

    public int getTDEE() {
        return TDEE;
    }

    public void setTDEE(int TDEE) {
        this.TDEE = TDEE;
    }

    private void bmiCalc() {
        if (bmiWeight.length() != 0 && bmiHeight.length() != 0) {
            double weight = Double.parseDouble(bmiWeight.getText().toString());
            double height = Double.parseDouble(bmiHeight.getText().toString());
            bmiValue.setText(String.format("= %.1f", UnitConverter.getBMI(weight, height)));
        }
    }

    public void kjCalc() {
        if (energyCal.length() != 0) {
            double calories = Double.parseDouble(energyCal.getText().toString());
            energyKJ.setText(Double.toString(Math.round(UnitConverter.getKjFromCal(calories))), TextView.BufferType.EDITABLE);
        }

    }

    public void calCalc() {
        if (energyKJ.length() != 0) {
            double kilojoules = Double.parseDouble(energyKJ.getText().toString());
            energyCal.setText(Double.toString(Math.round(UnitConverter.getCalFromKj(kilojoules))), TextView.BufferType.EDITABLE);
        }
    }

    // Text field updates
    @Override
    public void afterTextChanged(Editable editable){
        bmiCalc();
    }

    //Unused callbacks
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
}
