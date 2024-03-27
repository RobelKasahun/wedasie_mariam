package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public final class Month implements Comparable<Month>, Parcelable {
    public static final Parcelable.Creator<Month> CREATOR = new Parcelable.Creator<Month>() {
        public Month createFromParcel(Parcel parcel) {
            return Month.create(parcel.readInt(), parcel.readInt());
        }

        public Month[] newArray(int i) {
            return new Month[i];
        }
    };
    private final Calendar calendar = Calendar.getInstance();
    final int daysInMonth;
    final int daysInWeek;
    private final String longName;
    final int month;
    final int year;

    public int describeContents() {
        return 0;
    }

    private Month(Calendar calendar2) {
        this.calendar.setTimeInMillis(DateLongs.canonicalYearMonthDay(calendar2.getTimeInMillis()));
        this.month = this.calendar.get(2);
        this.year = this.calendar.get(1);
        this.daysInWeek = this.calendar.getMaximum(7);
        this.daysInMonth = this.calendar.getActualMaximum(5);
        this.longName = new SimpleDateFormat("MMMM, yyyy", Locale.getDefault()).format(this.calendar.getTime());
    }

    public static Month create(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        return new Month(instance);
    }

    static Month create(int i, int i2) {
        Calendar instance = Calendar.getInstance();
        instance.clear();
        instance.set(1, i);
        instance.set(2, i2);
        return new Month(instance);
    }

    public static Month today() {
        Calendar instance = Calendar.getInstance();
        return create(instance.get(1), instance.get(2));
    }

    /* access modifiers changed from: package-private */
    public int daysFromStartOfWeekToFirstOfMonth() {
        int firstDayOfWeek = this.calendar.get(7) - this.calendar.getFirstDayOfWeek();
        return firstDayOfWeek < 0 ? firstDayOfWeek + this.daysInWeek : firstDayOfWeek;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Month)) {
            return false;
        }
        Month month2 = (Month) obj;
        if (this.month == month2.month && this.year == month2.year) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.month), Integer.valueOf(this.year)});
    }

    public int compareTo(Month month2) {
        return this.calendar.compareTo(month2.calendar);
    }

    /* access modifiers changed from: package-private */
    public int monthsUntil(Month month2) {
        if (this.calendar instanceof GregorianCalendar) {
            return ((month2.year - this.year) * 12) + (month2.month - this.month);
        }
        throw new IllegalArgumentException("Only Gregorian calendars are supported.");
    }

    /* access modifiers changed from: package-private */
    public long getStableId() {
        return this.calendar.getTimeInMillis();
    }

    /* access modifiers changed from: package-private */
    public long getDay(int i) {
        Calendar calendar2 = (Calendar) this.calendar.clone();
        calendar2.set(5, i);
        return calendar2.getTimeInMillis();
    }

    /* access modifiers changed from: package-private */
    public Month monthsLater(int i) {
        Calendar calendar2 = (Calendar) this.calendar.clone();
        calendar2.add(2, i);
        return new Month(calendar2);
    }

    /* access modifiers changed from: package-private */
    public String getLongName() {
        return this.longName;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.year);
        parcel.writeInt(this.month);
    }
}
