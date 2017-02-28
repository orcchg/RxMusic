package com.orcchg.dev.maxa.rxmusic.presentation.ui.common.view.misc;

import android.content.Context;

import com.orcchg.dev.maxa.rxmusic.R;

public class ViewUtility {

    private static boolean sEnabledImageTransition = false;

    public static boolean isLargeScreen(Context context) {
        return context.getResources().getBoolean(R.bool.isLargeScreen);
    }

    public static void enableImageTransition(boolean isEnabled) {
        sEnabledImageTransition = isEnabled;
    }

    public static boolean isImageTransitionEnabled() {
        return sEnabledImageTransition;
    }
}
