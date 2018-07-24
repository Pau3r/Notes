package com.lul.pauer.notesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NoteAdapter noteAdapter;
    private NotesListSingleton notesListSingleton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notesListSingleton = NotesListSingleton.getInstance();


        recyclerView = findViewById(R.id.mainRecyclerView);
        noteAdapter = new NoteAdapter(notesListSingleton.getNotes());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(noteAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemListener(getApplicationContext(),
                recyclerView, new RecyclerItemListener.RecyclerTouchListener() {
            @Override
            public void onClickItem(View v, int position) {
                goToNext(position);
            }

            @Override
            public void onLongClickItem(View v, int position) {

            }
        }));

        noteAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                notesListSingleton.addNote(new Note("<blank>", ""));
                Toast.makeText(this, "added", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        noteAdapter.notifyDataSetChanged();
        return super.onOptionsItemSelected(item);
    }

    private void goToNext(int index) {
        Intent intent = new Intent(getApplicationContext(), EditNoteActivity.class);
       // intent.putExtra("list", notes);
        intent.putExtra("index", index);
        startActivityForResult(intent,1);//TODO change request code and check what is this
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            noteAdapter.notifyDataSetChanged();
        }
    }
}
