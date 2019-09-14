package com.example.clubmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MoreInformation extends AppCompatActivity {

    TextView clubName;
    TextView clubInfo;
    Club club;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_information);

        clubName = (TextView) findViewById(R.id.textView6);
        clubInfo = (TextView) findViewById(R.id.textView11);
        club = (Club) getIntent().getSerializableExtra("club");
        clubName.setText(club.getName());
        clubInfo.setText(club.getClubNotes());
    }
    public void enterMain(View v) {
        startActivity(new Intent(MoreInformation.this, MainActivity.class));
    }
}
