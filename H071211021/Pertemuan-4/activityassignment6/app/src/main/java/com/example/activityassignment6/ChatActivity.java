package com.example.activityassignment6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    public static final String EXTRA_USER = "user";
    Toolbar toolbar;
    TextView tvUsername;
    ImageView ivProfile;
    RecyclerView rvChat;
    ImageView btnBack;
    private ArrayList<Chat> listChat = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        tvUsername = findViewById(R.id.username);
        ivProfile = findViewById(R.id.profile);
        toolbar = findViewById(R.id.appbar);
        btnBack = findViewById(R.id.back);

        User user = getIntent().getParcelableExtra(EXTRA_USER);
        tvUsername.setText(user.getUsername());
        Chat lastChat = getIntent().getParcelableExtra("lastChat");

        Glide.with(getApplicationContext())
                .load(user.getImageUri())
                .into(ivProfile);

        rvChat = findViewById(R.id.rvChat);
        rvChat.setHasFixedSize(true);
        listChat.addAll(ChatData.getChatData());
        listChat.add(lastChat);

        rvChat.setLayoutManager(new LinearLayoutManager(this));
        ListChatAdapter listChatAdapter = new ListChatAdapter(listChat);
        rvChat.setAdapter(listChatAdapter);

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}