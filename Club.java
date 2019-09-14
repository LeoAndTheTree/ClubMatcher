//this is a test java file
import java.util.*;
import java.lang.Math;
public enum Tags {ANIME, GAMING, PIANO};

public class Club
{
    private String clubName;
    private int[] clubMeetingTime = new int[2];
    private Arraylist<Tags> clubTags = new ArrayList<Tags>();
    private int clubSize;
    private int clubTimeCommitment;
    private String clubNotes;

    Club(){}
    Club()
    {
        
    }

    void setName(String name)
    {
        clubName = name;
    }

    String getName()
    {
        return clubName;
    }

    void setTags(ArrayList<Tags> append)
    {
        for(int i = 0; i < append.size(); i++)
        {
            clubTags.insert(append.get(i));
        }
    }

    ArrayList<Tags> getTags()
    {
        return clubTags;
    }

    void setTimeCommitment(int minutes)
    {
        clubTimeCommitment = minutes;
    }

    int getTimeCommitment()
    {
        return clubTimeCommitment;
    }

    void setClubSize(int people)
    {
        clubSize = people;
    }

    int getClubSize()
    {
        return clubSize;
    }

    void setMeetingTime(int day, int hour, int minute, int meetingLength)
    {
        clubMeetingTime[0] = ((day * 24) + hour) * 60 + minute;
        clubMeetingTime[1] = (clubMeetingTime[0] + meetingLength - 1) % 10080;
    }

    int[] getMeetingTime()
    {
        int[] copy = {clubMeetingTime[0], clubMeetingTime[1]};
        return copy;
    }

    int[] readMeetingTime()
    {
        int day = clubMeetingTime[0]/1440;
        int hour = (clubMeetingTime[0] /60) % 24;
        int minute = clubMeetingTime[0] % 60;
        int[] readable = {day, hour, minute,
                         (clubMeetingTime[1] - clubMeetingTime[0] + 10081) % 10080};
        return readable;
    }

    void setClubNotes(String notes)
    {
        clubNotes = notes;
    }

    String getClubNotes()
    {
        return clubNotes;
    }

    int timeConflict(ArrayList<int[]> schedule)
    {
        int totalMinutes = 0;
        for(int i = 0; i < schedule.length; i++)
        {
            int curConflict = min(clubMeetingTime[1], schedule.get(i)[1]) - max(clubMeetingTime[0], schedule.get(i)[0]) + 1;
            if(curConflict < 0) curConflict = 0;
            totalMinutes += curConflict;
        }
        return totalConflict;
    }

    //returns the difference in sizes, divided by the size requested by the user
    double sizeConflict(int desired)
    {
        if(desired == 0) return (double)clubSize;
        return abs((double)desired - clubSize) / desired;
    }

    //returns the percent of tags satisfied
    double tagSatisfied(ArrayList<Tags> desired)
    //@ensures 0 <= \result && \result <= 1
    {
        if(desired.size() == 0) return (double)1;
        int totalTag = 0;
        for(i = 0; i < desired.size(); i++)
        {
            for(int j = 0; j < clubTags.size(); j++)
            {
                if(desired.get(i) == clubTags.get(j))
                {
                    totalTag++;
                    break;
                }
            }
        }
        return ((double) totalTag) / desired.size();
    }


}