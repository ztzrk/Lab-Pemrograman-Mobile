package com.example.activityassignment4;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    Button submit;
    ImageView profile;
    TextInputEditText fullname, username;
    ActivityResultLauncher<Intent> profilePhoto;
    Uri profileUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profile = findViewById(R.id.profile);
        fullname = findViewById(R.id.fullname);
        username = findViewById(R.id.username);
        submit = findViewById(R.id.submit);

        profilePhoto =
                registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        assert data != null;
                        profileUrl = data.getData();
                        profile.setImageURI(profileUrl);
                    }
                });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                profilePhoto.launch(intent);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (profileUrl != null && checkInput()) {
                    Intent intent = new Intent(getApplicationContext(), Post.class);

                    intent.putExtra(Posted.EXTRA_FULLNAME, fullname.getText().toString());
                    intent.putExtra(Posted.EXTRA_USERNAME, username.getText().toString());
                    intent.putExtra(Posted.EXTRA_PROFILE, profileUrl);

                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Please pick a profile photo first",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public boolean checkInput() {
        String stringFullname = String.valueOf(fullname.getText());
        String stringUsername = String.valueOf(username.getText());

        if (stringFullname.isEmpty() || stringUsername.isEmpty()) {
            if (stringFullname.isEmpty()) fullname.setError("Field can't be empty");
            if (stringUsername.isEmpty()) username.setError("Field can't be empty");
            return false;
        }
        return true;
    }
}