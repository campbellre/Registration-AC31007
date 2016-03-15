package com.team07.signinapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

// This is the class for each of the Cards that gets displayed
public class LessonViewHolder extends RecyclerView.ViewHolder {
    public Lesson lesson;
    public TextView name;
    public TextView location;
    public TextView time;
    public TextView type;

    public LessonViewHolder(View cardView) {
        super(cardView);
        name = (TextView)cardView.findViewById(R.id.LessonCardName);
        location = (TextView)cardView.findViewById(R.id.LessonCardLocation);
        time = (TextView)cardView.findViewById(R.id.LessonCardTime);
        type = (TextView)cardView.findViewById(R.id.LessonCardType);
    }
}