package com.solarexsoft.weeklycalendarview;

import java.util.Calendar;
import java.util.Date;

public class WeeklyUtils {

    public static Date getStartOfDay(Date date) {
        Date ret = null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        ret = calendar.getTime();
        return ret;
    }

    public static WeeklyItemModel getWeeklyStartEndDate(Date date) {
        WeeklyItemModel model = null;
        Date startDate = null;
        Date endDate = null;
        Calendar dateCalendar = Calendar.getInstance();
        Calendar startCalendar = Calendar.getInstance();
        Calendar endCalendar = Calendar.getInstance();
        dateCalendar.setTime(date);
        startCalendar.setTime(date);
        endCalendar.setTime(date);
        int dayOfWeek = dateCalendar.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                startDate = startCalendar.getTime();
                endCalendar.add(Calendar.DATE, 6);
                endDate = endCalendar.getTime();
                break;
            case Calendar.MONDAY:
                startCalendar.add(Calendar.DATE, -1);
                startDate = startCalendar.getTime();
                endCalendar.add(Calendar.DATE, 5);
                endDate = endCalendar.getTime();
                break;
            case Calendar.TUESDAY:
                startCalendar.add(Calendar.DATE, -2);
                startDate = startCalendar.getTime();
                endCalendar.add(Calendar.DATE, 4);
                endDate = endCalendar.getTime();
                break;
            case Calendar.WEDNESDAY:
                startCalendar.add(Calendar.DATE, -3);
                startDate = startCalendar.getTime();
                endCalendar.add(Calendar.DATE, 3);
                endDate = endCalendar.getTime();
                break;
            case Calendar.THURSDAY:
                startCalendar.add(Calendar.DATE, -4);
                startDate = startCalendar.getTime();
                endCalendar.add(Calendar.DATE, 2);
                endDate = endCalendar.getTime();
                break;
            case Calendar.FRIDAY:
                startCalendar.add(Calendar.DATE, -5);
                startDate = startCalendar.getTime();
                endCalendar.add(Calendar.DATE, 1);
                endDate = endCalendar.getTime();
                break;
            case Calendar.SATURDAY:
                startCalendar.add(Calendar.DATE, -6);
                startDate = startCalendar.getTime();
                endDate = endCalendar.getTime();
                break;
        }
        model = new WeeklyItemModel(startDate, endDate);
        return model;
    }
}
