package com.inredec.atutorn.presentationlayer.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inredec.atutorn.R;
import com.inredec.atutorn.model.businesslayer.entities.Lesson;

import java.util.List;

public class MyLessonAdapter extends RecyclerView.Adapter<MyLessonAdapter.CustomViewHolder>{

    private List<Lesson> dataList;

    public MyLessonAdapter(List<Lesson> dataList){
        this.dataList = dataList;
    }

    public class CustomViewHolder  extends RecyclerView.ViewHolder{
        public final View myView;

        TextView textLesson;

        CustomViewHolder(View itemView) {
            super(itemView);
            myView = itemView;

            textLesson = myView.findViewById(R.id.lesson);

        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_lesson_layout, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyLessonAdapter.CustomViewHolder holder, int position) {
        holder.textLesson.setText(dataList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


}
