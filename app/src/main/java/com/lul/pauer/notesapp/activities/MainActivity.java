package com.lul.pauer.notesapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.lul.pauer.notesapp.models.Note;
import com.lul.pauer.notesapp.adapters.NoteAdapter;
import com.lul.pauer.notesapp.models.NoteList;
import com.lul.pauer.notesapp.R;
import com.lul.pauer.notesapp.models.RecyclerItemListener;
import com.lul.pauer.notesapp.models.SavePreferences;
import com.lul.pauer.notesapp.services.AppKillService;


public class MainActivity extends AppCompatActivity {
    private NoteAdapter noteAdapter;
    private NoteList noteList;
    SharedPreferences sharedPreferences;
    SavePreferences savePreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("com.lul.pauer.notesapp", MODE_PRIVATE);
        savePreferences = new SavePreferences(sharedPreferences);
        savePreferences.readNoteList();
        noteList = NoteList.getInstance();
        Intent appKillService = new Intent(this, AppKillService.class);
        startService(appKillService);

        RecyclerView recyclerView = findViewById(R.id.mainRecyclerView);
        recyclerView.setHasFixedSize(true);
        noteAdapter = new NoteAdapter(noteList.getList());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(noteAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemListener(getApplicationContext(),
                recyclerView, new RecyclerItemListener.RecyclerTouchListener() {
            @Override
            public void onClickItem(View v, int position) {
                goToNext(position);
                //savePreferences.saveNoteList();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                noteList.addOne(new Note(getResources().getString(R.string.noName), ""));
                Toast.makeText(this, R.string.added, Toast.LENGTH_SHORT).show();
                //savePreferences.saveNoteList();
                break;
            default:
                break;
        }
        noteAdapter.notifyDataSetChanged();
        return super.onOptionsItemSelected(item);
    }

    private void goToNext(int index) {
        Intent intent = new Intent(getApplicationContext(), EditNoteActivity.class);
        intent.putExtra("index", index);
        startActivityForResult(intent, 1);//TODO change request code and check what is this
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            noteAdapter.notifyDataSetChanged();
        }
    }

}
