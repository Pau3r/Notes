package com.lul.pauer.notesapp;

import java.util.ArrayList;
import java.util.List;

public class NotesListSingleton {
    private static final NotesListSingleton ourInstance = new NotesListSingleton();
    private static List<Note> notes = new ArrayList<>();
    public static NotesListSingleton getInstance() {
        return ourInstance;
    }

    private NotesListSingleton() {
    }

    public List<Note> getNotes() {
        return notes;
    }


    public void setAllNotes(List<Note> notes) {
        this.notes = notes;
    }

    public void setOneNoteTitle(int index, String title) {
        this.notes.get(index).setTitle(title);
    }

    public void setOneNoteContent(int index, String content) {
        this.notes.get(index).setContent(content);
    }

    public void addNote(Note note) {
        notes.add(note);
    }

    public String getOneNoteTitle(int index) {
        return notes.get(index).getTitle();
    }

    public String getOneNoteContent(int index) {
        return notes.get(index).getContent();
    }
}
