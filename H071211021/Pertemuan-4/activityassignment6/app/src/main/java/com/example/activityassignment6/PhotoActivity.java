package com.example.activityassignment6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class PhotoActivity extends AppCompatActivity {
    ImageView btnBack, ivProfile;
    TextView tvNama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        User user = getIntent().getParcelableExtra(ChatActivity.EXTRA_USER);

        btnBack = findViewById(R.id.back);
        ivProfile = findViewById(R.id.ivImage);
        tvNama = findViewById(R.id.tvNama);

        Glide.with(getApplicationContext())
                .load(user.getImageUri())
                .into(ivProfile);
        tvNama.setText(user.getUsername());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}