package com.example.activityassignment6;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListChatAdapter extends RecyclerView.Adapter<ListChatAdapter.ListChatHolder> {
    private ArrayList<Chat> list;
    public ListChatAdapter(ArrayList<Chat> listChat) {this.list = listChat; }

    @NonNull
    @Override
    public ListChatAdapter.ListChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_chat, parent, false);
        return new ListChatAdapter.ListChatHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListChatAdapter.ListChatHolder holder, int position) {
        Chat chat = list.get(position);
        holder.tvMessage.setText(chat.getMessage());
        holder.tvTime.setText(chat.getTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ListChatHolder extends RecyclerView.ViewHolder {
        TextView tvMessage, tvTime;
        public ListChatHolder(@NonNull View itemView) {
            super(itemView);
            tvMessage = itemView.findViewById(R.id.message);
            tvTime = itemView.findViewById(R.id.time);
        }
    }
}