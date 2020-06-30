package com.manage.schoolnode.homework;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.manage.schoolnode.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HomeworkAdapter extends RecyclerView.Adapter<HomeworkAdapter.HomeWorkViewHolder> {

    List<HomeworkModel> Homeworks;
    Context HomeworkContext;

    public HomeworkAdapter(List<HomeworkModel> homeworks, Context homeworkContext) {
        Homeworks       = homeworks;
        HomeworkContext = homeworkContext;
    }

    public void setHomeworks(List<HomeworkModel> homeworks) {
        Homeworks = homeworks;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomeWorkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(HomeworkContext);
        View view               = inflater.inflate(R.layout.homework_layout,null);
        return new HomeWorkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeWorkViewHolder holder, int position) {
        final HomeworkModel homework   = Homeworks.get(position);
        String homeworkDate            = homework.getDate();
        String originalStringFormat    = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        String desiredStringFormat     = "EEE, dd MMM yyyy";
        SimpleDateFormat readingFormat = new SimpleDateFormat(originalStringFormat);
        SimpleDateFormat outputFormat  = new SimpleDateFormat(desiredStringFormat);
        try {
            Date date = readingFormat.parse(homeworkDate);
            holder.HomeworkDateTv.setText(outputFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.HomeworkDescTv.setText(homework.getHwDescription());
        holder.HomeWorkSubjectTv.setText("Subject = "+homework.getSubjectId());
    }

    @Override
    public int getItemCount() {
        if(Homeworks != null){
            return Homeworks.size();
        }
        return 0;
    }

    public class HomeWorkViewHolder extends RecyclerView.ViewHolder {
        TextView HomeworkDescTv,HomeworkDateTv ,HomeWorkSubjectTv;
        public HomeWorkViewHolder(@NonNull View itemView) {

            super(itemView);
            HomeworkDescTv    = itemView.findViewById(R.id.hw_description);
            HomeWorkSubjectTv = itemView.findViewById(R.id.hw_subject);
            HomeworkDateTv    = itemView.findViewById(R.id.hw_date);
        }
    }
}
