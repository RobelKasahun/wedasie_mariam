package com.google.android.material.elevation;

import android.content.Context;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialAttributes;

public class ElevationOverlayProvider {
    private static final float FORMULA_MULTIPLIER = 4.5f;
    private static final float FORMULA_OFFSET = 2.0f;
    private final int colorSurface;
    private final float displayDensity;
    private final int elevationOverlayColor;
    private final boolean elevationOverlayEnabled;

    public ElevationOverlayProvider(Context context) {
        this.elevationOverlayEnabled = MaterialAttributes.resolveBoolean(context, R.attr.elevationOverlayEnabled, false);
        this.elevationOverlayColor = MaterialColors.getColor(context, R.attr.elevationOverlayColor, 0);
        this.colorSurface = MaterialColors.getColor(context, R.attr.colorSurface, 0);
        this.displayDensity = context.getResources().getDisplayMetrics().density;
    }

    public int compositeOverlayWithThemeSurfaceColorIfNeeded(float f) {
        return compositeOverlayWithThemeSurfaceColorIfNeeded(f, (View) null);
    }

    public int compositeOverlayWithThemeSurfaceColorIfNeeded(float f, View view) {
        if (view != null) {
            f += getParentAbsoluteElevation(view);
        }
        return compositeOverlayIfNeeded(this.colorSurface, f);
    }

    public int compositeOverlayIfNeeded(int i, float f) {
        return compositeOverlayIfNeeded(i, f, (View) null);
    }

    public int compositeOverlayIfNeeded(int i, float f, View view) {
        if (view != null) {
            f += getParentAbsoluteElevation(view);
        }
        return (!this.elevationOverlayEnabled || !isThemeSurfaceColor(i)) ? i : compositeOverlay(i, f);
    }

    public int compositeOverlay(int i, float f) {
        return compositeOverlay(i, f, (View) null);
    }

    public int compositeOverlay(int i, float f, View view) {
        if (view != null) {
            f += getParentAbsoluteElevation(view);
        }
        return MaterialColors.layer(i, this.elevationOverlayColor, calculateOverlayAlphaFraction(f));
    }

    public int calculateOverlayAlpha(float f) {
        return Math.round(calculateOverlayAlphaFraction(f) * 255.0f);
    }

    public float calculateOverlayAlphaFraction(float f) {
        float f2 = this.displayDensity;
        if (f2 <= 0.0f || f <= 0.0f) {
            return 0.0f;
        }
        return Math.min(((((float) Math.log1p((double) (f / f2))) * FORMULA_MULTIPLIER) + FORMULA_OFFSET) / 100.0f, 1.0f);
    }

    public boolean isThemeElevationOverlayEnabled() {
        return this.elevationOverlayEnabled;
    }

    public int getThemeElevationOverlayColor() {
        return this.elevationOverlayColor;
    }

    public int getThemeSurfaceColor() {
        return this.colorSurface;
    }

    public float getParentAbsoluteElevation(View view) {
        return ViewUtils.getParentAbsoluteElevation(view);
    }

    private boolean isThemeSurfaceColor(int i) {
        return ColorUtils.setAlphaComponent(i, 255) == this.colorSurface;
    }
}
