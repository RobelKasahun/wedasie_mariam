package com.google.android.material.datepicker;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

public final class CalendarConstraints implements Parcelable {
    public static final Parcelable.Creator<CalendarConstraints> CREATOR = new Parcelable.Creator<CalendarConstraints>() {
        public CalendarConstraints createFromParcel(Parcel parcel) {
            return new CalendarConstraints((Month) parcel.readParcelable(Month.class.getClassLoader()), (Month) parcel.readParcelable(Month.class.getClassLoader()), (Month) parcel.readParcelable(Month.class.getClassLoader()), (DateValidator) parcel.readParcelable(DateValidator.class.getClassLoader()));
        }

        public CalendarConstraints[] newArray(int i) {
            return new CalendarConstraints[i];
        }
    };
    /* access modifiers changed from: private */
    public final Month end;
    private final int monthSpan;
    /* access modifiers changed from: private */
    public final Month opening;
    /* access modifiers changed from: private */
    public final Month start;
    /* access modifiers changed from: private */
    public final DateValidator validator;
    private final int yearSpan;

    public interface DateValidator extends Parcelable {
        boolean isValid(long j);
    }

    public int describeContents() {
        return 0;
    }

    private CalendarConstraints(Month month, Month month2, Month month3, DateValidator dateValidator) {
        this.start = month;
        this.end = month2;
        this.opening = month3;
        this.validator = dateValidator;
        if (month.compareTo(month3) > 0) {
            throw new IllegalArgumentException("start Month cannot be after current Month");
        } else if (month3.compareTo(month2) <= 0) {
            this.monthSpan = month.monthsUntil(month2) + 1;
            this.yearSpan = (month2.year - month.year) + 1;
        } else {
            throw new IllegalArgumentException("current Month cannot be after end Month");
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isWithinBounds(long j) {
        if (this.start.getDay(1) <= j) {
            Month month = this.end;
            if (j <= month.getDay(month.daysInMonth)) {
                return true;
            }
        }
        return false;
    }

    public DateValidator getDateValidator() {
        return this.validator;
    }

    public Month getStart() {
        return this.start;
    }

    public Month getEnd() {
        return this.end;
    }

    public Month getOpening() {
        return this.opening;
    }

    /* access modifiers changed from: package-private */
    public int getMonthSpan() {
        return this.monthSpan;
    }

    /* access modifiers changed from: package-private */
    public int getYearSpan() {
        return this.yearSpan;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CalendarConstraints)) {
            return false;
        }
        CalendarConstraints calendarConstraints = (CalendarConstraints) obj;
        if (!this.start.equals(calendarConstraints.start) || !this.end.equals(calendarConstraints.end) || !this.opening.equals(calendarConstraints.opening) || !this.validator.equals(calendarConstraints.validator)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.start, this.end, this.opening, this.validator});
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.start, 0);
        parcel.writeParcelable(this.end, 0);
        parcel.writeParcelable(this.opening, 0);
        parcel.writeParcelable(this.validator, 0);
    }

    public static class Builder {
        private static final String DEEP_COPY_VALIDATOR_KEY = "DEEP_COPY_VALIDATOR_KEY";
        public static final Month DEFAULT_END = Month.create(2100, 11);
        public static final Month DEFAULT_START = Month.create(1900, 0);
        private Month end = DEFAULT_END;
        private Month opening;
        private Month start = DEFAULT_START;
        private DateValidator validator = new DateValidatorPointForward(Long.MIN_VALUE);

        public Builder() {
        }

        Builder(CalendarConstraints calendarConstraints) {
            this.start = calendarConstraints.start;
            this.end = calendarConstraints.end;
            this.opening = calendarConstraints.opening;
            this.validator = calendarConstraints.validator;
        }

        public Builder setStart(Month month) {
            this.start = month;
            return this;
        }

        public Builder setEnd(Month month) {
            this.end = month;
            return this;
        }

        public Builder setOpening(Month month) {
            this.opening = month;
            return this;
        }

        public Builder setValidator(DateValidator dateValidator) {
            this.validator = dateValidator;
            return this;
        }

        public CalendarConstraints build() {
            if (this.opening == null) {
                Month month = Month.today();
                if (this.start.compareTo(month) > 0 || month.compareTo(this.end) > 0) {
                    month = this.start;
                }
                this.opening = month;
            }
            Bundle bundle = new Bundle();
            bundle.putParcelable(DEEP_COPY_VALIDATOR_KEY, this.validator);
            return new CalendarConstraints(this.start, this.end, this.opening, (DateValidator) bundle.getParcelable(DEEP_COPY_VALIDATOR_KEY));
        }
    }
}
