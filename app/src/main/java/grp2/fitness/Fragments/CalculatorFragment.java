package grp2.fitness.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import grp2.fitness.R;

public class CalculatorFragment extends Fragment {

    private int TDEE;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calculator, container, false);
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
}
