package com.lul.pauer.notesapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class NotesList implements Parcelable {
    private List<Note> notes;

    public NotesList() {
        this.notes = new ArrayList<>();
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


    protected NotesList(Parcel in) {
        if (in.readByte() == 0x01) {
            notes = new ArrayList<>();
            in.readList(notes, Note.class.getClassLoader());
        } else {
            notes = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (notes == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(notes);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<NotesList> CREATOR = new Parcelable.Creator<NotesList>() {
        @Override
        public NotesList createFromParcel(Parcel in) {
            return new NotesList(in);
        }

        @Override
        public NotesList[] newArray(int size) {
            return new NotesList[size];
        }
    };
}
