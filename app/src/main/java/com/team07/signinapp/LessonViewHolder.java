package com.team07.signinapp;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Date;

// This is the class for each of the Cards that gets displayed
public class LessonViewHolder extends RecyclerView.ViewHolder {
    public Lesson lesson;
    public TextView name;
    public TextView location;
    public TextView time;
    public TextView type;
    public View cardView;

    public LessonViewHolder(View cardView) {
        super(cardView);
        this.cardView = cardView;
        name = (TextView)cardView.findViewById(R.id.LessonCardName);
        location = (TextView)cardView.findViewById(R.id.LessonCardLocation);
        time = (TextView)cardView.findViewById(R.id.LessonCardTime);
        type = (TextView)cardView.findViewById(R.id.LessonCardType);
    }

    public void setBackgroundColour(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        Context cardViewContext = itemView.getContext();
        int colourId;
        switch (day) {
            case Calendar.MONDAY:
                colourId = R.color.mondayColour;
                break;
            case Calendar.TUESDAY:
                colourId = R.color.tuesdayColour;
                break;
            case Calendar.WEDNESDAY:
                colourId = R.color.wednesdayColour;
                break;
            case Calendar.THURSDAY:
                colourId = R.color.thursdayColour;
                break;
            case Calendar.FRIDAY:
                colourId = R.color.fridayColour;
                break;
            case Calendar.SATURDAY:
                colourId = R.color.saturdayColour;
                break;
            case Calendar.SUNDAY:
                colourId = R.color.sundayColour;
                break;
            default:
                colourId = R.color.errorColour;
                break;
        }
        cardView.findViewById(R.id.cardBackgroundId).setBackgroundColor(ContextCompat.getColor(cardViewContext, colourId));
    }
}