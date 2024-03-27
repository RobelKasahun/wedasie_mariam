package com.google.android.material.datepicker;

import java.util.Calendar;

class DateLongs {
    private DateLongs() {
    }

    static long canonicalYearMonthDay(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        Calendar instance2 = Calendar.getInstance();
        instance2.clear();
        instance2.set(instance.get(1), instance.get(2), instance.get(5));
        return instance2.getTimeInMillis();
    }
}
