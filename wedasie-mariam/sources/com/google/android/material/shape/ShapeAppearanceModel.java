package com.google.android.material.shape;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import com.google.android.material.R;

public class ShapeAppearanceModel {
    public static final int PILL = -1;
    EdgeTreatment bottomEdge;
    CornerTreatment bottomLeftCorner;
    CornerTreatment bottomRightCorner;
    EdgeTreatment leftEdge;
    EdgeTreatment rightEdge;
    EdgeTreatment topEdge;
    CornerTreatment topLeftCorner;
    CornerTreatment topRightCorner;

    public static final class Builder {
        /* access modifiers changed from: private */
        public EdgeTreatment bottomEdge = MaterialShapeUtils.createDefaultEdgeTreatment();
        /* access modifiers changed from: private */
        public CornerTreatment bottomLeftCorner = MaterialShapeUtils.createDefaultCornerTreatment();
        /* access modifiers changed from: private */
        public CornerTreatment bottomRightCorner = MaterialShapeUtils.createDefaultCornerTreatment();
        /* access modifiers changed from: private */
        public EdgeTreatment leftEdge = MaterialShapeUtils.createDefaultEdgeTreatment();
        /* access modifiers changed from: private */
        public EdgeTreatment rightEdge = MaterialShapeUtils.createDefaultEdgeTreatment();
        /* access modifiers changed from: private */
        public EdgeTreatment topEdge = MaterialShapeUtils.createDefaultEdgeTreatment();
        /* access modifiers changed from: private */
        public CornerTreatment topLeftCorner = MaterialShapeUtils.createDefaultCornerTreatment();
        /* access modifiers changed from: private */
        public CornerTreatment topRightCorner = MaterialShapeUtils.createDefaultCornerTreatment();

        public Builder() {
        }

        public Builder(ShapeAppearanceModel shapeAppearanceModel) {
            this.topLeftCorner = shapeAppearanceModel.topLeftCorner;
            this.topRightCorner = shapeAppearanceModel.topRightCorner;
            this.bottomRightCorner = shapeAppearanceModel.bottomRightCorner;
            this.bottomLeftCorner = shapeAppearanceModel.bottomLeftCorner;
            this.topEdge = shapeAppearanceModel.topEdge;
            this.rightEdge = shapeAppearanceModel.rightEdge;
            this.bottomEdge = shapeAppearanceModel.bottomEdge;
            this.leftEdge = shapeAppearanceModel.leftEdge;
        }

        public Builder setAllCorners(int i, int i2) {
            return setAllCorners(MaterialShapeUtils.createCornerTreatment(i, i2));
        }

        public Builder setAllCorners(CornerTreatment cornerTreatment) {
            return setTopLeftCorner(cornerTreatment).setTopRightCorner(cornerTreatment).setBottomRightCorner(cornerTreatment).setBottomLeftCorner(cornerTreatment);
        }

        public Builder setCornerRadius(float f) {
            return setTopLeftCornerSize(f).setTopRightCornerSize(f).setBottomRightCornerSize(f).setBottomLeftCornerSize(f);
        }

        public Builder setTopLeftCornerSize(float f) {
            this.topLeftCorner = CornerTreatment.withSizeAndCornerClassCheck(this.topLeftCorner, f);
            return this;
        }

        public Builder setTopRightCornerSize(float f) {
            this.topRightCorner = CornerTreatment.withSizeAndCornerClassCheck(this.topRightCorner, f);
            return this;
        }

        public Builder setBottomRightCornerSize(float f) {
            this.bottomRightCorner = CornerTreatment.withSizeAndCornerClassCheck(this.bottomRightCorner, f);
            return this;
        }

        public Builder setBottomLeftCornerSize(float f) {
            this.bottomLeftCorner = CornerTreatment.withSizeAndCornerClassCheck(this.bottomLeftCorner, f);
            return this;
        }

        public Builder setTopLeftCorner(int i, int i2) {
            return setTopLeftCorner(MaterialShapeUtils.createCornerTreatment(i, i2));
        }

        public Builder setTopLeftCorner(CornerTreatment cornerTreatment) {
            this.topLeftCorner = cornerTreatment;
            return this;
        }

