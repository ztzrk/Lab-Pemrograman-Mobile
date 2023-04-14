package com.example.activityassignment5;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    ImageView ivProfile;
    TextView tvName, tvBestScore;
    Button btnPlay;
    ActivityResultLauncher<Intent> profilePhoto;
    Uri profileUrl;
    User user;
    Photo photo;

    String json;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivProfile = findViewById(R.id.profile);
        tvName = findViewById(R.id.name);
        tvBestScore = findViewById(R.id.bestScore);
        btnPlay = findViewById(R.id.play);
        user = getIntent().getParcelableExtra(Score.EXTRA_USER);
        photo = getIntent().getParcelableExtra(Score.EXTRA_PHOTO);
        Picasso.get().load(photo.getPhotoUri()).into(ivProfile);

        if(user.getBest_score() == 0){
            Picasso.get().load(photo.getPhotoUri()).into(ivProfile);
            tvName.setText(user.getUsername());
            tvBestScore.setText("Best Score: 0");
        } else {
            tvName.setText(user.getUsername());
            tvBestScore.setText("Best Score: " + String.valueOf(user.getBest_score()));
            btnPlay.setText("PLAY AGAIN?");
        }

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

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Soal[] array_soal = MakeSoal();
                Intent play = new Intent(MainActivity.this, Quiz.class);
                play.putExtra(Score.EXTRA_USER, user);
                play.putExtra(Score.EXTRA_PHOTO, photo);
                play.putExtra(Quiz.EXTRA_ARRAYSOAL, array_soal);
                startActivity(play);
            }
        });
    }
    public Soal[] MakeSoal() {
        try {
            // Get the JSON file from the assets folder
            AssetManager assetManager = getAssets();
            InputStream inputStream = assetManager.open("soal.json");

            // Read the JSON data from the file
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            json = stringBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        JsonArray jsonArray = jsonObject.getAsJsonArray("dataSoal");

        Soal[] array_soal = gson.fromJson(jsonArray, Soal[].class);
        return  array_soal;
    }
}
