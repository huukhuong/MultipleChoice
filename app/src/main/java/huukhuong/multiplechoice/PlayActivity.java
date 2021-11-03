package huukhuong.multiplechoice;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import huukhuong.multiplechoice.model.Question;
import huukhuong.multiplechoice.util.PlayGame;

public class PlayActivity extends AppCompatActivity {

    private int num = 0;
    private int correct = 0;
    private ImageView btnBack;
    private TextView txtCountDown;
    private TextView txtNum;
    private TextView txtQuestion;
    private Button btnNext;
    private TextView txvAnsA;
    private TextView txvAnsB;
    private TextView txvAnsC;
    private TextView txvAnsD;
    private LinearLayout answerA;
    private LinearLayout answerB;
    private LinearLayout answerC;
    private LinearLayout answerD;
    private String selectedAnswer;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        addControls();
        addEvents();
    }

    private void addControls() {
        btnBack = findViewById(R.id.btnBack);
        txtCountDown = findViewById(R.id.txtCountDown);
        txtNum = findViewById(R.id.txtNum);
        txtQuestion = findViewById(R.id.txtQuestion);
        btnNext = findViewById(R.id.btnNext);

        txvAnsA = findViewById(R.id.txvAnsA);
        txvAnsB = findViewById(R.id.txvAnsB);
        txvAnsC = findViewById(R.id.txvAnsC);
        txvAnsD = findViewById(R.id.txvAnsD);

        answerA = findViewById(R.id.answerA);
        answerB = findViewById(R.id.answerB);
        answerC = findViewById(R.id.answerC);
        answerD = findViewById(R.id.answerD);

        setQuestion();

        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
        long minutes = 600000;
        timer = new CountDownTimer(minutes, 1000) {

            public void onTick(long millisUntilFinished) {
                txtCountDown.setText(sdf.format(millisUntilFinished));
            }

            public void onFinish() {

            }

        };
        timer.start();
    }

    private void addEvents() {
        btnBack.setOnClickListener(v -> finish());
        btnNext.setOnClickListener(v -> nextQuestion());
        answerA.setOnClickListener(v -> setColorAnswer(answerA, "A"));
        answerB.setOnClickListener(v -> setColorAnswer(answerB, "B"));
        answerC.setOnClickListener(v -> setColorAnswer(answerC, "C"));
        answerD.setOnClickListener(v -> setColorAnswer(answerD, "D"));
    }

    private void setQuestion() {
        btnNext.setEnabled(false);
        btnNext.setAlpha(.5f);

        txtNum.setText("Câu hỏi số: " + (num + 1));
        Question question = PlayGame.questionPlay.get(num);
        txtQuestion.setText(question.getQuestion());
        txvAnsA.setText(question.getAnsA());
        txvAnsB.setText(question.getAnsB());
        txvAnsC.setText(question.getAnsC());
        txvAnsD.setText(question.getAnsD());

        setColorAnswer(null, "");
    }

    private void nextQuestion() {
        num++;
        Question question = PlayGame.questionPlay.get(num);
        // kiem tra dap an
        if (selectedAnswer.equals(question.getCorrect())) {
            correct++;
            switch (selectedAnswer.charAt(0)) {
                case 'A':
                    answerA.setBackgroundResource(R.color.cardAnswerCorrect);
                    break;
                case 'B':
                    answerB.setBackgroundResource(R.color.cardAnswerCorrect);
                    break;
                case 'C':
                    answerC.setBackgroundResource(R.color.cardAnswerCorrect);
                    break;
                case 'D':
                    answerD.setBackgroundResource(R.color.cardAnswerCorrect);
                    break;
            }
        } else {
            switch (question.getCorrect().charAt(0)) {
                case 'A':
                    answerA.setBackgroundResource(R.color.cardAnswerError);
                    break;
                case 'B':
                    answerB.setBackgroundResource(R.color.cardAnswerError);
                    break;
                case 'C':
                    answerC.setBackgroundResource(R.color.cardAnswerError);
                    break;
                case 'D':
                    answerD.setBackgroundResource(R.color.cardAnswerError);
                    break;
            }
        }
        new CountDownTimer(0, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                setQuestion();
            }

        }.start();
        if (num == PlayGame.questionPlay.size()) {
            Intent intent = new Intent(PlayActivity.this, ResultActivity.class);
            intent.putExtra("correct", correct);
            startActivity(intent);
            finish();
        }
    }


    private void setColorAnswer(LinearLayout layout, String answer) {
        int colorFrom = getResources().getColor(R.color.cardAnswer);
        int colorTo = getResources().getColor(R.color.cardAnswerSelected);

        answerA.setBackgroundResource(R.color.cardAnswer);
        answerB.setBackgroundResource(R.color.cardAnswer);
        answerC.setBackgroundResource(R.color.cardAnswer);
        answerD.setBackgroundResource(R.color.cardAnswer);

        if (layout == null) {
            return;
        }

        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(250);
        colorAnimation.addUpdateListener(animator -> layout.setBackgroundColor((int) animator.getAnimatedValue()));
        colorAnimation.start();

        selectedAnswer = answer;
        btnNext.setEnabled(true);
        btnNext.setAlpha(1f);
    }

}