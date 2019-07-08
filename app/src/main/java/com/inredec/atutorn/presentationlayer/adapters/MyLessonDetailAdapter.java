package com.inredec.atutorn.presentationlayer.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.inredec.atutorn.R;
import com.inredec.atutorn.model.businesslayer.entities.Content;
import com.inredec.atutorn.presentationlayer.controllers.activities.ContactActivity;
import com.inredec.atutorn.presentationlayer.controllers.activities.MainMenuActivity;
import com.inredec.atutorn.presentationlayer.controllers.activities.QuizActivity;

import java.util.List;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class MyLessonDetailAdapter extends RecyclerView.Adapter<MyLessonDetailAdapter.ViewHolder>{

    private static final String TAG = "MyLessonDetailAdapter";
    private List<Content> dataList;
    private Context context;

    public MyLessonDetailAdapter(List<Content> dataList , Context context){
        this.dataList = dataList;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_lesso2n,parent,false);
        return new ViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Content content = dataList.get(position);
        Log.d(TAG ,"Content Title: "+ content.getTitle());
        Log.d(TAG ,"Content Description: "+ content.getDescription());
        Log.d(TAG ,"Content URL: "+ content.getImage());
        viewHolder.detail.setText(content.getDescription());
        viewHolder.title.setText(content.getTitle());
        if (content.getPosition() != 0){
            viewHolder.title.setTextColor(ContextCompat.getColor(context, R.color.content_text_color));
            viewHolder.title.setTypeface(null, Typeface.NORMAL);
            viewHolder.title.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            viewHolder.title.setLineSpacing(10, 1.2f);
            Log.d(TAG, "GetPosition: "+ content.getPosition() + " ItemCunt: " + getItemCount() + " Parameter Position: "+position);

            if (content.getPosition() == getItemCount()-1){
                // If last content --> show button to questionaty
                viewHolder.bt_questionaty.setVisibility(View.VISIBLE);
                viewHolder.bt_questionaty.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d(TAG, "Questionary Button clicked");
                        Intent myIntent = new Intent(view.getContext(), QuizActivity.class);
                        context.startActivity(myIntent);
                    }
                });
            }

        }
        viewHolder.detail.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        viewHolder.detail.setLineSpacing(60, 0);
        Glide.with(this.context)
                .load(content.getImage())
                //.centerCrop()
                .fitCenter()
                .override(500, 500)
                .into(viewHolder.img);


    }

    @Override
    public int getItemCount() {
        return this.dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView detail;
        private ImageView img;
        private Button bt_questionaty;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.tv_item_detail_content_title);
            detail=(TextView)itemView.findViewById(R.id.tv_item_lesson_description);
            img = (ImageView)itemView.findViewById(R.id.iv_item_content);
            bt_questionaty = (Button)itemView.findViewById(R.id.bt_questionaty);
            Log.d(TAG ,"ViewHolder");

        }
    }
}
