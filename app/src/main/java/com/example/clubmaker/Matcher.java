package com.example.clubmaker;

import android.util.Log;

import java.io.Serializable;
import java.util.*;
import java.lang.Math;
import java.util.ArrayList;

public class Matcher implements Serializable {
    private ArrayList<Club> clubs;
    private Student student;
    //clubsize, time, conflict, satisfy
    private int[] hyperparams = new int[]{1,1,1,10};

    Matcher(ArrayList<Club> inClubs, Student inStudent){
        clubs = inClubs;
        student = inStudent;
    }

    ArrayList<Club> topClubs(int topk){
        Log.i("club", clubs.get(0).toString());
        for (Club candidate: clubs){
            candidate.score = heuristic(candidate);
        }
        Collections.sort(clubs, new ClubComparator());

        ArrayList<Club> tclubs = new ArrayList<Club>();
        for (int i =0; i<topk; i++){
            tclubs.add(clubs.get(i));
        }
        return tclubs;
    }

    double heuristic(Club candidate){
        ArrayList<int[]> schedule = student.schedule;
        int commit = student.timeCommitment;
        int size = student.clubsize;
        ArrayList<String> tags = new ArrayList<String>(Arrays.asList(student.typeOfClub));

        //size difference, the more negative the worse 0, -1
        double score1 = -1*Math.abs((double)size - candidate.clubSize)/candidate.clubSize;

        //minutes above exceed commitments, 0 or badly negative
        double score2 = candidate.commitmentCap(commit);

        //minutes conflicting large number
        double score3 = -1*candidate.timeConflict(schedule);

        //comment
        double score4 = candidate.tagSatisfied(tags);

        double finalScore = hyperparams[3]*score4*(hyperparams[0]*score1)+ hyperparams[1]*score2 + hyperparams[2]*score3;

        Log.i("indiv_scores", score1 + " " + score2 + " " +  score3 + " " +  score4);

        return finalScore;
    }
}
