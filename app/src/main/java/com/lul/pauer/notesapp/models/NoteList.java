package com.lul.pauer.notesapp.models;

import android.util.Log;

import com.lul.pauer.notesapp.interfaces.ListContainer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NoteList implements ListContainer, Serializable {
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

    @SuppressWarnings("unchecked")
    @Override
    public final void addAll(List list) {
        try {

            notes = (ArrayList<Note>) list;
        }catch (Exception e)
        {
            e.printStackTrace();
        }

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
    public final void removeAll() {
        for (Iterator iterator = notes.listIterator(); iterator.hasNext(); ) {
            iterator.remove();
        }
    }

    @Override
    public final void removeOne(int index) {
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
    public final String getContent(int index, int flag) {
        switch (flag) {
            case 1:
                return notes.get(index).getTitle();
            case 2:
                return notes.get(index).getContent();
            default:
                return null;
        }
    }

    @Override
    public int getLastIndex() {
        return notes.size() - 1;
    }
}
