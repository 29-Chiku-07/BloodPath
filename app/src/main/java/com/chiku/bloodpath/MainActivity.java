package com.example.bloodpath;

import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView iv_11,iv_12,iv_13,iv_14,
              iv_21,iv_22,iv_23,iv_24,
              iv_31,iv_32,iv_33,iv_34,
              iv_41,iv_42,iv_43,iv_44,
              iv_51,iv_52,iv_53,iv_54;

    Button b_play;
    TextView tv_time , tv_score , tv_best;
    Random r;

    int rockLocationRow1, rockLocationRow2 , rockLocationRow3, rockLocationRow4,rockLocationRow5;

    int frameImage , pavInFrameImage , tapImage , emptyimage;

    int currentScore =0;
    int bestScore;

    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getSharedPreferences("PREFS",0);
        bestScore = preferences.getInt("highscore",0);

        iv_11 = (ImageView)findViewById(R.id.iv_11);
        iv_12 = (ImageView)findViewById(R.id.iv_12);
        iv_13 = (ImageView)findViewById(R.id.iv_13);
        iv_14 = (ImageView)findViewById(R.id.iv_14);

        iv_21 = (ImageView)findViewById(R.id.iv_21);
        iv_22 = (ImageView)findViewById(R.id.iv_22);
        iv_23 = (ImageView)findViewById(R.id.iv_23);
        iv_24 = (ImageView)findViewById(R.id.iv_24);

        iv_31 = (ImageView)findViewById(R.id.iv_31);
        iv_32 = (ImageView)findViewById(R.id.iv_32);
        iv_33 = (ImageView)findViewById(R.id.iv_33);
        iv_34 = (ImageView)findViewById(R.id.iv_34);

        iv_41 = (ImageView)findViewById(R.id.iv_41);
        iv_42 = (ImageView)findViewById(R.id.iv_42);
        iv_43 = (ImageView)findViewById(R.id.iv_43);
        iv_44 = (ImageView)findViewById(R.id.iv_44);

        iv_51 = (ImageView)findViewById(R.id.iv_51);
        iv_52 = (ImageView)findViewById(R.id.iv_52);
        iv_53 = (ImageView)findViewById(R.id.iv_53);
        iv_54 = (ImageView)findViewById(R.id.iv_54);

        tv_score = (TextView) findViewById(R.id.tv_score);
        tv_score.setText("SCORE : " + currentScore);

        tv_best = (TextView) findViewById(R.id.tv_best);
        tv_best.setText("BEST : "+bestScore);

        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_time.setText("TIME : "+ millisToTime(15000));

        b_play = (Button) findViewById(R.id.bplay);

        r = new Random();
        loadImages();

        timer = new CountDownTimer(15000,500) {
            @Override
            public void onTick(long l) {
                tv_time.setText("TIME : "+ millisToTime(l) + 1);
            }

            @Override
            public void onFinish() {
                tv_time.setText("TIME : "+ millisToTime(0));

                iv_31.setEnabled(false);
                iv_32.setEnabled(false);
                iv_33.setEnabled(false);
                iv_34.setEnabled(false);
                b_play.setVisibility(View.VISIBLE);

                iv_11.setImageResource(emptyimage);
                iv_12.setImageResource(emptyimage);
                iv_13.setImageResource(emptyimage);
                iv_14.setImageResource(emptyimage);

                iv_21.setImageResource(emptyimage);
                iv_22.setImageResource(emptyimage);
                iv_23.setImageResource(emptyimage);
                iv_24.setImageResource(emptyimage);

                iv_31.setImageResource(emptyimage);
                iv_32.setImageResource(emptyimage);
                iv_33.setImageResource(emptyimage);
                iv_34.setImageResource(emptyimage);

                iv_41.setImageResource(emptyimage);
                iv_42.setImageResource(emptyimage);
                iv_43.setImageResource(emptyimage);
                iv_44.setImageResource(emptyimage);

                iv_51.setImageResource(emptyimage);
                iv_52.setImageResource(emptyimage);
                iv_53.setImageResource(emptyimage);
                iv_54.setImageResource(emptyimage);

                Toast.makeText(MainActivity.this, "Game Over", Toast.LENGTH_SHORT).show();

                if(currentScore > bestScore)
                {
                    bestScore = currentScore;
                    tv_best.setText("BEST : "+ bestScore);

                    SharedPreferences preferences1 = getSharedPreferences("PREFS",0);
                    SharedPreferences.Editor editor = preferences1.edit();
                    editor.apply();
                }
            }
        };

        iv_31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rockLocationRow3 == 1){
                    continueGame();
                } else{
                    endGame();
                }
            }
        });

        iv_32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rockLocationRow3 == 2){
                    continueGame();
                } else{
                    endGame();
                }
            }
        });

        iv_33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rockLocationRow3 == 3){
                    continueGame();
                } else{
                    endGame();
                }
            }
        });
        iv_34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rockLocationRow3 == 4){
                    continueGame();
                } else{
                    endGame();
                }
            }
        });

        b_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initGame();
            }
        });





    }


    private void continueGame(){
        //row5
        rockLocationRow5 = rockLocationRow4;
        setRockLocation(rockLocationRow5,5);

        //row4
        rockLocationRow4 = rockLocationRow3;
        setRockLocation(rockLocationRow4,4);

        //row3
        rockLocationRow3 = rockLocationRow2;
        setRockLocation(rockLocationRow3,3);

        //row2
        rockLocationRow2 = rockLocationRow1;
        setRockLocation(rockLocationRow2,2);

        //row1
        rockLocationRow1 = r.nextInt(4) + 1;
        setRockLocation(rockLocationRow1,1);

        currentScore++;
        tv_score.setText("SCORE : "+ currentScore);

    }

    private void initGame(){
        iv_31.setEnabled(true);
        iv_32.setEnabled(true);
        iv_33.setEnabled(true);
        iv_34.setEnabled(true);
        b_play.setVisibility(View.INVISIBLE);

        currentScore=0;
        tv_score.setText("SCORE : "+ currentScore);

        timer.start();

        //row5 nothing
        //row4
        rockLocationRow4 = 2;
        iv_42.setImageResource(pavInFrameImage);

        //row3
        rockLocationRow3 = 2;
        iv_32.setImageResource(tapImage);

        //row2
        rockLocationRow2 = r.nextInt(4) + 1;
        setRockLocation(rockLocationRow2 , 2);

        //row1
        rockLocationRow1 = r.nextInt(4) + 1;
        setRockLocation(rockLocationRow1 , 1);

    }

    private void endGame(){
        timer.cancel();


        iv_31.setEnabled(false);
        iv_32.setEnabled(false);
        iv_33.setEnabled(false);
        iv_34.setEnabled(false);
        b_play.setVisibility(View.VISIBLE);

        iv_11.setImageResource(emptyimage);
        iv_12.setImageResource(emptyimage);
        iv_13.setImageResource(emptyimage);
        iv_14.setImageResource(emptyimage);

        iv_21.setImageResource(emptyimage);
        iv_22.setImageResource(emptyimage);
        iv_23.setImageResource(emptyimage);
        iv_24.setImageResource(emptyimage);

        iv_31.setImageResource(emptyimage);
        iv_32.setImageResource(emptyimage);
        iv_33.setImageResource(emptyimage);
        iv_34.setImageResource(emptyimage);

        iv_41.setImageResource(emptyimage);
        iv_42.setImageResource(emptyimage);
        iv_43.setImageResource(emptyimage);
        iv_44.setImageResource(emptyimage);

        iv_51.setImageResource(emptyimage);
        iv_52.setImageResource(emptyimage);
        iv_53.setImageResource(emptyimage);
        iv_54.setImageResource(emptyimage);

        Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();

    }

    private void setRockLocation(int place , int row){
        if(row==1){
            iv_11.setImageResource(emptyimage);
            iv_12.setImageResource(emptyimage);
            iv_13.setImageResource(emptyimage);
            iv_14.setImageResource(emptyimage);

            switch (place){
                case 1:
                    iv_11.setImageResource(frameImage);
                    break;
                case 2:
                    iv_12.setImageResource(frameImage);
                    break;
                case 3:
                    iv_13.setImageResource(frameImage);
                    break;
                case 4:
                    iv_14.setImageResource(frameImage);
                    break;

            }
        }

        if(row==2){
            iv_21.setImageResource(emptyimage);
            iv_22.setImageResource(emptyimage);
            iv_23.setImageResource(emptyimage);
            iv_24.setImageResource(emptyimage);

            switch (place){
                case 1:
                    iv_21.setImageResource(frameImage);
                    break;
                case 2:
                    iv_22.setImageResource(frameImage);
                    break;
                case 3:
                    iv_23.setImageResource(frameImage);
                    break;
                case 4:
                    iv_24.setImageResource(frameImage);
                    break;


            }
        }


        if(row==3){
            iv_31.setImageResource(emptyimage);
            iv_32.setImageResource(emptyimage);
            iv_33.setImageResource(emptyimage);
            iv_34.setImageResource(emptyimage);

            switch (place){
                case 1:
                    iv_31.setImageResource(tapImage);
                    break;
                case 2:
                    iv_32.setImageResource(tapImage);
                    break;
                case 3:
                    iv_33.setImageResource(tapImage);
                    break;
                case 4:
                    iv_34.setImageResource(tapImage);
                    break;

            }
        }


        if(row==4){
            iv_41.setImageResource(emptyimage);
            iv_42.setImageResource(emptyimage);
            iv_43.setImageResource(emptyimage);
            iv_44.setImageResource(emptyimage);

            switch (place){
                case 1:
                    iv_41.setImageResource(pavInFrameImage);
                    break;
                case 2:
                    iv_42.setImageResource(pavInFrameImage);
                    break;
                case 3:
                    iv_43.setImageResource(pavInFrameImage);
                    break;
                case 4:
                    iv_44.setImageResource(pavInFrameImage);
                    break;

            }
        }

        if(row==5){
            iv_51.setImageResource(emptyimage);
            iv_52.setImageResource(emptyimage);
            iv_53.setImageResource(emptyimage);
            iv_54.setImageResource(emptyimage);

            switch (place){
                case 1:
                    iv_51.setImageResource(frameImage);
                    break;
                case 2:
                    iv_52.setImageResource(frameImage);
                    break;
                case 3:
                    iv_53.setImageResource(frameImage);
                    break;
                case 4:
                    iv_54.setImageResource(frameImage);
                    break;

            }
        }
    }

    private int millisToTime(long millis){
        return (int) millis/1000;
    }


    private void loadImages(){
        frameImage = R.drawable.rframe;
        pavInFrameImage = R.drawable.redframe;
        tapImage = R.drawable.rtap;
        emptyimage = R.drawable.empty;

    }

}