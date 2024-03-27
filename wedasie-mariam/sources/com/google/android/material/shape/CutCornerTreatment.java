package com.google.android.material.shape;

public class CutCornerTreatment extends CornerTreatment {
    public CutCornerTreatment(float f) {
        super(f);
    }

    public void getCornerPath(float f, float f2, ShapePath shapePath) {
        shapePath.reset(0.0f, getCornerSize() * f2, 180.0f, 180.0f - f);
        double sin = Math.sin(Math.toRadians((double) f));
        double cornerSize = (double) getCornerSize();
        Double.isNaN(cornerSize);
        double d = sin * cornerSize;
        double d2 = (double) f2;
        Double.isNaN(d2);
        float f3 = (float) (d * d2);
        double sin2 = Math.sin(Math.toRadians((double) (90.0f - f)));
        double cornerSize2 = (double) getCornerSize();
        Double.isNaN(cornerSize2);
        Double.isNaN(d2);
        shapePath.lineTo(f3, (float) (sin2 * cornerSize2 * d2));
    }

    public CutCornerTreatment withSize(float f) {
        return new CutCornerTreatment(f);
    }
}
