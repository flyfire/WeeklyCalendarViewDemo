package com.solarexsoft.weeklycalendarview;

import java.util.Date;

public class WeeklyItemModel {
    Date startDate;
    Date endDate;

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
}
