package com.example.activityassigment10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormActivity extends AppCompatActivity {
    public static final int RESULT_ADD = 200;
    public static final String EXTRA_NOTE = "notes";
    public static final int RESULT_UPDATE = 201;
    public static final int RESULT_DELETE = 202;
    private EditText etTitle, etDescription;
    private Button btnSave, btnDelete;

    private NoteHelper noteHelper;

    private boolean isEdit = false;

    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        etTitle = findViewById(R.id.et_title);
        etDescription = findViewById(R.id.et_description);
        btnSave = findViewById(R.id.btn_save);
        btnDelete = findViewById(R.id.btn_delete);

        note = getIntent().getParcelableExtra(EXTRA_NOTE);
        if (note != null) {
            etTitle.setText(note.getTitle());
            etDescription.setText(note.getDescription());
            isEdit = true;
        }

        if (isEdit) {
            btnSave.setText("Update");
            btnDelete.setVisibility(View.VISIBLE);
        }

        noteHelper = NoteHelper.getInstance(getApplicationContext());
        noteHelper.open();

        btnSave.setOnClickListener(view -> save());
        btnDelete.setOnClickListener(view -> delete());
    }

    private void save() {
        String title = etTitle.getText().toString().trim();
        String description = etDescription.getText().toString().trim();

        if (title.isEmpty()) {
            etTitle.setError("Title must not be empty");
            return;
        }
        if(description.isEmpty()) {
            etDescription.setError("Description must not be empty");
            return;
        }

        long currentTimeMillis = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String createdAt = dateFormat.format(new Date(currentTimeMillis));

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseContract.NoteColumns.TITLE, title);
        contentValues.put(DatabaseContract.NoteColumns.DESCRIPTION, description);
        contentValues.put(DatabaseContract.NoteColumns.CREATED_AT, createdAt);

        if (isEdit) {
            contentValues.put(DatabaseContract.NoteColumns.IS_EDITED, 1);
            long result = noteHelper.update(String.valueOf(note.getId()), contentValues);
            if (result > 0) {
                setResult(RESULT_UPDATE, getIntent());
                finish();
            } else {
                Toast.makeText(this, "Failed to save data", Toast.LENGTH_SHORT).show();
            }
        } else {
            long result = noteHelper.insert(contentValues);
            if (result > 0) {
                setResult(RESULT_ADD, getIntent());
                finish();
            } else {
                Toast.makeText(this, "Failed to save data", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void delete() {
        long result = noteHelper.delete(String.valueOf(note.getId()));
        if (result > 0) {
            setResult(RESULT_DELETE, getIntent());
            finish();
        } else {
            Toast.makeText(this, "Failed to delete data", Toast.LENGTH_SHORT).show();
        }
    }

}