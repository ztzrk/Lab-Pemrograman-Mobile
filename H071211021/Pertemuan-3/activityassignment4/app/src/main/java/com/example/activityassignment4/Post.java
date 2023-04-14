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

import com.google.android.material.textfield.TextInputEditText;

public class Post extends AppCompatActivity {
    ActivityResultLauncher<Intent> imagePhoto;
    ImageView image;
    Button upload;
    TextInputEditText caption;
    Uri imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        image = findViewById(R.id.image);
        upload = findViewById(R.id.upload);
        caption = findViewById(R.id.caption);

        imagePhoto = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        assert data != null;
                        imageUrl = data.getData();
                        image.setImageURI(data.getData());
                    }
                });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                imagePhoto.launch(intent);
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stCaption = caption.getText().toString();
                String fullname = getIntent().getStringExtra(Posted.EXTRA_FULLNAME);
                String username = getIntent().getStringExtra(Posted.EXTRA_USERNAME);
                Uri profileUrl = getIntent().getParcelableExtra(Posted.EXTRA_PROFILE);

                if(imageUrl != null) {
                    Intent intent = new Intent(Post.this, Posted.class);
                    intent.putExtra(Posted.EXTRA_CAPTION, stCaption);
                    intent.putExtra(Posted.EXTRA_FULLNAME, fullname);
                    intent.putExtra(Posted.EXTRA_USERNAME, username);
                    intent.putExtra(Posted.EXTRA_IMAGE, imageUrl);
                    intent.putExtra(Posted.EXTRA_PROFILE, profileUrl);
                    startActivity(intent);
                }
            }
        });
    }
}