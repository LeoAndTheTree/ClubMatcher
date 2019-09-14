package com.example.clubmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.androidbuts.multispinnerfilter.KeyPairBoolData;
import com.androidbuts.multispinnerfilter.MultiSpinnerSearch;
import com.androidbuts.multispinnerfilter.SpinnerListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Survey extends AppCompatActivity {
    private static final String TAG = "Survey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        EditText num = (EditText) findViewById(R.id.inputnumber);
        String value = num.getText().toString();
        int finalValue = Integer.parseInt(value);

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("value");

        final List<String> list = Arrays.asList("Anime", "Music", "Gaming", "Sports", "Math", "Computer Science", "Art");
        final List<KeyPairBoolData> listArray0 = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            KeyPairBoolData h = new KeyPairBoolData();
            h.setId(i + 1);
            h.setName(list.get(i));
            h.setSelected(false);
            listArray0.add(h);
        }

        final List<String> list1 = Arrays.asList("Resume buffing", "Fun", "Social activity", "Exploration", "Continued hobby", "Learning");
        final List<KeyPairBoolData> listArray1 = new ArrayList<>();

        for (int i = 0; i < list1.size(); i++) {
            KeyPairBoolData h = new KeyPairBoolData();
            h.setId(i + 1);
            h.setName(list1.get(i));
            h.setSelected(false);
            listArray1.add(h);
        }

        MultiSpinnerSearch searchMultiSpinnerUnlimited = (MultiSpinnerSearch) findViewById(R.id.searchMultiSpinnerUnlimited);
        searchMultiSpinnerUnlimited.setItems(listArray0, -1, new SpinnerListener() {
            @Override
            public void onItemsSelected(List<KeyPairBoolData> items) {
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).isSelected()) {
                        Log.i(TAG, i + " : " + items.get(i).getName() + " : " + items.get(i).isSelected());
                    }
                }
            }
        });
        MultiSpinnerSearch searchMultiSpinnerUnlimited2 = (MultiSpinnerSearch) findViewById(R.id.searchMultiSpinnerUnlimited2);
        searchMultiSpinnerUnlimited2.setItems(listArray1, -1, new SpinnerListener() {
            @Override
            public void onItemsSelected(List<KeyPairBoolData> items) {
                for (int i = 0; i < items.size(); i++) {
                    if (items.get(i).isSelected()) {
                        Log.i(TAG, i + " : " + items.get(i).getName() + " : " + items.get(i).isSelected());
                    }
                }
            }
        });
    }

    private static final int READ_REQUEST_CODE = 42;
    private Student student = new Student ();

    private ICSReader reader = new ICSReader();

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
                student.schedule = reader.parseICS(output);
                //displayICS.setText(calsses.get(0)[0] + " " + calsses.get(0)[1]);
            } catch (IOException e) {}
            for(int i = 0; i < student.schedule.size(); i++){
                //displayICS.append(student.classes.get(i)[0] + " " + student.classes.get(i)[1] + "\n");
            }
        }
    }


    protected String readTextFromUri(Uri uri) throws IOException {
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