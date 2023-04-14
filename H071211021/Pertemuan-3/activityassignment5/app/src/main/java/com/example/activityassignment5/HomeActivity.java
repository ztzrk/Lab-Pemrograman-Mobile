package com.example.activityassignment5;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    ImageView ivProfile;
    EditText etName;
    Button btnApply;
    User user = new User();
    Photo photo = new Photo();
    ActivityResultLauncher<Intent> profilePhoto;
    Uri profileUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ivProfile = findViewById(R.id.profile);
        etName = findViewById(R.id.name);
        btnApply = findViewById(R.id.apply);

        profilePhoto =
                registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                        result -> {
                            if (result.getResultCode() == RESULT_OK) {
                                Intent data = result.getData();
                                assert data != null;
                                profileUrl = data.getData();
                                ivProfile.setImageURI(profileUrl);
                                photo.setPhotoUri(profileUrl);
                            }
                        });
        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                profilePhoto.launch(intent);
            }
        });

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setUsername(String.valueOf(etName.getText()));
                if (user.getUsername().isEmpty()) {
                    Toast.makeText(HomeActivity.this, "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "" +
                                    "Please pick a profile photo first",
                            Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra(Score.EXTRA_USER, user);
                    intent.putExtra(Score.EXTRA_PHOTO, photo);
                    startActivity(intent);
                }
            }
        });
    }
}