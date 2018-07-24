package com.lul.pauer.notesapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.CustomViewHolder> {
    private List<Note> notes;

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public CustomViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTextView);
        }
    }

    public NoteAdapter(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.relative_layout, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.title.setText(note.getTitle());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}
