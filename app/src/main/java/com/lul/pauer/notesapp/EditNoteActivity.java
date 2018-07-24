package com.lul.pauer.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class EditNoteActivity extends AppCompatActivity {
    private NotesListSingleton notesListSingleton;
    private int indexInNotesList;
    private TextView textView;

    //TODO finish this activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        notesListSingleton = NotesListSingleton.getInstance();

        Bundle data = getIntent().getExtras();
        indexInNotesList = data.getInt("index");

        textView = findViewById(R.id.textView);

        textView.setText(notesListSingleton.getOneNoteTitle(indexInNotesList));
        notesListSingleton.setOneNoteTitle(indexInNotesList,":))):):):):)):):)");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void finish() {//TODO REQUEST CODE
        setResult(1);
        super.finish();
    }
}