        public Builder setTopRightCorner(int i, int i2) {
            return setTopRightCorner(MaterialShapeUtils.createCornerTreatment(i, i2));
        }

        public Builder setTopRightCorner(CornerTreatment cornerTreatment) {
            this.topRightCorner = cornerTreatment;
            return this;
        }

        public Builder setBottomRightCorner(int i, int i2) {
            return setBottomRightCorner(MaterialShapeUtils.createCornerTreatment(i, i2));
        }

        public Builder setBottomRightCorner(CornerTreatment cornerTreatment) {
            this.bottomRightCorner = cornerTreatment;
            return this;
        }

        public Builder setBottomLeftCorner(int i, int i2) {
            return setBottomLeftCorner(MaterialShapeUtils.createCornerTreatment(i, i2));
        }

        public Builder setBottomLeftCorner(CornerTreatment cornerTreatment) {
            this.bottomLeftCorner = cornerTreatment;
            return this;
        }

        public Builder setAllEdges(EdgeTreatment edgeTreatment) {
            return setLeftEdge(edgeTreatment).setTopEdge(edgeTreatment).setRightEdge(edgeTreatment).setBottomEdge(edgeTreatment);
        }

        public Builder setLeftEdge(EdgeTreatment edgeTreatment) {
            this.leftEdge = edgeTreatment;
            return this;
        }

        public Builder setTopEdge(EdgeTreatment edgeTreatment) {
            this.topEdge = edgeTreatment;
            return this;
        }

        public Builder setRightEdge(EdgeTreatment edgeTreatment) {
            this.rightEdge = edgeTreatment;
            return this;
        }

        public Builder setBottomEdge(EdgeTreatment edgeTreatment) {
            this.bottomEdge = edgeTreatment;
            return this;
        }

        public Builder adjustCorners(float f) {
            return setTopLeftCornerSize(getOffsetCornerSize(this.topLeftCorner, f)).setTopRightCornerSize(getOffsetCornerSize(this.topRightCorner, f)).setBottomRightCornerSize(getOffsetCornerSize(this.bottomRightCorner, f)).setBottomLeftCornerSize(getOffsetCornerSize(this.bottomLeftCorner, f));
        }

        private static float getOffsetCornerSize(CornerTreatment cornerTreatment, float f) {
            return Math.max(0.0f, cornerTreatment.getCornerSize() + f);
        }

