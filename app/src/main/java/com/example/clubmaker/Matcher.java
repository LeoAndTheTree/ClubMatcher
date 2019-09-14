package com.example.clubmaker;

import java.util.*;
import java.lang.Math;
import java.util.ArrayList;

public class Matcher {
    private ArrayList<Club> clubs;
    private Student student;
    private int[] hyperparams = new int[]{1,1,1,50};

    Matcher(ArrayList<Club> inClubs, Student inStudent){
        clubs = inClubs;
        student = inStudent;
    }

    ArrayList<Club> topClubs(int topk){
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

        double score1 = -1*Math.abs((double)size - candidate.clubSize)/candidate.clubSize;

        double score2 = candidate.commitmentCap(commit);

        double score3 = -1*candidate.timeConflict(schedule);

        double score4 = candidate.tagSatisfied(tags);

        double finalScore = hyperparams[3]*score4*(hyperparams[0]*score1)+ hyperparams[1]*score2 + hyperparams[2]*score3;

        return finalScore;
    }
}
