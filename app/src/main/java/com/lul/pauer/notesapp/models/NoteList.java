package com.lul.pauer.notesapp.models;

import android.util.Log;

import com.lul.pauer.notesapp.interfaces.ListContainer;

import java.util.ArrayList;
import java.util.List;

public class NoteList implements ListContainer {
    private final static NoteList ourInstance = new NoteList();

    private static List<Note> notes = new ArrayList<>();

    public final static NoteList getInstance() {
        return ourInstance;
    }

    private NoteList() {
    }

    @Override
    public final List<Note> getList() {
        return notes;
    }

    @Override
    public final void addAll(List list) {
        notes = list;
    }

    @Override
    public final void addOne(Object object) {
        try {
            notes.add((Note) object);
        } catch (Exception e) {
            e.getStackTrace();
            Log.i("error", "addOne: added non compatible object");
        }
    }

    /**
     * @param index  index of the element
     * @param flag   what will be added:
     *               1 - Title
     *               2 - content
     * @param object object to add, must be string
     */
    @Override
    public final void setOne(int index, int flag, Object object) {
        switch (flag) {
            case 1:
                notes.get(index).setTitle(object.toString());
                break;
            case 2:
                notes.get(index).setContent(object.toString());
                break;
            default:
                break;
        }


    }

    @Override
    public void removeAll() {
        notes = null;
    }

    @Override
    public void removeOne(int index) {
        notes.remove(index);
    }

    /**
     * @param index index of element
     * @param flag  what will be returned:
     *              1 - title
     *              2 - content
     * @return returns String
     */
    @Override
    public String getContent(int index, int flag) {
        switch (flag) {
            case 1:
                return notes.get(index).getTitle();
            case 2:
                return notes.get(index).getContent();
            default:
                return null;
        }
    }
}
