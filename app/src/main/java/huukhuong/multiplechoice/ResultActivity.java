package huukhuong.multiplechoice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView txvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

//        Intent intent = getIntent();
//        int correct = intent.getIntExtra("correct", 0);
//        txvResult = findViewById(R.id.txvCorrect);
//        txvResult.setText(correct + "/20");
    }
}