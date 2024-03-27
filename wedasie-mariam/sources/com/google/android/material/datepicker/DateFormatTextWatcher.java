package com.google.android.material.datepicker;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import com.google.android.material.R;
import com.google.android.material.textfield.TextInputLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

abstract class DateFormatTextWatcher implements TextWatcher {
    private final CalendarConstraints constraints;
    private final DateFormat dateFormat;
    private final String invalidFormat;
    private final String outOfRange;
    private final String pattern;
    private final TextInputLayout textInputLayout;

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    /* access modifiers changed from: package-private */
    public void onInvalidDate() {
    }

    /* access modifiers changed from: package-private */
    public abstract void onValidDate(Long l);

    DateFormatTextWatcher(String str, DateFormat dateFormat2, TextInputLayout textInputLayout2, CalendarConstraints calendarConstraints) {
        this.pattern = str;
        this.dateFormat = dateFormat2;
        this.textInputLayout = textInputLayout2;
        this.constraints = calendarConstraints;
        this.invalidFormat = textInputLayout2.getContext().getString(R.string.mtrl_picker_invalid_format);
        this.outOfRange = textInputLayout2.getContext().getString(R.string.mtrl_picker_out_of_range);
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (TextUtils.isEmpty(charSequence)) {
            this.textInputLayout.setError((CharSequence) null);
            onValidDate((Long) null);
            return;
        }
        try {
            Date parse = this.dateFormat.parse(charSequence.toString());
            this.textInputLayout.setError((CharSequence) null);
            long time = parse.getTime();
            if (!this.constraints.getDateValidator().isValid(time) || !this.constraints.isWithinBounds(time)) {
                this.textInputLayout.setError(String.format(this.outOfRange, new Object[]{DateStrings.getDateString(time)}));
                onInvalidDate();
                return;
            }
            onValidDate(Long.valueOf(parse.getTime()));
        } catch (ParseException unused) {
            this.textInputLayout.setError(String.format(this.invalidFormat, new Object[]{this.pattern}));
            onInvalidDate();
        }
    }
}
