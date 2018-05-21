package grp2.fitness.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import grp2.fitness.R;

public class DiaryFragment extends Fragment {

    private int calGoal;
    private int calCurrent;
    private int calRemaining;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_diary, container, false);
    }

    public int getCalGoal() {
        return calGoal;
    }

    public void setCalGoal(int calGoal) {
        this.calGoal = calGoal;
    }

    public int getCalCurrent() {
        return calCurrent;
    }

    public void setCalCurrent(int calCurrent) {
        this.calCurrent = calCurrent;
    }

    public int getCalRemaining() {
        return calRemaining;
    }

    public void setCalRemaining(int calRemaining) {
        this.calRemaining = calRemaining;
    }
}
