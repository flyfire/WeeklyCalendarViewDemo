package com.solarexsoft.weeklycalendar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.solarexsoft.weeklycalendarview.WeeklyCalendarView;
import com.solarexsoft.weeklycalendarview.WeeklyItemModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WeeklyCalendarView weeklyCalendarView = findViewById(R.id.wcl_main);
        Date startDate = new Date(80, 0, 1);
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        Log.d(TAG, "start = " + format.format(startDate));
//        weeklyCalendarView.setStartDate(startDate);
        weeklyCalendarView.setWeekAgo(30);
        weeklyCalendarView.setOnWeeklyItemClickListener(new WeeklyCalendarView.OnWeeklyItemClickListener() {


            @Override
            public void onItemClick(int position, WeeklyItemModel model) {
                Log.d(TAG, model.toString());
            }
        });
    }
}
