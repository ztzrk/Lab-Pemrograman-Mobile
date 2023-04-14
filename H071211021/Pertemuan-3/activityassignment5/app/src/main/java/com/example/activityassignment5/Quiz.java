package com.example.activityassignment5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.Random;
import java.util.Stack;

public class Quiz extends AppCompatActivity implements View.OnClickListener {
    final public static String EXTRA_ARRAYSOAL = "extra-arraysoal";
    Button pilihan1, pilihan2, pilihan3, pilihan4;
    Soal[] arraysoal;
    Soal soal;
    TextView tvQuiz;
    ImageView ivPokemon;
    Stack<Soal> stackSoal;
    int score = 0;
    int currentQuestionIndex = 0;
    User user;
    Photo photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        pilihan1 = findViewById(R.id.pilihan1);
        pilihan1.setOnClickListener(this);
        pilihan2 = findViewById(R.id.pilihan2);
        pilihan2.setOnClickListener(this);
        pilihan3 = findViewById(R.id.pilihan3);
        pilihan3.setOnClickListener(this);
        pilihan4 = findViewById(R.id.pilihan4);
        pilihan4.setOnClickListener(this);

        tvQuiz = findViewById(R.id.quiz);
        ivPokemon = findViewById(R.id.pokemon);

        arraysoal = (Soal[]) getIntent().getSerializableExtra(EXTRA_ARRAYSOAL);
        stackSoal = makeStack();
        soal = stackSoal.pop();
        setSoal();
    }

    @Override
    public void onClick(View v) {
        String jawaban = ((Button) v).getText().toString();
        Button btnClicked = (Button) v;
        try {
            if (soal.getJawaban().equals(jawaban)) {
                btnClicked.setBackgroundColor(getResources().getColor(R.color.teal_200));
            }
            else {
                btnClicked.setBackgroundColor(getResources().getColor(R.color.purple_200));
            }
            checkJawaban(jawaban);
        } catch (EmptyStackException e) {
            System.out.println("halo");
            Intent intent = new Intent(Quiz.this, Score.class);
            user = getIntent().getParcelableExtra(Score.EXTRA_USER);
            photo = getIntent().getParcelableExtra(Score.EXTRA_PHOTO);
            intent.putExtra("score", score);
            intent.putExtra(Score.EXTRA_USER, user);
            intent.putExtra(Score.EXTRA_PHOTO, photo);
            startActivity(intent);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                currentQuestionIndex++;
                setSoal();
            }
        }, 1000);
    }

    public void checkJawaban(String jawaban) {
        boolean flag = soal.getJawaban().equals(jawaban);
        if (flag) {
            score += Integer.parseInt(soal.getNilai());
        }
        soal = stackSoal.pop();
    }

    public Stack<Soal> makeStack() {
        Collections.shuffle(Arrays.asList(arraysoal));
        arraysoal = Arrays.copyOfRange(arraysoal, 0, 5);
        Stack<Soal> stack = new Stack<>();
        for (int i = 0; i < arraysoal.length; i++) {
            stack.push(arraysoal[i]);
        }
        return stack;
    }

    public void setSoal() {
        tvQuiz.setText(String.format("Quiz %d of 5", currentQuestionIndex + 1));
        String[] pilihan = soal.getPilihan();
        pilihan1.setBackgroundColor(getResources().getColor(R.color.black));
        pilihan2.setBackgroundColor(getResources().getColor(R.color.black));
        pilihan3.setBackgroundColor(getResources().getColor(R.color.black));
        pilihan4.setBackgroundColor(getResources().getColor(R.color.black));
        pilihan1.setText(pilihan[0]);
        pilihan2.setText(pilihan[1]);
        pilihan3.setText(pilihan[2]);
        pilihan4.setText(pilihan[3]);
        String imageUrl = soal.getSoal();
        Picasso.get().load(imageUrl).into(ivPokemon);
    }
}