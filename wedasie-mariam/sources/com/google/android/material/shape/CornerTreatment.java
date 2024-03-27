package com.google.android.material.shape;

import android.util.Log;

public class CornerTreatment {
    private static final String TAG = "CornerTreatment";
    private final float cornerSize;

    public void getCornerPath(float f, float f2, ShapePath shapePath) {
    }

    public CornerTreatment() {
        this.cornerSize = 0.0f;
    }

    protected CornerTreatment(float f) {
        this.cornerSize = f;
    }

    public float getCornerSize() {
        return this.cornerSize;
    }

    public CornerTreatment withSize(float f) {
        return new CornerTreatment(f);
    }

    public static CornerTreatment withSizeAndCornerClassCheck(CornerTreatment cornerTreatment, float f) {
        CornerTreatment withSize = cornerTreatment.withSize(f);
        if (!withSize.getClass().equals(cornerTreatment.getClass())) {
            Log.w(TAG, "CornerTreatments should override withSize() to return an instance of their class");
        }
        return withSize;
    }
}
