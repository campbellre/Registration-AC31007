package com.team07.signinapp;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

//Implementation of a RecyclerView to display each lesson in a timeline view
public class ScheduleAdapter extends RecyclerView.Adapter<LessonViewHolder>{
    private List<Lesson> lessons;

    public ScheduleAdapter(List<Lesson> lessons){
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
        lessonViewHolder.name.setText(lessons.get(i).name);
        lessonViewHolder.location.setText(lessons.get(i).location);
        lessonViewHolder.time.setText(lessons.get(i).time);
    }

    //Required
    @Override
    public int getItemCount() {
        return lessons.size();
    }
}