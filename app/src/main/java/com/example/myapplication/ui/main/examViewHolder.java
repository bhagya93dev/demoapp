package com.example.myapplication.ui.main;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class examViewHolder  extends RecyclerView.ViewHolder {
    TextView examName;
    TextView examMessage;
    TextView examDate;
    View view;
    ImageView imageView;
    Button accept,decline;

    examViewHolder(View itemView)
    {
        super(itemView);
        examName
                = (TextView)itemView
                .findViewById(R.id.examName);
        examDate
                = (TextView)itemView
                .findViewById(R.id.examDate);
        examMessage
                = (TextView)itemView
                .findViewById(R.id.examMessage);
        imageView
                = itemView.findViewById(R.id.examPic);
        accept = itemView.findViewById(R.id.accept);
        decline = itemView.findViewById(R.id.decline);
        view  = itemView;
    }
}
