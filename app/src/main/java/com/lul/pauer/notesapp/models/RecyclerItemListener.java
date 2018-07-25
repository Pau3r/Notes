package com.lul.pauer.notesapp.models;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerItemListener
        implements RecyclerView.OnItemTouchListener {

    private RecyclerTouchListener listener;
    private GestureDetector gestureDetector;

    public interface RecyclerTouchListener {
        void onClickItem(View v, int position);

        void onLongClickItem(View v, int position);
    }

    public RecyclerItemListener(Context context, final RecyclerView recyclerView,
                                final RecyclerTouchListener recyclerTouchListener) {
        this.listener = recyclerTouchListener;
        gestureDetector = new GestureDetector(context,
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public void onLongPress(MotionEvent e) {
                        View view = recyclerView.findChildViewUnder(e.getX(), e.getY());
                        recyclerTouchListener.onLongClickItem(view, recyclerView.getChildAdapterPosition(view));
                    }

                    @Override
                    public boolean onSingleTapUp(MotionEvent e) {
                        View view = recyclerView.findChildViewUnder(e.getX(), e.getY());
                        recyclerTouchListener.onClickItem(view, recyclerView.getChildAdapterPosition(view));
                        return true;
                    }
                });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child = rv.findChildViewUnder(e.getX(), e.getY());
        return (child != null && gestureDetector.onTouchEvent(e));
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}