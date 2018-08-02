package com.example.attendance.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.attendance.R;

import java.security.CryptoPrimitive;
import java.text.DateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment {
    public CalendarView calendar;
    public TextView Date;
    String getDate, setDate;
    Calendar getcalendar;
    public ScheduleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_schedule, container, false);

        calendar=(CalendarView) view.findViewById(R.id.calendarView);
        Date=(TextView)view.findViewById(R.id.getDate);


        getcalendar=Calendar.getInstance();
        setDate= DateFormat.getDateInstance().format(getcalendar.getTime());

        Date.setText(setDate);


        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                getDate= year+"/"+(month+1)+"/"+dayOfMonth;
                Date.setText(getDate);
            }
        });
        return view;
    }
}
