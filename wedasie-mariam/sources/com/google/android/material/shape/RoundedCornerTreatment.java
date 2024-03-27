package com.google.android.material.shape;

public class RoundedCornerTreatment extends CornerTreatment {
    public RoundedCornerTreatment(float f) {
        super(f);
    }

    public void getCornerPath(float f, float f2, ShapePath shapePath) {
        float cornerSize = getCornerSize();
        shapePath.reset(0.0f, cornerSize * f2, 180.0f, 180.0f - f);
        float f3 = cornerSize * 2.0f * f2;
        shapePath.addArc(0.0f, 0.0f, f3, f3, 180.0f, f);
    }

    public RoundedCornerTreatment withSize(float f) {
        return new RoundedCornerTreatment(f);
    }
}
