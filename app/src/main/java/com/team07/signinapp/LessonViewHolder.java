package com.team07.signinapp;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

// This is the class for each of the Cards that gets displayed
public class LessonViewHolder extends RecyclerView.ViewHolder {
    private CardView lessonCard;
    public Lesson lesson;
    public TextView name;
    public TextView location;
    public TextView time;

    public LessonViewHolder(View cardView) {
        super(cardView);
        lessonCard = (CardView)cardView.findViewById(R.id.LessonCardView);
        name = (TextView)cardView.findViewById(R.id.LessonName);
        location = (TextView)cardView.findViewById(R.id.LessonLocation);
        time = (TextView)cardView.findViewById(R.id.LessonTime);
    }
}