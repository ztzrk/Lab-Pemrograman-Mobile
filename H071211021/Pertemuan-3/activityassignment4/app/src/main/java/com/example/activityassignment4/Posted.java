package com.example.activityassignment4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Posted extends AppCompatActivity {

    final public static String EXTRA_IMAGE = "extra-image";
    final public static String EXTRA_CAPTION = "extra-caption";
    final public static String EXTRA_FULLNAME = "extra-fullname";
    final public static String EXTRA_USERNAME = "extra-username";
    final public static String EXTRA_PROFILE = "extra-profile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posted);

        ImageView imagePhoto = findViewById(R.id.image);
        TextView fullname = findViewById(R.id.fullname);
        TextView username = findViewById(R.id.username);
        TextView caption = findViewById(R.id.caption);
        ImageView profilePhoto = findViewById(R.id.profile);

        fullname.setText(getIntent().getStringExtra(EXTRA_FULLNAME));
        username.setText(getIntent().getStringExtra(EXTRA_USERNAME));
        caption.setText(getIntent().getStringExtra(EXTRA_CAPTION));
        imagePhoto.setImageURI(getIntent().getParcelableExtra(EXTRA_IMAGE));
        profilePhoto.setImageURI(getIntent().getParcelableExtra(EXTRA_PROFILE));
    }
}