package com.example.activityassignment5;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Score extends AppCompatActivity {
    public static String EXTRA_USER = "extra-user";
    public static String EXTRA_PHOTO = "extra-photo";
    TextView tvNilai, tvBestscore, tvGgwp;
    Button back;
    User user;
    Photo photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        int Score = getIntent().getIntExtra("score", 0);
        user = getIntent().getParcelableExtra(EXTRA_USER);
        photo = getIntent().getParcelableExtra(EXTRA_PHOTO);

        tvNilai = findViewById(R.id.nilai);
        tvGgwp = findViewById(R.id.ggwp);
        tvBestscore = findViewById(R.id.bestScore);
        back = findViewById(R.id.back);
        if (Score>user.getBest_score()){
            user.setBest_score(Score);
        }
        tvGgwp.setText("GGWP " + user.getUsername());
        tvBestscore.setText(String.valueOf(user.getBest_score()));
        tvNilai.setText(String.valueOf(Score));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Score.this, MainActivity.class);
                intent.putExtra(EXTRA_USER, user);
                intent.putExtra(EXTRA_PHOTO, photo);
                startActivity(intent);
            }
        });
    }
}