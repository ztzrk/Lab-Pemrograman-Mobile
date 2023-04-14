package com.example.activityassignment6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ProfileActivity extends AppCompatActivity {
    ImageView ivProfile, btnBack;
    TextView tvNama, tvPhoneNumber, tvStatus, tvStatusDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ivProfile = findViewById(R.id.ivProfilePf);
        tvNama = findViewById(R.id.tvNamaPf);
        tvStatus = findViewById(R.id.status);
        tvStatusDate = findViewById(R.id.statusDate);
        tvPhoneNumber =findViewById(R.id.tvPhonePf);
        btnBack = findViewById(R.id.back);

        User user = getIntent().getParcelableExtra(ChatActivity.EXTRA_USER);
        tvNama.setText(user.getUsername());
        Glide.with(getApplicationContext())
                .load(user.getImageUri())
                .into(ivProfile);
        tvStatus.setText(user.getStatus());
        tvStatusDate.setText(user.getStatusDate());
        tvPhoneNumber.setText(user.getPhoneNumber());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PhotoActivity.class);
                intent.putExtra(ChatActivity.EXTRA_USER, user);
                startActivity(intent);
            }
        });
    }
}