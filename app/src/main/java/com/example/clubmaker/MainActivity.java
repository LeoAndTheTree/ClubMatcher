package com.example.clubmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final int READ_REQUEST_CODE = 42;
    private TextView displayICS = null;
    private Calendar c = Calendar.getInstance();
    private SimpleDateFormat match = new SimpleDateFormat("yyyy/M/dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayICS = (TextView) findViewById (R.id.textView2);
        displayICS.setText("");
        Button pickCalendar = (Button) findViewById(R.id.button);

    }

    public void pickCalendar(View view){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

        // Filter to only show results that can be "opened", such as a
        // file (as opposed to a list of contacts or timezones)
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        // Filter to show only images, using the image MIME data type.
        // If one wanted to search for ogg vorbis files, the type would be "audio/ogg".
        // To search for all documents available via installed storage providers,
        // it would be "*/*".
        //text/calendar
        intent.setType("text/calendar");

        startActivityForResult(intent, READ_REQUEST_CODE);
    }

    private Uri uri = null;

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent resultData) {

        // The ACTION_OPEN_DOCUMENT intent was sent with the request code
        // READ_REQUEST_CODE. If the request code seen here doesn't match, it's the
        // response to some other intent, and the code below shouldn't run at all.

        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.
            // Pull that URI using resultData.getData().
            if (resultData != null) {
                uri = resultData.getData();
//                Log.i(TAG, "Uri: " + uri.toString());
//                showImage(uri);
            }
            try{
                String output = readTextFromUri(uri);
                //displayICS.setText(output);
                ArrayList<int[]> calsses = parseICS(output);
                //displayICS.setText(calsses.get(0)[0] + " " + calsses.get(0)[1]);
            } catch (IOException e) {}
        }
    }

    private ArrayList<int[]> parseICS(String input){
        Reader inputString = new StringReader(input);
        BufferedReader reader = new BufferedReader(inputString);
        ArrayList<int[]> output = new ArrayList<>();
        try{
            String line = "";
            String year = "2000";
            String month = "11";
            String day = "15";
            int hour = 0;
            int minute = 0;
            int day_of_week = 0;
            int fminFromMon = 0;
            int sminFromMon = 0;
            while((line = reader.readLine()) != null){
                int split = line.indexOf(':');
                if(split != -1){
                    switch(line.substring(0,split)){
                        case "DTSTART":
                            year = line.substring(split+1, split+5);
                            month = line.substring(split+5, split+7);
                            day = line.substring(split+7, split+9);
                            c.setTime(match.parse(year + "/" + month + "/" + day));

                            hour = Integer.parseInt(line.substring(split+10, split+12));
                            minute = Integer.parseInt(line.substring(split+12, split+14));
                            day_of_week = (c.get(Calendar.DAY_OF_WEEK) -2 + 7) % 7;
                            fminFromMon = day_of_week * 1440 + hour * 60 + minute;
                            //displayICS.append(fminFromMon + "\n");
                            //displayICS.append(day_of_week + " " + hour + " " + minute + "\n");
                            //displayICS.setText(c.toString());
                            //displayICS.setText(year + " " + month + " " + day + " " + day_of_week.toString());
                            break;
                        case "DTEND":
                            year = line.substring(split+1, split+5);
                            month = line.substring(split+5, split+7);
                            day = line.substring(split+7, split+9);
                            c.setTime(match.parse(year + "/" + month + "/" + day));

                            hour = Integer.parseInt(line.substring(split+10, split+12));
                            minute = Integer.parseInt(line.substring(split+12, split+14));
                            day_of_week = (c.get(Calendar.DAY_OF_WEEK) -2 + 7) % 7;
                            sminFromMon = day_of_week * 1440 + hour * 60 + minute;
                            //displayICS.append(day_of_week + " " + hour + " " + minute + "\n");
                            //displayICS.append(fminFromMon + " " + sminFromMon + "\n");
                            int[] o = {fminFromMon, sminFromMon};
                            output.add(o);
                            break;
                        default: break;
                    }
                }
            }
        } catch(IOException e1){

        } catch(ParseException e2){}
        return output;
    }

    private String readTextFromUri(Uri uri) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (InputStream inputStream =
                     getContentResolver().openInputStream(uri);
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(Objects.requireNonNull(inputStream)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line+"\n");
            }
        }
        return stringBuilder.toString();
    }
}
