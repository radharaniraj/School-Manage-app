package com.manage.schoolnode.attendance;

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

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.AttendanceViewHolder> {

    private List<AttendanceModel> Attendances;
    Context attendanceContext;

    public AttendanceAdapter(List<AttendanceModel> attendances, Context attendanceContext) {
        Attendances = attendances;
        this.attendanceContext = attendanceContext;
    }

    public void setAttendances(List<AttendanceModel> attendances) {
        Attendances = attendances;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(attendanceContext);
        View view = inflater.inflate(R.layout.homework_layout,null);
        return new AttendanceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceViewHolder holder, int position) {
        final AttendanceModel attendance = Attendances.get(position);
        String attendanceDate            = attendance.getDate();
        String originalStringFormat      = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        String desiredStringFormat       = "EEE, dd MMM yyyy";
        SimpleDateFormat readingFormat   = new SimpleDateFormat(originalStringFormat);
        SimpleDateFormat outputFormat    = new SimpleDateFormat(desiredStringFormat);
        try {
            Date date = readingFormat.parse(attendanceDate);
            holder.AttendanceDateTv.setText(outputFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(attendance.isPresent()){
            holder.isPresentTv.setText("Present");
            holder.isPresentTv.setTextColor(Color.parseColor("#5ABF28"));
        }
        else{
            holder.isPresentTv.setText("Absent");
            holder.isPresentTv.setTextColor(Color.parseColor("#DF4A2C"));
        }
    }

    @Override
    public int getItemCount() {
        if(Attendances != null){
            return Attendances.size();
        }
        return 0;
    }

    public class AttendanceViewHolder extends RecyclerView.ViewHolder {
        TextView AttendanceDateTv,isPresentTv;
        public AttendanceViewHolder(@NonNull View itemView) {
            super(itemView);
            isPresentTv      = itemView.findViewById(R.id.hw_subject);
            AttendanceDateTv = itemView.findViewById(R.id.hw_date);
        }
    }
}
