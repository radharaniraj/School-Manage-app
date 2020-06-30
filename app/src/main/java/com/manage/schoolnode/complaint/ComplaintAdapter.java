package com.manage.schoolnode.complaint;

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

public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.HomeWorkViewHolder> {

    List<ComplaintModel> Complaints;
    Context ComplaintContext;

    public ComplaintAdapter(List<ComplaintModel> complaints, Context homeworkContext) {
        Complaints       = complaints;
        ComplaintContext = homeworkContext;
    }

    public void setComplaints(List<ComplaintModel> homeworks) {
        Complaints = homeworks;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomeWorkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ComplaintContext);
        View view               = inflater.inflate(R.layout.homework_layout, null);
        return new HomeWorkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeWorkViewHolder holder, int position) {
        final ComplaintModel complaint = Complaints.get(position);
        String complaintDate           = complaint.getComplaintDate();
        String originalStringFormat    = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        String desiredStringFormat     = "EEE, dd MMM yyyy";
        SimpleDateFormat readingFormat = new SimpleDateFormat(originalStringFormat);
        SimpleDateFormat outputFormat  = new SimpleDateFormat(desiredStringFormat);
        try {
            Date date = readingFormat.parse(complaintDate);
            holder.ComplaintDateTv.setText(outputFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.ComplaintMessageTv.setText(complaint.getComplaint_message());
        holder.ComplaintTeacherTv.setText("From " + complaint.getTeachers().getName());
    }

    @Override
    public int getItemCount() {
        if (Complaints != null) {
            return Complaints.size();
        }
        return 0;
    }

    public class HomeWorkViewHolder extends RecyclerView.ViewHolder {
        TextView ComplaintTeacherTv, ComplaintDateTv, ComplaintMessageTv;

        public HomeWorkViewHolder(@NonNull View itemView) {
            super(itemView);
            ComplaintTeacherTv = itemView.findViewById(R.id.hw_subject);
            ComplaintMessageTv = itemView.findViewById(R.id.hw_description);
            ComplaintDateTv    = itemView.findViewById(R.id.hw_date);
        }
    }
}
