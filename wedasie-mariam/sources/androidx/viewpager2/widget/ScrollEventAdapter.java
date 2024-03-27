package androidx.viewpager2.widget;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import java.util.Locale;

final class ScrollEventAdapter extends RecyclerView.OnScrollListener {
    private static final int NO_POSITION = -1;
    private static final int STATE_IDLE = 0;
    private static final int STATE_IN_PROGRESS_FAKE_DRAG = 4;
    private static final int STATE_IN_PROGRESS_IMMEDIATE_SCROLL = 3;
    private static final int STATE_IN_PROGRESS_MANUAL_DRAG = 1;
    private static final int STATE_IN_PROGRESS_SMOOTH_SCROLL = 2;
    private static final ViewGroup.MarginLayoutParams ZERO_MARGIN_LAYOUT_PARAMS = new ViewGroup.MarginLayoutParams(-1, -1);
    private int mAdapterState;
    private ViewPager2.OnPageChangeCallback mCallback;
    private boolean mDataSetChangeHappened;
    private boolean mDispatchSelected;
    private int mDragStartPosition;
    private boolean mFakeDragging;
    private final LinearLayoutManager mLayoutManager;
    private boolean mScrollHappened;
    private int mScrollState;
    private ScrollEventValues mScrollValues = new ScrollEventValues();
    private int mTarget;
    private final ViewPager2 mViewPager;

    static {
        ZERO_MARGIN_LAYOUT_PARAMS.setMargins(0, 0, 0, 0);
    }

    ScrollEventAdapter(ViewPager2 viewPager2) {
        this.mLayoutManager = viewPager2.mLayoutManager;
        this.mViewPager = viewPager2;
        resetState();
    }

    private void resetState() {
        this.mAdapterState = 0;
        this.mScrollState = 0;
        this.mScrollValues.reset();
        this.mDragStartPosition = -1;
        this.mTarget = -1;
        this.mDispatchSelected = false;
        this.mScrollHappened = false;
        this.mFakeDragging = false;
        this.mDataSetChangeHappened = false;
    }

