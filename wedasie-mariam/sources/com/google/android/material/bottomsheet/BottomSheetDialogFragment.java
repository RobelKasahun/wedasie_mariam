package com.google.android.material.bottomsheet;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatDialogFragment;
import com.google.android.material.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class BottomSheetDialogFragment extends AppCompatDialogFragment {
    private boolean waitingForDismissAllowingStateLoss;

    public Dialog onCreateDialog(Bundle bundle) {
        return new BottomSheetDialog(getContext(), getTheme());
    }

    public void dismiss() {
        if (!tryDismissWithAnimation(false)) {
            super.dismiss();
        }
    }

    public void dismissAllowingStateLoss() {
        if (!tryDismissWithAnimation(true)) {
            super.dismissAllowingStateLoss();
        }
    }

    public BottomSheetDialog getDialog() {
        return (BottomSheetDialog) super.getDialog();
    }

    private boolean tryDismissWithAnimation(boolean z) {
        BottomSheetBehavior<View> bottomSheetBehavior = getBottomSheetBehavior();
        if (bottomSheetBehavior == null || !bottomSheetBehavior.isHideable() || !getDialog().getDismissWithAnimation()) {
            return false;
        }
        dismissWithAnimation(bottomSheetBehavior, z);
        return true;
    }

    private BottomSheetBehavior<View> getBottomSheetBehavior() {
        View findViewById;
        BottomSheetDialog dialog = getDialog();
        if (dialog == null || (findViewById = dialog.findViewById(R.id.design_bottom_sheet)) == null) {
            return null;
        }
        return BottomSheetBehavior.from(findViewById);
    }

    private void dismissWithAnimation(BottomSheetBehavior<View> bottomSheetBehavior, boolean z) {
        this.waitingForDismissAllowingStateLoss = z;
        if (bottomSheetBehavior.getState() == 5) {
            dismissAfterAnimation();
            return;
        }
        if (!(bottomSheetBehavior.getBottomSheetCallback() instanceof BottomSheetDismissCallback)) {
            bottomSheetBehavior.setBottomSheetCallback(new BottomSheetDismissCallback(bottomSheetBehavior.getBottomSheetCallback()));
        }
        bottomSheetBehavior.setState(5);
    }

    /* access modifiers changed from: private */
    public void dismissAfterAnimation() {
        if (this.waitingForDismissAllowingStateLoss) {
            super.dismissAllowingStateLoss();
        } else {
            super.dismiss();
        }
    }

    private class BottomSheetDismissCallback extends BottomSheetBehavior.BottomSheetCallback {
        private final BottomSheetBehavior.BottomSheetCallback originalCallback;

        public BottomSheetDismissCallback(BottomSheetBehavior.BottomSheetCallback bottomSheetCallback) {
            this.originalCallback = bottomSheetCallback;
        }

        public void onStateChanged(View view, int i) {
            if (i == 5) {
                BottomSheetDialogFragment.this.dismissAfterAnimation();
            }
            this.originalCallback.onStateChanged(view, i);
        }

        public void onSlide(View view, float f) {
            this.originalCallback.onSlide(view, f);
        }
    }
}
