package com.inredec.atutorn.presentationlayer.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.inredec.atutorn.R;
import com.inredec.atutorn.model.businesslayer.entities.Concept;
import com.inredec.atutorn.model.businesslayer.entities.Lesson;

import java.util.List;

public class MyConceptAdapter extends RecyclerView.Adapter<MyConceptAdapter.ViewHolder> {
    private List<Concept> dataList;
    private Context context;

    private MyConceptAdapter.OnConceptListener onConceptListener;

    public MyConceptAdapter(List<Concept> dataList , Context context){
        this.dataList = dataList;
        this.context = context;
        this.onConceptListener = (MyConceptAdapter.OnConceptListener)context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lesson,parent,false);
        return new MyConceptAdapter.ViewHolder(v, onConceptListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyConceptAdapter.ViewHolder viewHolder, int i) {

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

        MyConceptAdapter.OnConceptListener onConceptistener;

        public ViewHolder(View itemView, MyConceptAdapter.OnConceptListener onLessonListener) {
            super(itemView);
            //tvname=(TextView)itemView.findViewById(R.id.txt_name);
            //img=(ImageView)itemView.findViewById(R.id.image_view);


            tvname=(TextView)itemView.findViewById(R.id.tv_item_lesson_title);
            img=(ImageView)itemView.findViewById(R.id.iv_item_lesson);
            tvViewed=(TextView)itemView.findViewById(R.id.tv_item_lesson_viewed);
            tvDescription=(TextView)itemView.findViewById(R.id.tv_item_lesson_description);

            this.onConceptistener = onConceptListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onConceptistener.onConceptClick(getAdapterPosition());
        }
    }

    public interface OnConceptListener{
        void onConceptClick(int position);
    }
}

