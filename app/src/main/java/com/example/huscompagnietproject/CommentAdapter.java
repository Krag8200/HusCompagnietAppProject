package com.example.huscompagnietproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CommentAdapter extends  RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    // Inflate layout for RecyclerView
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.comment_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.email.setText(comments.get(position).getEmail());
        holder.comment.setText(comments.get(position).getUserComment());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView email;
        private final EditText comment;

        ViewHolder(View itemView) {
            super(itemView);
            email = itemView.findViewById(R.id.comment_user);
            comment = itemView.findViewById(R.id.comment);

        }
    }

    private ArrayList<Comment> comments;

    public CommentAdapter() {

    }

    CommentAdapter(ArrayList<Comment> comments) {this.comments = comments; }
}


