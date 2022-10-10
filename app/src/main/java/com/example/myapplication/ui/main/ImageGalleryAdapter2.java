package com.example.myapplication.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.room.User;

import java.util.Collections;
import java.util.List;

public class ImageGalleryAdapter2 extends RecyclerView.Adapter<examViewHolder> {

    List<User> list
            = Collections.emptyList();

    Context context;
    ClickListiner listiner;

    public ImageGalleryAdapter2(List<User> list,
                                Context context, ClickListiner listiner) {
        this.list = list;
        this.context = context;
        this.listiner = listiner;
    }

    @Override
    public examViewHolder
    onCreateViewHolder(ViewGroup parent,
                       int viewType) {

        Context context
                = parent.getContext();
        LayoutInflater inflater
                = LayoutInflater.from(context);

        // Inflate the layout

        View photoView
                = inflater
                .inflate(R.layout.card_user,
                        parent, false);

        examViewHolder viewHolder
                = new examViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void
    onBindViewHolder(final examViewHolder viewHolder,
                     final int position) {
        final int index = viewHolder.getAdapterPosition();
        viewHolder.examName
                .setText("name :" +list.get(position).getFirstName());
        Glide.with(context).load(list.get(position).getPictureMedium()).into( viewHolder.imageView );
        viewHolder.examDate
                .setText("city :"+list.get(position).getCity());
        viewHolder.examMessage
                .setText(list.get(position).getState());
        viewHolder.view.setOnClickListener(view -> listiner.click(index));

        viewHolder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.accept.setText("Accepted!!");
            }
        });
        viewHolder.decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.decline.setText("Declined!!");
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(
            RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}