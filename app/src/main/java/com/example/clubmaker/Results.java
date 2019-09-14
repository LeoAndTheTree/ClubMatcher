package com.example.clubmaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class Results extends AppCompatActivity {

    Button choice1;
    Button choice2;
    Button choice3;
    Button choice4;
    Button choice5;
    Matcher matcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        choice1 = (Button) findViewById(R.id.button5);
        choice2 = (Button) findViewById(R.id.button6);
        choice3 = (Button) findViewById(R.id.button7);
        choice4 = (Button) findViewById(R.id.button9);
        choice5 = (Button) findViewById(R.id.button8);

        matcher = (Matcher) getIntent().getSerializableExtra("matcher");
        try{
            ArrayList<Club> result = matcher.topClubs(5);
            choice1.setText(result.get(0).getName());
            choice2.setText(result.get(1).getName());
            choice3.setText(result.get(2).getName());
            choice4.setText(result.get(3).getName());
            choice5.setText(result.get(4).getName());
        }catch(Error e){
            choice1.setText(e.getMessage());
        }
    }
}
