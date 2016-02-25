package com.team07.signinapp;

import java.util.List;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

// Implementation of a RecyclerView to display each lesson in a timeline view
public class ScheduleAdapter extends RecyclerView.Adapter<LessonViewHolder>{
    private List<Lesson> lessons;

    public ScheduleLessonHandler handler;

    public ScheduleAdapter(List<Lesson> lessons, ScheduleLessonHandler handler){
        this.lessons = lessons;
        this.handler = handler;
    }

    // Fetches the layout that each of the views in the ScheduleView will be styled from
    // This will be set in layout->lesson_card_view.xml
    @Override
    public LessonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lesson_card_view, viewGroup, false);
        return new LessonViewHolder(v);
    }

    // Override method to set variables on each LessonCard
    @Override
    public void onBindViewHolder(final LessonViewHolder lessonViewHolder, final int i) {
        lessonViewHolder.lesson = lessons.get(i);
        lessonViewHolder.name.setText(lessons.get(i).name);
        lessonViewHolder.location.setText(lessons.get(i).location);
        lessonViewHolder.time.setText(lessons.get(i).time);
        
        lessonViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
            handler.handleLesson(i,  lessonViewHolder.lesson);
            }
        });
    }

    // Required
    @Override
    public int getItemCount() {
        return lessons.size();
    }

    public interface ScheduleLessonHandler {
        void handleLesson(int i, Lesson lesson);
    }
}