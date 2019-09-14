package com.example.clubmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.androidbuts.multispinnerfilter.KeyPairBoolData;
import com.androidbuts.multispinnerfilter.MultiSpinnerSearch;
import com.androidbuts.multispinnerfilter.SpinnerListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Survey extends AppCompatActivity {
    private static final String TAG = "Survey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

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
}