    public void onScrollStateChanged(RecyclerView recyclerView, int i) {
        boolean z = true;
        if (!(this.mAdapterState == 1 && this.mScrollState == 1) && i == 1) {
            startDrag(false);
        } else if (!isInAnyDraggingState() || i != 2) {
            if (isInAnyDraggingState() && i == 0) {
                updateScrollEventValues();
                if (!this.mScrollHappened) {
                    if (this.mScrollValues.mPosition != -1) {
                        dispatchScrolled(this.mScrollValues.mPosition, 0.0f, 0);
                    }
                } else if (this.mScrollValues.mOffsetPx != 0) {
                    z = false;
                } else if (this.mDragStartPosition != this.mScrollValues.mPosition) {
                    dispatchSelected(this.mScrollValues.mPosition);
                }
                if (z) {
                    dispatchStateChanged(0);
                    resetState();
                }
            }
            if (this.mAdapterState == 2 && i == 0 && this.mDataSetChangeHappened) {
                updateScrollEventValues();
                if (this.mScrollValues.mOffsetPx == 0) {
                    if (this.mTarget != this.mScrollValues.mPosition) {
                        dispatchSelected(this.mScrollValues.mPosition == -1 ? 0 : this.mScrollValues.mPosition);
                    }
                    dispatchStateChanged(0);
                    resetState();
                }
            }
        } else if (this.mScrollHappened) {
            dispatchStateChanged(2);
            this.mDispatchSelected = true;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001d, code lost:
        if ((r5 < 0) == r3.mViewPager.isRtl()) goto L_0x0022;
     */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x002b  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onScrolled(androidx.recyclerview.widget.RecyclerView r4, int r5, int r6) {
        /*
            r3 = this;
            r4 = 1
            r3.mScrollHappened = r4
            r3.updateScrollEventValues()
            boolean r0 = r3.mDispatchSelected
            r1 = -1
            r2 = 0
            if (r0 == 0) goto L_0x0041
            r3.mDispatchSelected = r2
            if (r6 > 0) goto L_0x0022
            if (r6 != 0) goto L_0x0020
            if (r5 >= 0) goto L_0x0016
            r5 = 1
            goto L_0x0017
        L_0x0016:
            r5 = 0
        L_0x0017:
            androidx.viewpager2.widget.ViewPager2 r6 = r3.mViewPager
            boolean r6 = r6.isRtl()
            if (r5 != r6) goto L_0x0020
            goto L_0x0022
        L_0x0020:
            r5 = 0
            goto L_0x0023
        L_0x0022:
            r5 = 1
        L_0x0023:
            if (r5 == 0) goto L_0x0031
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r5 = r3.mScrollValues
            int r5 = r5.mOffsetPx
            if (r5 == 0) goto L_0x0031
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r5 = r3.mScrollValues
            int r5 = r5.mPosition
            int r5 = r5 + r4
            goto L_0x0035
        L_0x0031:
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r5 = r3.mScrollValues
            int r5 = r5.mPosition
        L_0x0035:
            r3.mTarget = r5
            int r5 = r3.mDragStartPosition
            int r6 = r3.mTarget
            if (r5 == r6) goto L_0x004f
            r3.dispatchSelected(r6)
            goto L_0x004f
        L_0x0041:
            int r5 = r3.mAdapterState
            if (r5 != 0) goto L_0x004f
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r5 = r3.mScrollValues
            int r5 = r5.mPosition
            if (r5 != r1) goto L_0x004c
            r5 = 0
        L_0x004c:
            r3.dispatchSelected(r5)
        L_0x004f:
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r5 = r3.mScrollValues
            int r5 = r5.mPosition
            if (r5 != r1) goto L_0x0057
            r5 = 0
            goto L_0x005b
        L_0x0057:
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r5 = r3.mScrollValues
            int r5 = r5.mPosition
        L_0x005b:
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r6 = r3.mScrollValues
            float r6 = r6.mOffset
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r0 = r3.mScrollValues
            int r0 = r0.mOffsetPx
            r3.dispatchScrolled(r5, r6, r0)
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r5 = r3.mScrollValues
            int r5 = r5.mPosition
            int r6 = r3.mTarget
            if (r5 == r6) goto L_0x0070
            if (r6 != r1) goto L_0x0080
        L_0x0070:
            androidx.viewpager2.widget.ScrollEventAdapter$ScrollEventValues r5 = r3.mScrollValues
            int r5 = r5.mOffsetPx
            if (r5 != 0) goto L_0x0080
            int r5 = r3.mScrollState
            if (r5 == r4) goto L_0x0080
            r3.dispatchStateChanged(r2)
            r3.resetState()
        L_0x0080:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager2.widget.ScrollEventAdapter.onScrolled(androidx.recyclerview.widget.RecyclerView, int, int):void");
    }

    private void updateScrollEventValues() {
        int i;
        int i2;
        int i3;
        float f;
        ScrollEventValues scrollEventValues = this.mScrollValues;
        scrollEventValues.mPosition = this.mLayoutManager.findFirstVisibleItemPosition();
        if (scrollEventValues.mPosition == -1) {
            scrollEventValues.reset();
            return;
        }
        View findViewByPosition = this.mLayoutManager.findViewByPosition(scrollEventValues.mPosition);
        if (findViewByPosition == null) {
            scrollEventValues.reset();
            return;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = findViewByPosition.getLayoutParams() instanceof ViewGroup.MarginLayoutParams ? (ViewGroup.MarginLayoutParams) findViewByPosition.getLayoutParams() : ZERO_MARGIN_LAYOUT_PARAMS;
        if (this.mLayoutManager.getOrientation() == 0) {
            i = findViewByPosition.getWidth() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
            if (!this.mViewPager.isRtl()) {
                i3 = findViewByPosition.getLeft();
                i2 = marginLayoutParams.leftMargin;
            } else {
                i3 = i - findViewByPosition.getRight();
                i2 = marginLayoutParams.rightMargin;
            }
        } else {
            i = findViewByPosition.getHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
            i3 = findViewByPosition.getTop();
            i2 = marginLayoutParams.topMargin;
        }
        scrollEventValues.mOffsetPx = -(i3 - i2);
        if (scrollEventValues.mOffsetPx >= 0) {
            if (i == 0) {
                f = 0.0f;
            } else {
                f = ((float) scrollEventValues.mOffsetPx) / ((float) i);
            }
            scrollEventValues.mOffset = f;
        } else if (new AnimateLayoutChangeDetector(this.mLayoutManager).mayHaveInterferingAnimations()) {
            throw new IllegalStateException("Page(s) contain a ViewGroup with a LayoutTransition (or animateLayoutChanges=\"true\"), which interferes with the scrolling animation. Make sure to call getLayoutTransition().setAnimateParentHierarchy(false) on all ViewGroups with a LayoutTransition before an animation is started.");
        } else {
            throw new IllegalStateException(String.format(Locale.US, "Page can only be offset by a positive amount, not by %d", new Object[]{Integer.valueOf(scrollEventValues.mOffsetPx)}));
        }
    }

    private void startDrag(boolean z) {
        this.mFakeDragging = z;
        this.mAdapterState = z ? 4 : 1;
        int i = this.mTarget;
        if (i != -1) {
            this.mDragStartPosition = i;
            this.mTarget = -1;
        } else if (this.mDragStartPosition == -1) {
            this.mDragStartPosition = getPosition();
        }
        dispatchStateChanged(1);
    }

    /* access modifiers changed from: package-private */
    public void notifyDataSetChangeHappened() {
        this.mDataSetChangeHappened = true;
    }

    /* access modifiers changed from: package-private */
    public void notifyProgrammaticScroll(int i, boolean z) {
        this.mAdapterState = z ? 2 : 3;
        boolean z2 = false;
        this.mFakeDragging = false;
        if (this.mTarget != i) {
            z2 = true;
        }
        this.mTarget = i;
        dispatchStateChanged(2);
        if (z2) {
            dispatchSelected(i);
        }
    }

    /* access modifiers changed from: package-private */
    public void notifyBeginFakeDrag() {
        this.mAdapterState = 4;
        startDrag(true);
    }

    /* access modifiers changed from: package-private */
    public void notifyEndFakeDrag() {
        if (!isDragging() || this.mFakeDragging) {
            this.mFakeDragging = false;
            updateScrollEventValues();
            if (this.mScrollValues.mOffsetPx == 0) {
                if (this.mScrollValues.mPosition != this.mDragStartPosition) {
                    dispatchSelected(this.mScrollValues.mPosition);
                }
                dispatchStateChanged(0);
                resetState();
                return;
            }
            dispatchStateChanged(2);
        }
    }

    /* access modifiers changed from: package-private */
    public void setOnPageChangeCallback(ViewPager2.OnPageChangeCallback onPageChangeCallback) {
        this.mCallback = onPageChangeCallback;
    }

    /* access modifiers changed from: package-private */
    public int getScrollState() {
        return this.mScrollState;
    }

    /* access modifiers changed from: package-private */
    public boolean isIdle() {
        return this.mScrollState == 0;
    }

    /* access modifiers changed from: package-private */
    public boolean isDragging() {
        return this.mScrollState == 1;
    }

    /* access modifiers changed from: package-private */
    public boolean isFakeDragging() {
        return this.mFakeDragging;
    }

    private boolean isInAnyDraggingState() {
        int i = this.mAdapterState;
        return i == 1 || i == 4;
    }

    /* access modifiers changed from: package-private */
    public double getRelativeScrollPosition() {
        updateScrollEventValues();
        double d = (double) this.mScrollValues.mPosition;
        double d2 = (double) this.mScrollValues.mOffset;
        Double.isNaN(d);
        Double.isNaN(d2);
        return d + d2;
    }

    private void dispatchStateChanged(int i) {
        if ((this.mAdapterState != 3 || this.mScrollState != 0) && this.mScrollState != i) {
            this.mScrollState = i;
            ViewPager2.OnPageChangeCallback onPageChangeCallback = this.mCallback;
            if (onPageChangeCallback != null) {
                onPageChangeCallback.onPageScrollStateChanged(i);
            }
        }
    }

    private void dispatchSelected(int i) {
        ViewPager2.OnPageChangeCallback onPageChangeCallback = this.mCallback;
        if (onPageChangeCallback != null) {
            onPageChangeCallback.onPageSelected(i);
        }
    }

    private void dispatchScrolled(int i, float f, int i2) {
        ViewPager2.OnPageChangeCallback onPageChangeCallback = this.mCallback;
        if (onPageChangeCallback != null) {
            onPageChangeCallback.onPageScrolled(i, f, i2);
        }
    }

    private int getPosition() {
        return this.mLayoutManager.findFirstVisibleItemPosition();
    }

    private static final class ScrollEventValues {
        float mOffset;
        int mOffsetPx;
        int mPosition;

        ScrollEventValues() {
        }

        /* access modifiers changed from: package-private */
        public void reset() {
            this.mPosition = -1;
            this.mOffset = 0.0f;
            this.mOffsetPx = 0;
        }
    }
}
