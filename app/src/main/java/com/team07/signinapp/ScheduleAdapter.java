package com.team07.signinapp;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lewispalmer on 24/02/2016.
 */

//Implementation of a recyclerview to display each lesson in a timeline view
public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.LessonViewHolder>{

    private List<Lesson> lessons;

    //This is the class for each of the Cards that gets displayed
    public static class LessonViewHolder extends RecyclerView.ViewHolder {
        CardView lessonCard;
        static TextView Name;
        static TextView Location;
        static TextView Time;

        LessonViewHolder(View cardView) {
            super(cardView);
            lessonCard = (CardView)cardView.findViewById(R.id.LessonCardView);
            Name = (TextView)cardView.findViewById(R.id.LessonName);
            Location = (TextView)cardView.findViewById(R.id.LessonLocation);
            Time = (TextView)cardView.findViewById(R.id.LessonTime);
        }
    }

    ScheduleAdapter(List<Lesson> lessons){
        this.lessons = lessons;
    }

    //Fetches the layout that each of the views in the Scheduleview will be styled from
    //This will be set in layout->lesson_card_view.xml
    @Override
    public LessonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lesson_card_view, viewGroup, false);
        return new LessonViewHolder(v);
    }

    //override method to set variables on each LessonCard
    @Override
    public void onBindViewHolder(LessonViewHolder lessonViewHolder, int i) {
        LessonViewHolder.Name.setText(lessons.get(i).name);
        LessonViewHolder.Location.setText(lessons.get(i).location);
        LessonViewHolder.Time.setText(lessons.get(i).time);
    }

    //Required
    @Override
    public int getItemCount() {
        return lessons.size();
    }

}