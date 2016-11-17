package creatingnew.kz.colorgame;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.IntegerRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class main extends AppCompatActivity  {
    private TextView meanShow,colorShow;
    private Button btnYes, btnNo;

    private ArrayList<Integer> colors;
    private ArrayList<String> colorsNames;


    private int leftTextIndex;
    private int rightTextIndex;
    private int leftColorsIndex;
    private int rightColorsIndex;
    private int numberOfColors = 5;

    private TextSwitcher mTextSwitcher;
    private TextSwitcher mTextSwitcher1;
    private int inOfCorrect=0;
    private int inOfIncorrect=0;

    public TextView timerView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        meanShow = (TextView)findViewById(R.id.meanShow);
        colorShow = (TextView)findViewById(R.id.colorShow);
        mTextSwitcher = (TextSwitcher)findViewById(R.id.sumOfCorrect);
        mTextSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(main.this);
                textView.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL);
                textView.setTextSize(40);
                textView.setTextColor(Color.RED);
                return textView;
            }
        });
        mTextSwitcher1 = (TextSwitcher)findViewById(R.id.sumOfInCorrect);
        mTextSwitcher1.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(main.this);
                textView.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL);
                textView.setTextSize(40);
                textView.setTextColor(Color.RED);
                return textView;
            }
        });
        Animation inAnimation = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in);
        Animation outAnimation = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out);
        mTextSwitcher.setInAnimation(inAnimation);
        mTextSwitcher.setOutAnimation(outAnimation);

        Animation inAnimation2 = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in);
        Animation outAnimation2 = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out);
        mTextSwitcher1.setInAnimation(inAnimation);
        mTextSwitcher1.setOutAnimation(outAnimation);

        btnNo = (Button)findViewById(R.id.btnNo);
        btnYes = (Button)findViewById(R.id.btnYes);
        genData();
        updateCounter();
        updatesView();
        timerView = (TextView)findViewById(R.id.timerView);
        timerView.setText("00:03:00");

        final CounterClass timer = new CounterClass(180000,1000);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // timer.start();
                onClickYes();
            }
        });
        
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // timer.start();
                onClickNo();
            }
        });



    }
    private void updateCounter() {
        mTextSwitcher.setText(String.valueOf(inOfCorrect));
        mTextSwitcher1.setText(String.valueOf(inOfIncorrect));
    }
    private void correct(){
        Snackbar.make(meanShow,"Correct!",Snackbar.LENGTH_SHORT).show();
        updateCounter();
    }

    private  void incorrect(){

        Snackbar.make(meanShow,"Wrong!",Snackbar.LENGTH_SHORT).show();
        updateCounter();

    }

    private void onClickYes(){
        if(leftTextIndex == rightColorsIndex){
            inOfCorrect++;
            correct();
        }else{
            inOfIncorrect++;
            incorrect();
        }
        updatesView();


    }
    private void onClickNo() {
        if(leftTextIndex != rightColorsIndex){
            inOfCorrect++;

            correct();


        }else{
            inOfIncorrect++;
            incorrect();
        }
        updatesView();



    }

    private void updatesView() {
        Random random = new Random();

        leftTextIndex = random.nextInt(numberOfColors);
        rightTextIndex = random.nextInt(numberOfColors);

        leftColorsIndex = random.nextInt(numberOfColors);
        rightColorsIndex = random.nextInt(numberOfColors);

        meanShow.setText(colorsNames.get(leftTextIndex));
        colorShow.setText(colorsNames.get(rightTextIndex));

        meanShow.setBackgroundResource(colors.get(leftColorsIndex));
        colorShow.setBackgroundResource(colors.get(rightColorsIndex));



    }

    private void genData(){
        colors = new ArrayList<>();
        colorsNames = new ArrayList<>();

        colors.add(android.R.color.holo_blue_dark);
        colorsNames.add("BLUE");

        colors.add(android.R.color.holo_red_dark);
        colorsNames.add("RED");

        colors.add(android.R.color.holo_green_dark);
        colorsNames.add("GREEN");

        colors.add(android.R.color.holo_orange_dark);
        colorsNames.add("ORANGE");

        colors.add(android.R.color.holo_purple);
        colorsNames.add("PURPLE");
    }


    class CounterClass extends CountDownTimer {



        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);

        }

        @Override
        public void onTick(long millisUntilFinished) {
            long milis =millisUntilFinished;
            String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(milis),
                    TimeUnit.MILLISECONDS.toMinutes(milis) - TimeUnit.HOURS.toHours(TimeUnit.MILLISECONDS.toHours(milis)),
                    TimeUnit.MILLISECONDS.toSeconds(milis)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milis)));

            timerView.setText(hms);
        }

        @Override
        public void onFinish() {

        }
    }




}

