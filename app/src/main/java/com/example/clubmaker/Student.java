package com.example.clubmaker;

import java.util.ArrayList;

public class Student {
    int timeCommitment = 0;
    ArrayList<int[]> classes;
    String[] preferrence;
    int clubsize = 0;

    public void setTimeCommitment(int timeCommitment) {
        this.timeCommitment = timeCommitment;
    }

    public int getTimeCommitment() {
        return timeCommitment;
    }

    public void setClasses(ArrayList<int[]> classes) {
        this.classes = classes;
    }

    public ArrayList<int[]> getClasses() {
        return classes;
    }

    public void setClubsize(int clubsize) {
        this.clubsize = clubsize;
    }

    public int getClubsize() {
        return clubsize;
    }
}
