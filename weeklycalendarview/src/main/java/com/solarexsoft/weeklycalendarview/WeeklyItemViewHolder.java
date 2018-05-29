package com.solarexsoft.weeklycalendarview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class WeeklyItemViewHolder extends RecyclerView.ViewHolder {
    TextView tv_month;
    TextView tv_days;
    View v_bottom;
    RelativeLayout rl_weekly;

    public WeeklyItemViewHolder(View itemView) {
        super(itemView);
        tv_month = itemView.findViewById(R.id.tv_month);
        tv_days = itemView.findViewById(R.id.tv_days);
        v_bottom = itemView.findViewById(R.id.v_bottom);
        rl_weekly = itemView.findViewById(R.id.rl_weekly);
    }
}
