package com.rmanrique.notes.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rmanrique.notes.Models.Note;
import com.rmanrique.notes.NoteCreateActivity;
import com.rmanrique.notes.R;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder>{
    List<Note> notesList;
    public Context context;
    public NoteAdapter(List<Note> notes) {
        this.notesList = notes;
    }

    @Override
    public int getItemCount() {return notesList.size();}

    @Override
    public NoteViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_card,parent,false);
        NoteViewHolder noteViewHolder = new NoteViewHolder(view);
        return noteViewHolder;
    }
    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        Note note = notesList.get(position);
        holder._txvTitle.setText(note.getTitle());
        holder._txvAuthor.setText(note.getAuthor());
        holder._txvNote.setText(note.getText());
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    public class NoteViewHolder extends RecyclerView.ViewHolder{
        TextView _txvTitle,_txvAuthor,_txvNote;
        public NoteViewHolder (final View itemView) {
            super(itemView);
            _txvTitle = itemView.findViewById(R.id.txvTitle);
            _txvAuthor = itemView.findViewById(R.id.txvAuthor);
            _txvNote = itemView.findViewById(R.id.txvNote);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    try{
                        int position = getAdapterPosition();
                        Note note_card;
                        note_card = notesList.get(position);
                        Intent intent = new Intent(itemView.getContext(),NoteCreateActivity.class);
                        intent.putExtra("id",note_card.getId());
                        intent.putExtra("noteText",note_card.getText());
                        intent.putExtra("author",note_card.getAuthor());
                        intent.putExtra("title",note_card.getTitle());
                        itemView.getContext().startActivity(intent);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
