//this is a test java file
import java.util.*;
import java.lang.Math;
public enum Tags {ANIME, GAMING, PIANO};//temp SHOULD DELETE AFTER TAGS ENUM CLASS IMPORTED


/* setName(string name)
 * setTags(ArrayList<Tags> append)
 * setTimeCommitment(int minutes)
 * setClubSize(int size)
 * setMeetingTime(int day, int hour, int minute, int meetinglength) -- sets start and end time in integer minute format
 * setClubNotes(String notes)
 * getName() - returns name as string
 * getTags() - returns Tags as ArrayList<Tags>
 * getTimeCommitment() - returns int
 * getClubSize() - return club size as int
 * getClubNotes() - returns club notes as string
 * getMeetingTime() - returns int[] meeting time in integer minute format
 * readMeetingTime() - returns int[] time containing day of week, hour, minute, and meeting length
 * timeConflict(ArrayList<int[]> schedule) - returns int minutes of conflict between club and schedule
 * sizeConflict(int desired) - returns double difference in size over desired size
 * tagSatisfied(ArrayList<Tags> desired) - returns percent of tags satisfied
 * commitmentCap() - returns difference between club and user time, negative if club exceeds user commit time
*/
public class Club
{
    private String clubName;
    private int[] clubMeetingTime = new int[2];
    private Arraylist<Tags> clubTags = new ArrayList<Tags>();
    private int clubSize;
    private int clubTimeCommitment;
    private String clubNotes;

    Club(){}
    Club(String name)
    {
        setName(name);
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

    //converts 
    void setMeetingTime(int day, int hour, int minute, int meetingLength)
    {
        clubMeetingTime[0] = ((day * 24) + hour) * 60 + minute;
        clubMeetingTime[1] = (clubMeetingTime[0] + meetingLength - 1) % 10080;
    }

    //returns meeting time in integer format
    int[] getMeetingTime()
    {
        int[] copy = {clubMeetingTime[0], clubMeetingTime[1]};
        return copy;
    }

    //returns day, hour, minute, and meeting length for the club
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

    //returns the minutes of conflict between the club and their schedule
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

    //return int minutes, negative if clubTimeCommitment exceeds their time
    int commitmentCap(int commit)
    {
        return commit - clubTimeCommitment;
    }
}
