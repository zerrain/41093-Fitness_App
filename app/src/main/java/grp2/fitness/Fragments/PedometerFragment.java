package grp2.fitness.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import grp2.fitness.R;

public class PedometerFragment extends Fragment {

    private int stepsGoal;
    private int stepsCurrent;
    private int stepsRemaining;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pedometer, container, false);
    }

    public int getStepsGoal() {
        return stepsGoal;
    }

    public void setStepsGoal(int stepsGoal) {
        this.stepsGoal = stepsGoal;
    }

    public int getStepsCurrent() {
        return stepsCurrent;
    }

    public void setStepsCurrent(int stepsCurrent) {
        this.stepsCurrent = stepsCurrent;
    }

    public int getStepsRemaining() {
        return stepsRemaining;
    }

    public void setStepsRemaining(int stepsRemaining) {
        this.stepsRemaining = stepsRemaining;
    }
}
