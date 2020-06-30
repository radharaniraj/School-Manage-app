package com.manage.schoolnode.Announcement;

import android.content.Context;
import android.graphics.Color;
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

public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.AnnouncementViewHolder> {

    private List<AnnouncementModel> Announcements;
    Context AnnouncementContext;

    public AnnouncementAdapter(List<AnnouncementModel> announcements, Context AnnouncementContext) {
        Announcements = announcements;
        this.AnnouncementContext = AnnouncementContext;
    }

    public void setAnnouncements(List<AnnouncementModel> announcements) {
        Announcements = announcements;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AnnouncementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(AnnouncementContext);
        View view = inflater.inflate(R.layout.homework_layout,null);
        return new AnnouncementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnnouncementViewHolder holder, int position) {
        final AnnouncementModel Announcement = Announcements.get(position);
        String AnnouncementDate        = Announcement.getDate();
        String originalStringFormat    = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        String desiredStringFormat     = "EEE, dd MMM yyyy";
        SimpleDateFormat readingFormat = new SimpleDateFormat(originalStringFormat);
        SimpleDateFormat outputFormat  = new SimpleDateFormat(desiredStringFormat);
        try {
            Date date = readingFormat.parse(AnnouncementDate);
            holder.AnnouncementDateTv.setText(outputFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.AnnouncementDescTv.setText(Announcement.getDescription());
    }

    @Override
    public int getItemCount() {
        if(Announcements != null){
            return Announcements.size();
        }
        return 0;
    }

    public class AnnouncementViewHolder extends RecyclerView.ViewHolder {
        TextView AnnouncementDateTv,AnnouncementDescTv;
        public AnnouncementViewHolder(@NonNull View itemView) {
            super(itemView);
            AnnouncementDescTv = itemView.findViewById(R.id.hw_description);
            AnnouncementDateTv = itemView.findViewById(R.id.hw_subject);
        }
    }
}
