package com.example.clubmaker;

import java.util.ArrayList;

/*
* public void setTimeCommitment(int timeCommitment);
* public int getTimeCommitment();
* public void setSchedule(ArrayList<int[]> classes);
* public ArrayList<int[]> getSchedule();
* public void setClubsize (int clubsize);
* public int getClubsize (clubsize);
* public void setTypeOfClub(String[]);
* public String[] getTypeOfClub();
* public boolean hasTimeCommitment();
* public boolean hasSchedule();
* public boolean hasTypeOfClub();
* public boolean hasClubsize();
* */

public class Student {
    int timeCommitment = 0;
    ArrayList<int[]> schedule;
    String[] typeOfClub;
    int clubsize = 0;

    public void setTimeCommitment(int timeCommitment) {
        this.timeCommitment = timeCommitment;
    }
    public int getTimeCommitment() {
        return timeCommitment;
    }
    public void setSchedule(ArrayList<int[]> classes) {
        this.schedule = classes;
    }
    public ArrayList<int[]> getSchedule() {
        return schedule;
    }
    public void setClubsize(int clubsize) {
        this.clubsize = clubsize;
    }
    public int getClubsize() {
        return clubsize;
    }
    public boolean hasTimeCommitment(){
        return timeCommitment != 0;
    }
    public boolean hasSchedule(){
        return schedule != null;
    }
    public boolean hasTypeOfClub(){
        return typeOfClub != null;
    }
    public boolean hasClubsize(){
        return clubsize != 0;
    }

    public String[] getTypeOfClub() {
        return typeOfClub;
    }

    public void setTypeOfClub(String[] typeOfClub) {
        this.typeOfClub = typeOfClub;
    }
}
