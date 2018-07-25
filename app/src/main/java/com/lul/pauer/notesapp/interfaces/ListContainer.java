package com.lul.pauer.notesapp.interfaces;

import com.lul.pauer.notesapp.models.Note;

import java.util.List;

public interface ListContainer {
    /**
     *
     * @return list
     */
    List getList();

    /**
     * Adds entire list
     * @param list list to set
     */
    void addAll(List list);

    /**
     * Adds one object to the list
     * @param object object to add
     */
    void addOne(Object object);

    /**
     * Sets one element
     * @param index index of the element
     * @param flag what will be added
     * @param object object to add
     */
    void setOne(int index,int flag, Object object);

    /**
     * Removes entire list
     */
    void removeAll();

    /**
     * Removes one with specified index
     * @param index index of element to remove
     */
    void removeOne(int index);

    /**
     * Gets content of one element
     * @param index index of element
     * @param flag what will be returned
     * @return content specified in flag
     */
    Object getContent(int index, int flag);




}
