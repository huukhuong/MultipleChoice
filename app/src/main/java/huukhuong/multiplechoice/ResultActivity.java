package huukhuong.multiplechoice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView txtCorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        txtCorrect = findViewById(R.id.txtCorrect);
        Intent intent = getIntent();
        txtCorrect.setText(intent.getIntExtra("correct", 0) + "");

    }
}