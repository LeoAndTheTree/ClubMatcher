package com.example.clubmaker;

import java.util.*;
import java.lang.Math;
import java.util.ArrayList;


public class Matcher {
    private ArrayList<Club> clubs;
    private Student student;

    Matcher(ArrayList<Club> inClubs, Student inStudent){
        clubs = inClubs;
        student = inStudent;
    }

    ArrayList<Club> topClubs(int topk){
        ArrayList<int> schedule = student.schedule;
        int commit = student.timeCommitment;
        int size = student.clubsize;
        String[] tags = student.typeOfClub;

    }
}
