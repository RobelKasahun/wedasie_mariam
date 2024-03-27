package com.google.android.material.datepicker;

import android.icu.text.DateFormat;
import android.os.Build;
import androidx.core.util.Pair;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

class DateStrings {
    private DateStrings() {
    }

    static String getYearMonthDay(Date date, Locale locale) {
        if (Build.VERSION.SDK_INT >= 24) {
            return DateFormat.getInstanceForSkeleton("yMMMd", locale).format(date);
        }
        return java.text.DateFormat.getDateInstance(2, locale).format(date);
    }

    static String getMonthDay(Date date, Locale locale) {
        if (Build.VERSION.SDK_INT >= 24) {
            return DateFormat.getInstanceForSkeleton("MMMd", locale).format(date);
        }
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) java.text.DateFormat.getDateInstance(2, locale);
        simpleDateFormat.applyPattern(removeYearFromDateFormatPattern(simpleDateFormat.toPattern()));
        return simpleDateFormat.format(date);
    }

    static String getMonthDayOfWeekDay(long j) {
        if (Build.VERSION.SDK_INT >= 24) {
            return DateFormat.getInstanceForSkeleton("MMMEd", Locale.getDefault()).format(new Date(j));
        }
        return java.text.DateFormat.getDateInstance(0, Locale.getDefault()).format(new Date(j));
    }

    static String getYearMonthDayOfWeekDay(long j) {
        if (Build.VERSION.SDK_INT >= 24) {
            return DateFormat.getInstanceForSkeleton("yMMMEd", Locale.getDefault()).format(new Date(j));
        }
        return java.text.DateFormat.getDateInstance(0, Locale.getDefault()).format(new Date(j));
    }

    static String getDateString(long j) {
        return getDateString(j, (SimpleDateFormat) null);
    }

    static String getDateString(long j, SimpleDateFormat simpleDateFormat) {
        Calendar instance = Calendar.getInstance();
        Locale locale = Locale.getDefault();
        Date date = new Date(j);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTimeInMillis(j);
        if (simpleDateFormat != null) {
            return simpleDateFormat.format(date);
        }
        if (instance.get(1) == instance2.get(1)) {
            return getMonthDay(date, locale);
        }
        return getYearMonthDay(date, locale);
    }

    static Pair<String, String> getDateRangeString(Long l, Long l2) {
        return getDateRangeString(l, l2, (SimpleDateFormat) null);
    }

    static Pair<String, String> getDateRangeString(Long l, Long l2, SimpleDateFormat simpleDateFormat) {
        if (l == null && l2 == null) {
            return Pair.create(null, null);
        }
        if (l == null) {
            return Pair.create(null, getDateString(l2.longValue(), simpleDateFormat));
        }
        if (l2 == null) {
            return Pair.create(getDateString(l.longValue(), simpleDateFormat), null);
        }
        Calendar instance = Calendar.getInstance();
        Locale locale = Locale.getDefault();
        Date date = new Date(l.longValue());
        Date date2 = new Date(l2.longValue());
        Calendar instance2 = Calendar.getInstance();
        instance2.setTimeInMillis(l.longValue());
        Calendar instance3 = Calendar.getInstance();
        instance3.setTimeInMillis(l2.longValue());
        if (simpleDateFormat != null) {
            return Pair.create(simpleDateFormat.format(date), simpleDateFormat.format(date2));
        }
        if (instance2.get(1) != instance3.get(1)) {
            return Pair.create(getYearMonthDay(date, locale), getYearMonthDay(date2, locale));
        }
        if (instance2.get(1) == instance.get(1)) {
            return Pair.create(getMonthDay(date, locale), getMonthDay(date2, locale));
        }
        return Pair.create(getMonthDay(date, locale), getYearMonthDay(date2, locale));
    }

    private static String removeYearFromDateFormatPattern(String str) {
        int findCharactersInDateFormatPattern = findCharactersInDateFormatPattern(str, "yY", 1, 0);
        if (findCharactersInDateFormatPattern >= str.length()) {
            return str;
        }
        String str2 = "EMd";
        int findCharactersInDateFormatPattern2 = findCharactersInDateFormatPattern(str, str2, 1, findCharactersInDateFormatPattern);
        if (findCharactersInDateFormatPattern2 < str.length()) {
            str2 = str2 + ",";
        }
        return str.replace(str.substring(findCharactersInDateFormatPattern(str, str2, -1, findCharactersInDateFormatPattern) + 1, findCharactersInDateFormatPattern2), " ").trim();
    }

    private static int findCharactersInDateFormatPattern(String str, String str2, int i, int i2) {
        while (i2 >= 0 && i2 < str.length() && str2.indexOf(str.charAt(i2)) == -1) {
            if (str.charAt(i2) == '\'') {
                do {
                    i2 += i;
                    if (i2 < 0) {
                        break;
                    } else if (i2 >= str.length()) {
                        break;
                    }
                } while (str.charAt(i2) == '\'');
            }
            i2 += i;
        }
        return i2;
    }
}
