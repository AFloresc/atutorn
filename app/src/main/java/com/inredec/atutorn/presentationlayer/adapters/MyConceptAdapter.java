package com.inredec.atutorn.presentationlayer.adapters;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.inredec.atutorn.R;
import com.inredec.atutorn.model.businesslayer.entities.Concept;
import com.inredec.atutorn.model.businesslayer.entities.Lesson;

import java.util.ArrayList;
import java.util.List;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class MyConceptAdapter extends RecyclerView.Adapter<MyConceptAdapter.ViewHolder> {
    private static final String TAG = "MyConceptAdapter";
    private static final String CONCEPTS_IMAGE_URL = "https://res.cloudinary.com/dnvu5jzwt/image/upload/v1561711112/conceptsALL_mobc2f.png";

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
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_concept,parent,false);
        Log.d( TAG,"onCreateViewHolder");
        return new MyConceptAdapter.ViewHolder(v, onConceptListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyConceptAdapter.ViewHolder viewHolder, int position) {
        Concept concept = dataList.get(position);
        Log.d( TAG,"Concept Title: "+ concept.getTitle());
        viewHolder.tvname.setText(concept.getTitle());
        viewHolder.tvDescription.setText(concept.getDescription());

        Glide.with(this.context)
                .load(CONCEPTS_IMAGE_URL)
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
        private EditText etSearch;

        MyConceptAdapter.OnConceptListener onConceptistener;



        @RequiresApi(api = Build.VERSION_CODES.O)
        public ViewHolder(View itemView, MyConceptAdapter.OnConceptListener onLessonListener) {
            super(itemView);
            //tvname=(TextView)itemView.findViewById(R.id.txt_name);
            //img=(ImageView)itemView.findViewById(R.id.image_view);


            tvname=(TextView)itemView.findViewById(R.id.tv_item_concept_title);
            img=(ImageView)itemView.findViewById(R.id.iv_item_concept);

            tvDescription=(TextView)itemView.findViewById(R.id.tv_item_concept_description);

            tvDescription.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);

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

    public void filerList(ArrayList<Concept> filteredList){
        dataList = filteredList;
        notifyDataSetChanged();
    }
}

