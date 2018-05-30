package com.solarexsoft.weeklycalendarview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.SimpleDateFormat;
import java.util.List;

public class WeeklyItemAdapter extends RecyclerView.Adapter<WeeklyItemViewHolder> {
    private List<WeeklyItemModel> mDatas;
    private OnItemClickListener mOnItemClickListener;
    private SimpleDateFormat mMonthFormatter;
    private SimpleDateFormat mDayFormatter;
    private int mSelectPosition;
    private Context mContext;
    private int mWidth;

    public WeeklyItemAdapter(Context context) {
        mContext = context;
        mMonthFormatter = new SimpleDateFormat(context.getString(R.string
                .weekly_item_month_format));
        mDayFormatter = new SimpleDateFormat(context.getString(R.string.weekly_item_day_format));
        mWidth = mContext.getResources().getDisplayMetrics().widthPixels/7;
    }

    @NonNull
    @Override
    public WeeklyItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weekly_item,
                parent, false);
        return new WeeklyItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final WeeklyItemViewHolder holder, final int position) {
        final WeeklyItemModel model = mDatas.get(position);
        String month = mMonthFormatter.format(model.getStartDate());
        String day = mDayFormatter.format(model.getStartDate()) + "-" + mDayFormatter.format
                (model.getEndDate());
        holder.tv_month.setText(month);
        holder.tv_days.setText(day);
        int size = getItemCount();
        if (position == 0 || position == 1 || position == 2 || position == size -1 || position == size-2 || position == size-3) {
            holder.rl_weekly.setBackgroundColor(mContext.getResources().getColor(android.R.color.black));
            holder.rl_weekly.setClickable(false);
            holder.v_bottom.setVisibility(View.GONE);
        } else {
            if (position == mSelectPosition) {
                holder.rl_weekly.setBackgroundColor(mContext.getResources().getColor(R.color.CC_27BDFF));
                holder.v_bottom.setVisibility(View.VISIBLE);
            } else {
                holder.rl_weekly.setBackgroundColor(mContext.getResources().getColor(android.R.color.transparent));
                holder.v_bottom.setVisibility(View.GONE);
            }
            holder.rl_weekly.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectPosition = position;
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(model, holder, position);
                    }
                    notifyDataSetChanged();
                }
            });
        }
        ViewGroup.LayoutParams layoutParams = holder.rl_weekly.getLayoutParams();
        layoutParams.width = mWidth;
        holder.rl_weekly.setLayoutParams(layoutParams);
    }

    @Override
    public int getItemCount() {
        if (mDatas != null) {
            return mDatas.size();
        }
        return 0;
    }

    public void setSelection(int position) {
        mSelectPosition = position;
        notifyDataSetChanged();
    }

    public void setDatas(List<WeeklyItemModel> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(WeeklyItemModel data, WeeklyItemViewHolder holder, int position);
    }
}
