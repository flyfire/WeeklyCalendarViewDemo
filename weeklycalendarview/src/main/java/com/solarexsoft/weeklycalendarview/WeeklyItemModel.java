package com.solarexsoft.weeklycalendarview;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WeeklyItemModel {
    Date startDate;
    Date endDate;
    public static SimpleDateFormat mFormatter = new SimpleDateFormat("yyyy.MM.dd");

    public WeeklyItemModel(Date start, Date end) {
        this.startDate = start;
        this.endDate = end;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(mFormatter.format(startDate)).append("--").append(mFormatter.format(endDate));
        return builder.toString();
    }
}
