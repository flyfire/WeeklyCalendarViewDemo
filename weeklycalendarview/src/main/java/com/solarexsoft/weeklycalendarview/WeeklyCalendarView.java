package com.solarexsoft.weeklycalendarview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class WeeklyCalendarView extends LinearLayout {
    RecyclerView mRecyclerView;

    public WeeklyCalendarView(Context context) {
        super(context);
    }

    public WeeklyCalendarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WeeklyCalendarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.weeklycalendarview, this, true);
        mRecyclerView = view.findViewById(R.id.rv_weekly);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }
}
