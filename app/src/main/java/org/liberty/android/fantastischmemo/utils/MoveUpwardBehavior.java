package org.liberty.android.fantastischmemo.utils;

/**
 * Created by dylanfernandes on 2018-02-28.
 */

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

public class MoveUpwardBehavior extends CoordinatorLayout.Behavior<View> {
    public MoveUpwardBehavior() {
        super();
    }

    public MoveUpwardBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof Snackbar.SnackbarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        float translationY = Math.min(0, ViewCompat.getTranslationY(dependency) - dependency.getHeight());
        ViewCompat.setTranslationY(child, translationY);
        return true;
    }

    public void onDependentViewRemoved(CoordinatorLayout parent, View child, View dependency) {
        ViewCompat.animate(child).translationY(0).start();
    }
}
