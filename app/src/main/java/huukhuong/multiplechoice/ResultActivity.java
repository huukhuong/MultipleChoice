package huukhuong.multiplechoice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import huukhuong.multiplechoice.util.PlayGame;

public class ResultActivity extends AppCompatActivity {

    private TextView txvScore;
    private TextView txvCorrectQty;
    private TextView txvPercent;
    private TextView txvTotalQty;
    private TextView txvWrongQty;
    private ImageButton btnHome;
    private ImageButton btnShowResult;
    private ImageButton btnRanking;
    private Intent intent;
    private int correctQty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


//        addControls();
//        addEvents();
    }

    private void addControls() {
        intent = getIntent();
        txvScore = findViewById(R.id.txvScore);
        txvCorrectQty = findViewById(R.id.txvCorrectQty);
        txvPercent = findViewById(R.id.txvPercent);
        txvTotalQty = findViewById(R.id.txvTotalQty);
        txvWrongQty = findViewById(R.id.txvWrongQty);
        btnHome = findViewById(R.id.btnHome);
        btnShowResult = findViewById(R.id.btnShowResult);
        btnRanking = findViewById(R.id.btnRanking);

        correctQty = intent.getIntExtra("correct", 0);
        int total = PlayGame.questionPlay.size();
        float score = (float) 10 / total * correctQty;
        float percent = (float) (correctQty * 1.0 / total * 100);
        int wrong = total - correctQty;

        txvScore.setText(score + "");
        txvPercent.setText((int) percent + "%");
        txvTotalQty.setText(total + "");
        txvCorrectQty.setText(correctQty + "");
        txvWrongQty.setText(wrong + "");
    }

    private void addEvents() {
        btnHome.setOnClickListener(v -> startActivity(new Intent(ResultActivity.this, MainActivity.class)));

    }
}