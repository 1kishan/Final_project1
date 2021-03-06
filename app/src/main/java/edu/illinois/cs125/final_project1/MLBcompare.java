package edu.illinois.cs125.final_project1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MLBcompare extends AppCompatActivity {
    MLB getMethods = new MLB();
    public TextView homeRuns1;
    public TextView rbi1;
    public TextView battingAvg1;
    public TextView strikeouts1;
    public TextView errors1;
    public TextView homeruns2;
    public TextView rbi2;
    public TextView battingAvg2;
    public TextView strikeouts2;
    public TextView errors2;

    public TextView player1name;
    public TextView player2name;

    public TextView bettermlbplayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mlbcompare);


        Button mlbcompareback = (Button) findViewById(R.id.mlb_compare_back);
        Intent intent = getIntent();
        String input1 = intent.getStringExtra("p1");
        String input2 = intent.getStringExtra("p2");
        int season = intent.getIntExtra("year",2018);
        boolean playOff = intent.getBooleanExtra("playoffs",false);
        Data player1 = new Data(input1,"MLB",season,playOff);
        Data player2 = new Data(input2, "MLB",season,playOff);
        String jsonPlayer1 = player1.apiGetData();
        String jsonPlayer2 = player2.apiGetData();
        String p1Name = input1;
        String p2Name = input2;
        p1Name = p1Name.replace('-',' ');
        p2Name = p2Name.replace('-',' ');

        //player 1 data

        player1name = findViewById(R.id.mlbname1);
        player1name.setText(p1Name);

        player2name = findViewById(R.id.mlbname2);
        player2name.setText(p2Name);

        homeRuns1 = findViewById(R.id.hr1);
        homeRuns1.setText(Integer.toString(getMethods.getHR(jsonPlayer1)));

        rbi1 = findViewById(R.id.rbi1);
        rbi1.setText(Integer.toString(getMethods.getRBI(jsonPlayer1)));

        battingAvg1 = findViewById(R.id.bavg1);
        battingAvg1.setText(Double.toString(getMethods.getBattingAvg(jsonPlayer1)));

        strikeouts1 = findViewById(R.id.so1);
        strikeouts1.setText(Integer.toString(getMethods.getStrikeouts(jsonPlayer1)));

        errors1 = findViewById(R.id.err1);
        errors1.setText(Integer.toString(getMethods.getErrors(jsonPlayer1)));


        //player 2 data
        homeruns2 = findViewById(R.id.hr2);
        homeruns2.setText(Integer.toString(getMethods.getHR(jsonPlayer2)));

        rbi2 = findViewById(R.id.rbi2);
        rbi2.setText(Integer.toString(getMethods.getRBI(jsonPlayer2)));

        battingAvg2 = findViewById(R.id.bavg2);
        battingAvg2.setText(Double.toString(getMethods.getBattingAvg(jsonPlayer2)));

        strikeouts2 = findViewById(R.id.so2);
        strikeouts2.setText(Integer.toString(getMethods.getStrikeouts(jsonPlayer2)));

        errors2 = findViewById(R.id.err2);
        errors2.setText(Integer.toString(getMethods.getErrors(jsonPlayer2)));

        //set better player
        bettermlbplayer = findViewById(R.id.the_better_player3);
        bettermlbplayer.setText("The better player is: " + getMethods.betterPlayer(input1,input2));
        bettermlbplayer.setTextColor(Color.GREEN);



        //Sets Color

        if (getMethods.getHR(jsonPlayer1) > getMethods.getHR(jsonPlayer2)) {
            homeRuns1.setTextColor(Color.GREEN);
            homeruns2.setTextColor(Color.RED);
        } else {
            homeruns2.setTextColor(Color.GREEN);
            homeRuns1.setTextColor(Color.RED);
        }
        if (getMethods.getRBI(jsonPlayer1) > getMethods.getRBI(jsonPlayer2)) {
            rbi1.setTextColor(Color.GREEN);
            rbi2.setTextColor(Color.RED);
        } else {
            rbi2.setTextColor(Color.GREEN);
            rbi1.setTextColor(Color.RED);
        }
        if (getMethods.getErrors(jsonPlayer1) < getMethods.getErrors(jsonPlayer2)) {
            errors1.setTextColor(Color.GREEN);
            errors2.setTextColor(Color.RED);
        } else {
            errors2.setTextColor(Color.GREEN);
            errors1.setTextColor(Color.RED);
        }
        if (getMethods.getBattingAvg(jsonPlayer1) > getMethods.getBattingAvg(jsonPlayer2)) {
            battingAvg1.setTextColor(Color.GREEN);
            battingAvg2.setTextColor(Color.RED);
        } else {
            battingAvg2.setTextColor(Color.GREEN);
            battingAvg1.setTextColor(Color.RED);
        }
        if (getMethods.getStrikeouts(jsonPlayer1) < getMethods.getStrikeouts(jsonPlayer2)) {
            strikeouts1.setTextColor(Color.GREEN);
            strikeouts2.setTextColor(Color.RED);
        } else {
            strikeouts2.setTextColor(Color.GREEN);
            strikeouts1.setTextColor(Color.RED);
        }
        mlbcompareback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }



}
