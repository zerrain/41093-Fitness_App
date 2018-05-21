package grp2.fitness.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import grp2.fitness.R;

public class CalculatorFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calculator, container, false);
    }

    public static double tdeeCalcMale(double weight, double height, double age, double activityLevel){
        return (66 + (13.7 * weight) + (5 * height) - (6.8 * age)) * activityLevel;
    }

    public static double tdeeCalcFemale(double weight, double height, double age, double activityLevel){
        return (655 + (9.6 * weight) + (1.8 * height) - (4.7 * age)) * activityLevel;
    }
}
