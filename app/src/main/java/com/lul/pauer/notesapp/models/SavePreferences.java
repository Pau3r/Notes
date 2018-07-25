package com.lul.pauer.notesapp.models;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public  class SavePreferences {
    private SharedPreferences sharedPreferences;

    public SavePreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public final void saveNoteList() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        NoteList noteList = NoteList.getInstance();
        String json = gson.toJson(noteList.getList());
        editor.putString("noteList", json);
        editor.apply();
    }

    public final void readNoteList() {
        if (sharedPreferences.contains("noteList")) {
            Gson gson = new Gson();
            String json = sharedPreferences.getString("noteList", "");
            NoteList noteList = NoteList.getInstance();
            Type listType = new TypeToken<ArrayList<Note>>(){}.getType();
            List<Note> notesList = gson.fromJson(json,listType);
            noteList.addAll(notesList);
        }
    }


}