        public ShapeAppearanceModel build() {
            return new ShapeAppearanceModel(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(Context context, AttributeSet attributeSet, int i, int i2) {
        return builder(context, attributeSet, i, i2, 0);
    }

    public static Builder builder(Context context, AttributeSet attributeSet, int i, int i2, int i3) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MaterialShape, i, i2);
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.MaterialShape_shapeAppearance, 0);
        int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.MaterialShape_shapeAppearanceOverlay, 0);
        obtainStyledAttributes.recycle();
        return builder(context, resourceId, resourceId2, i3);
    }

    public static Builder builder(Context context, int i, int i2) {
        return builder(context, i, i2, 0);
    }

    private static Builder builder(Context context, int i, int i2, int i3) {
        if (i2 != 0) {
            ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(context, i);
            i = i2;
            context = contextThemeWrapper;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i, R.styleable.ShapeAppearance);
        try {
            int i4 = obtainStyledAttributes.getInt(R.styleable.ShapeAppearance_cornerFamily, 0);
            int i5 = obtainStyledAttributes.getInt(R.styleable.ShapeAppearance_cornerFamilyTopLeft, i4);
            int i6 = obtainStyledAttributes.getInt(R.styleable.ShapeAppearance_cornerFamilyTopRight, i4);
            int i7 = obtainStyledAttributes.getInt(R.styleable.ShapeAppearance_cornerFamilyBottomRight, i4);
            int i8 = obtainStyledAttributes.getInt(R.styleable.ShapeAppearance_cornerFamilyBottomLeft, i4);
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ShapeAppearance_cornerSize, i3);
            int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ShapeAppearance_cornerSizeTopLeft, dimensionPixelSize);
            int dimensionPixelSize3 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ShapeAppearance_cornerSizeTopRight, dimensionPixelSize);
            int dimensionPixelSize4 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ShapeAppearance_cornerSizeBottomRight, dimensionPixelSize);
            return new Builder().setTopLeftCorner(i5, dimensionPixelSize2).setTopRightCorner(i6, dimensionPixelSize3).setBottomRightCorner(i7, dimensionPixelSize4).setBottomLeftCorner(i8, obtainStyledAttributes.getDimensionPixelSize(R.styleable.ShapeAppearance_cornerSizeBottomLeft, dimensionPixelSize));
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    private ShapeAppearanceModel(Builder builder) {
        this.topLeftCorner = builder.topLeftCorner;
        this.topRightCorner = builder.topRightCorner;
        this.bottomRightCorner = builder.bottomRightCorner;
        this.bottomLeftCorner = builder.bottomLeftCorner;
        this.topEdge = builder.topEdge;
        this.rightEdge = builder.rightEdge;
        this.bottomEdge = builder.bottomEdge;
        this.leftEdge = builder.leftEdge;
    }

    public ShapeAppearanceModel() {
        this.topLeftCorner = MaterialShapeUtils.createDefaultCornerTreatment();
        this.topRightCorner = MaterialShapeUtils.createDefaultCornerTreatment();
        this.bottomRightCorner = MaterialShapeUtils.createDefaultCornerTreatment();
        this.bottomLeftCorner = MaterialShapeUtils.createDefaultCornerTreatment();
        this.topEdge = MaterialShapeUtils.createDefaultEdgeTreatment();
        this.rightEdge = MaterialShapeUtils.createDefaultEdgeTreatment();
        this.bottomEdge = MaterialShapeUtils.createDefaultEdgeTreatment();
        this.leftEdge = MaterialShapeUtils.createDefaultEdgeTreatment();
    }

    public CornerTreatment getTopLeftCorner() {
        return this.topLeftCorner;
    }

    public CornerTreatment getTopRightCorner() {
        return this.topRightCorner;
    }

    public CornerTreatment getBottomRightCorner() {
        return this.bottomRightCorner;
    }

    public CornerTreatment getBottomLeftCorner() {
        return this.bottomLeftCorner;
    }

    public EdgeTreatment getLeftEdge() {
        return this.leftEdge;
    }

    public EdgeTreatment getTopEdge() {
        return this.topEdge;
    }

    public EdgeTreatment getRightEdge() {
        return this.rightEdge;
    }

    public EdgeTreatment getBottomEdge() {
        return this.bottomEdge;
    }

    public boolean isUsingPillCorner() {
        return getTopRightCorner().getCornerSize() == -1.0f && getTopLeftCorner().getCornerSize() == -1.0f && getBottomLeftCorner().getCornerSize() == -1.0f && getBottomRightCorner().getCornerSize() == -1.0f;
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    public ShapeAppearanceModel withCornerRadius(float f) {
        return toBuilder().setCornerRadius(f).build();
    }

    public ShapeAppearanceModel withAdjustedCorners(float f) {
        return toBuilder().adjustCorners(f).build();
    }

    public boolean isRoundRect() {
        boolean z = this.leftEdge.getClass().equals(EdgeTreatment.class) && this.rightEdge.getClass().equals(EdgeTreatment.class) && this.topEdge.getClass().equals(EdgeTreatment.class) && this.bottomEdge.getClass().equals(EdgeTreatment.class);
        float cornerSize = this.topLeftCorner.getCornerSize();
        boolean z2 = this.topRightCorner.getCornerSize() == cornerSize && this.bottomLeftCorner.getCornerSize() == cornerSize && this.bottomRightCorner.getCornerSize() == cornerSize;
        boolean z3 = (this.topRightCorner instanceof RoundedCornerTreatment) && (this.topLeftCorner instanceof RoundedCornerTreatment) && (this.bottomRightCorner instanceof RoundedCornerTreatment) && (this.bottomLeftCorner instanceof RoundedCornerTreatment);
        if (!z || !z2 || !z3) {
            return false;
        }
        return true;
    }
}
