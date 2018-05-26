package grp2.fitness.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import grp2.fitness.NavigationActivity;
import grp2.fitness.R;

public class CalculatorFragment extends Fragment {

    private int TDEE;
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
}
