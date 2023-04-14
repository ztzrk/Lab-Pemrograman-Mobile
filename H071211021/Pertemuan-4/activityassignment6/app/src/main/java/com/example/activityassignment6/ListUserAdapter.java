package com.example.activityassignment6;

import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.BreakIterator;
import java.util.ArrayList;

public class ListUserAdapter extends RecyclerView.Adapter<ListUserAdapter.ListUserHolder> {
    private ArrayList<User> listUser;
    public ListUserAdapter(ArrayList<User> list) {this.listUser = list; }
    @NonNull
    @Override
    public ListUserAdapter.ListUserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_user, parent, false);
        return new ListUserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListUserAdapter.ListUserHolder holder, int position) {
        User user = listUser.get(position);
        Glide.with(holder.itemView.getContext())
                .load(user.getImageUri())
                .apply(new RequestOptions().override(60, 60))
                .into(holder.ivImage);
        holder.tvUsername.setText(user.getUsername());
        holder.tvLastChat.setText(user.getLastChat().getMessage());
        holder.tvTime.setText(user.getLastChat().getTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ChatActivity.class);
                Chat lastChat = user.getLastChat();
                intent.putExtra("lastChat", lastChat);
                intent.putExtra(ChatActivity.EXTRA_USER,  user);
                view.getContext().startActivity(intent);
            }
        });
        holder.ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PhotoActivity.class);
                intent.putExtra(ChatActivity.EXTRA_USER,  user);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public class ListUserHolder extends RecyclerView.ViewHolder {
        public ImageView ivImage;
        public TextView tvUsername, tvLastChat, tvTime;
        public ListUserHolder(@NonNull View itemView) {
            super(itemView);
            tvLastChat = itemView.findViewById(R.id.tvLastChat);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvTime = itemView.findViewById(R.id.tvTime);
            ivImage = itemView.findViewById(R.id.ivImage);
        }
    }
}
