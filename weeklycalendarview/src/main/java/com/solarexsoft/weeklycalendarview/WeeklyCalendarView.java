package com.solarexsoft.weeklycalendarview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WeeklyCalendarView extends LinearLayout {
    RecyclerView mRecyclerView;
    WeeklyItemAdapter mAdapter;
    ArrayList<WeeklyItemModel> mModels;
    OnWeeklyItemClickListener mListener;

    public WeeklyCalendarView(Context context) {
        super(context);
        init(context);
    }

    public WeeklyCalendarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WeeklyCalendarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.weeklycalendarview, this, true);
        mRecyclerView = view.findViewById(R.id.rv_weekly);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration divider = new DividerItemDecoration(context, DividerItemDecoration
                .HORIZONTAL);
        divider.setDrawable(context.getResources().getDrawable(R.drawable.weekly_divider));
        mRecyclerView.addItemDecoration(divider);
        mAdapter = new WeeklyItemAdapter(context);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new WeeklyItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(WeeklyItemModel data, WeeklyItemViewHolder holder, int
                    position) {
                int startPosition = position - 3;
                if (startPosition >= 0) {
                    mRecyclerView.smoothScrollToPosition(startPosition);
                } else {
                    mRecyclerView.smoothScrollToPosition(position);
                }
                if (mListener != null) {
                    mListener.onItemClick(position, data);
                }
            }
        });
    }

    public void setStartDate(Date date) {
        Date startParam = WeeklyUtils.getStartOfDay(date);
        WeeklyItemModel startModel = WeeklyUtils.getWeeklyStartEndDate(startParam);
        Date endParam = WeeklyUtils.getStartOfDay(new Date());
        WeeklyItemModel endModel = WeeklyUtils.getWeeklyStartEndDate(endParam);
        long startWeekSunday = startModel.getStartDate().getTime();
        long endWeekSunday = endModel.getStartDate().getTime();
        long diff = endWeekSunday - startWeekSunday;
        if (diff < TimeUnit.DAYS.toMillis(42)) {
            throw new IllegalArgumentException("The start time must be at least 42 days ago.");
        }
        mModels = new ArrayList<>();
        for (long start = startWeekSunday; start <= endWeekSunday; ) {
            Date tmpDate = new Date(start);
            WeeklyItemModel tmpModel = WeeklyUtils.getWeeklyStartEndDate(tmpDate);
            mModels.add(tmpModel);
            start += TimeUnit.DAYS.toMicros(7);
        }
        mAdapter.setDatas(mModels);
        int count = mModels.size();
        mRecyclerView.scrollToPosition(count - 1);
    }

    public void setOnWeeklyItemClickListener(OnWeeklyItemClickListener listener) {
        mListener = listener;
    }

    public interface OnWeeklyItemClickListener {
        void onItemClick(int position, WeeklyItemModel model);
    }
}
