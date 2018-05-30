package com.solarexsoft.weeklycalendarview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
        final RecyclerView.SmoothScroller smoothScroller = new LinearSmoothScroller(context) {
            @Override
            protected int getHorizontalSnapPreference() {
                return LinearSmoothScroller.SNAP_TO_START;
            }
        };
        mAdapter.setOnItemClickListener(new WeeklyItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(WeeklyItemModel data, WeeklyItemViewHolder holder, int
                    position) {
                int startPosition = position - 3;
                if (startPosition >= 0) {
                    smoothScroller.setTargetPosition(startPosition);
                } else {
                    smoothScroller.setTargetPosition(position);
                }
                mRecyclerView.getLayoutManager().startSmoothScroll(smoothScroller);
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
        if (diff < 42*24*60*60*1000) {
            throw new IllegalArgumentException("The start time must be at least 42 days ago.");
        }
        mModels = new ArrayList<>();
        for (long start = startWeekSunday; start <= endWeekSunday; ) {
            Date tmpDate = new Date(start);
            WeeklyItemModel tmpModel = WeeklyUtils.getWeeklyStartEndDate(tmpDate);
            mModels.add(tmpModel);
            start += 7*24*60*60*1000;
        }
        mAdapter.setDatas(mModels);
        int count = mModels.size();
        mAdapter.setSelection(count - 1);
        mRecyclerView.scrollToPosition(count - 1);
    }

    public void setWeekAgo(int weekAgo) {
        Date now = WeeklyUtils.getStartOfDay(new Date());
        WeeklyItemModel nowModel = WeeklyUtils.getWeeklyStartEndDate(now);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowModel.getStartDate());
        calendar.add(Calendar.DATE, -7 * weekAgo);
        Date start = calendar.getTime();
        setStartDate(start);
    }

    public void setOnWeeklyItemClickListener(OnWeeklyItemClickListener listener) {
        mListener = listener;
    }

    public interface OnWeeklyItemClickListener {
        void onItemClick(int position, WeeklyItemModel model);
    }
}
