package com.example.activityassigment10;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private ImageButton btnAdd;
    private RecyclerView rvNotes;

    private TextView noData;

    private SearchView searchView;

    private final ActivityResultLauncher<Intent> resultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getData() != null) {
                    System.out.println("RESULT CODE: " + result.getResultCode());
                    switch (result.getResultCode()) {
                        case FormActivity.RESULT_ADD -> {
                            showNotes();
                            Toast.makeText(this, "Note added successfully", Toast.LENGTH_SHORT).show();
                        }
                        case FormActivity.RESULT_UPDATE -> {
                            showNotes();
                            Toast.makeText(this, "Note updated successfully", Toast.LENGTH_SHORT).show();
                        }
                        case FormActivity.RESULT_DELETE -> {
                            showNotes();
                            Toast.makeText(this, "Note deleted successfully", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btn_add);
        noData = findViewById(R.id.tv_empty_notes);
        searchView = findViewById(R.id.sv_search);

        btnAdd.setOnClickListener(view -> {
            Intent toForm = new Intent(MainActivity.this, FormActivity.class);
            resultLauncher.launch(toForm);
        });

        rvNotes = findViewById(R.id.rv_notes);
        rvNotes.setLayoutManager(new LinearLayoutManager(this));

        showNotes();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) { return false; }
            @Override
            public boolean onQueryTextChange(String query) {
                if (query.isEmpty()) {
                    showNotes();
                    rvNotes.setVisibility(View.VISIBLE);
                } else {
                    new LoadNoteAsync(MainActivity.this, notes -> {
                        rvNotes.setVisibility(View.VISIBLE);
                        if (notes.size() > 0) {
                            setRecyclerView(notes);
                            noData.setVisibility(View.GONE);
                        } else {
                            noData.setVisibility(View.VISIBLE);
                            rvNotes.setVisibility(View.GONE);
                        }
                    }).searchExecute(query);
                }
                return true;
            }
        });
    }

    private void showNotes() {
        new LoadNoteAsync(this, notes -> {
            if (notes.size() > 0) {
                setRecyclerView(notes);
                noData.setVisibility(View.GONE);
                searchView.setVisibility(View.VISIBLE);
                rvNotes.setVisibility(View.VISIBLE);
            }
            else {
                rvNotes.setVisibility(View.GONE);
                noData.setVisibility(View.VISIBLE);
                searchView.setVisibility(View.GONE);
            }
        }).execute();
    }

    private void setRecyclerView(ArrayList<Note> notes) {
        NoteAdapter noteAdapter = new NoteAdapter(notes);
        noteAdapter.setOnItemClickCallback(note -> {
            Intent toForm = new Intent(MainActivity.this, FormActivity.class);
            toForm.putExtra(FormActivity.EXTRA_NOTE, note);
            resultLauncher.launch(toForm);
        });
        rvNotes.setAdapter(noteAdapter);
    }

    private static class LoadNoteAsync {

        private final WeakReference<Context> weakContext;
        private final WeakReference<LoadNoteCallback> weakCallback;

        private LoadNoteAsync(Context context, LoadNoteCallback callback) {
            weakContext = new WeakReference<>(context);
            weakCallback = new WeakReference<>(callback);
        }

        void execute() {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());

            executor.execute(() -> {
                Context context = weakContext.get();
                NoteHelper noteHelper = NoteHelper.getInstance(context);
                noteHelper.open();

                Cursor cursor = noteHelper.queryAll();
                ArrayList<Note> notes = MappingHelper.mapCursorToArrayList(cursor);
                noteHelper.close();

                handler.post(() -> weakCallback.get().postExecute(notes));
            });
        }

        void searchExecute(String query) {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());

            executor.execute(() -> {
                Context context = weakContext.get();
                NoteHelper noteHelper = NoteHelper.getInstance(context);
                noteHelper.open();

                Cursor cursor = noteHelper.querySearch(query);
                ArrayList<Note> notes = MappingHelper.mapCursorToArrayList(cursor);
                noteHelper.close();

                handler.post(() -> weakCallback.get().postExecute(notes));
            });
        }
    }

    interface LoadNoteCallback {
        void postExecute(ArrayList<Note> notes);
    }
}