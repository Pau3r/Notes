package com.lul.pauer.notesapp.activities;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.lul.pauer.notesapp.models.NoteList;
import com.lul.pauer.notesapp.R;


public class EditNoteActivity extends AppCompatActivity {
    private NoteList noteList;
    private int indexInNotesList;
    private TextInputEditText textInputEditTextTitle;
    private TextInputEditText textInputEditTextContent;


    //TODO add delete button, best in menu. Cleanup code
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        noteList = NoteList.getInstance();
        textInputEditTextTitle = findViewById(R.id.titleEditText);
        textInputEditTextContent = findViewById(R.id.contentEditText);
        Bundle data = getIntent().getExtras();
        indexInNotesList = data.getInt("index");
        textInputEditTextTitle.setText(noteList.getContent(indexInNotesList, 1));
        if (textInputEditTextTitle.getText().toString()
                .equals(getResources().getString(R.string.noName))) {
            textInputEditTextTitle.setText("");
        }
        textInputEditTextContent.setText(noteList.getContent(indexInNotesList, 2));

        textWatchers();

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
                finish();
                break;
            case R.id.delete:
                noteList.removeOne(indexInNotesList);
                setResult(1);
                super.finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void finish() {//TODO REQUEST CODE

        if (!textInputEditTextTitle.getText().toString().equals("")) {
            noteList.setOne(indexInNotesList, 1, textInputEditTextTitle.getText().toString());
        } else {
            noteList.setOne(indexInNotesList, 1, getResources().getString(R.string.noName));
        }

        noteList.setOne(indexInNotesList, 2, textInputEditTextContent.getText().toString());

        super.finish();
    }

    void textWatchers() {
        textInputEditTextTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!textInputEditTextTitle.getText().toString().equals("")) {
                    noteList.setOne(indexInNotesList, 1, textInputEditTextTitle.getText().toString());
                } else {
                    noteList.setOne(indexInNotesList, 1, getResources().getString(R.string.noName));
                }
            }
        });

        textInputEditTextContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                noteList.setOne(indexInNotesList, 2, textInputEditTextContent.getText().toString());
            }
        });
    }

}
