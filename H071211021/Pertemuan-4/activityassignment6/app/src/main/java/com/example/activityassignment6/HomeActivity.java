package com.example.activityassignment6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    RecyclerView rvUser;
    private ArrayList<User> listUser = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        rvUser = findViewById(R.id.rvUser);
        rvUser.setHasFixedSize(true);

        listUser.addAll(UserData.getUserData());
        rvUser.setLayoutManager(new LinearLayoutManager(this));
        ListUserAdapter listUserAdapter = new ListUserAdapter(listUser);
        rvUser.setAdapter(listUserAdapter);
    }
}