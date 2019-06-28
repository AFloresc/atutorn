package com.inredec.atutorn.presentationlayer.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.inredec.atutorn.R;
import com.inredec.atutorn.model.businesslayer.entities.Concept;
import com.inredec.atutorn.model.businesslayer.entities.Lesson;

import java.util.ArrayList;
import java.util.List;

public class MyLessonAdapter extends RecyclerView.Adapter<MyLessonAdapter.ViewHolder>{

    private List<Lesson> dataList;
    private Context context;

    private OnLessonListener onLessonListener;

    public MyLessonAdapter(List<Lesson> dataList , Context context){
        this.dataList = dataList;
        this.context = context;
        this.onLessonListener = (OnLessonListener)context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        //View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.lesson_layout,parent,false);
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lesson,parent,false);
        return new ViewHolder(v, onLessonListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Lesson lesson = dataList.get(position);
        Log.d("ONRESPONSE" ,"Lesson Title: "+ lesson.getTitle());
        viewHolder.tvname.setText(lesson.getTitle());
        viewHolder.tvDescription.setText(lesson.getText());
        viewHolder.tvViewed.setTextColor(Color.rgb(0, 255, 0));
        Glide.with(this.context)
                .load(lesson.getImage())
                .centerCrop()
                .override(50, 50)
                .into(viewHolder.img);
    }

    @Override
    public int getItemCount() {

        return dataList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvname;
        private ImageView img;

        private TextView tvDescription;
        private TextView tvViewed;

        OnLessonListener onLessonListener;

        public ViewHolder(View itemView, OnLessonListener onLessonListener) {
            super(itemView);
            //tvname=(TextView)itemView.findViewById(R.id.txt_name);
            //img=(ImageView)itemView.findViewById(R.id.image_view);

            tvname=(TextView)itemView.findViewById(R.id.tv_item_lesson_title);
            img=(ImageView)itemView.findViewById(R.id.iv_item_lesson);
            tvViewed=(TextView)itemView.findViewById(R.id.tv_item_lesson_viewed);
            tvDescription=(TextView)itemView.findViewById(R.id.tv_item_lesson_description);

            this.onLessonListener = onLessonListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onLessonListener.onLessonClick(getAdapterPosition());
        }
    }

    public interface OnLessonListener{
        void onLessonClick(int position);
    }

    public void filerList(ArrayList<Lesson> filteredList){
        dataList = filteredList;
        notifyDataSetChanged();
    }
/*
    public class CustomViewHolder  extends RecyclerView.ViewHolder{
        public final View myView;

        TextView lessonTitle;

        CustomViewHolder(View itemView) {
            super(itemView);
            myView = itemView;

            //textLesson = myView.findViewById(R.id.lesson);
            lessonTitle = (TextView) myView.findViewById(R.id.txt_name);

        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.lesson_layout, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyLessonAdapter.CustomViewHolder holder, int position) {
        Lesson lesson = dataList.get(position);
        Log.d("ONRESPONSE" ,"Lesson Title: "+ lesson.getTitle());
        holder.lessonTitle.setText(lesson.getTitle());
        //holder.textLesson.setText(dataList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
*/

}
