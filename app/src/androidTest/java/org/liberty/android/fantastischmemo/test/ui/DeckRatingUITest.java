package org.liberty.android.fantastischmemo.test.ui;


import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.liberty.android.fantastischmemo.ui.AnyMemo;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class DeckRatingUITest {

    @Rule
    public ActivityTestRule<AnyMemo> mActivityTestRule = new ActivityTestRule<>(AnyMemo.class);

    @Test
    public void deckRatingUITest() {
        ViewInteraction tabView = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(org.liberty.android.fantastischmemo.R.id.tabs),
                                0),
                        1),
                        isDisplayed()));
        tabView.perform(click());

        ViewInteraction viewPager = onView(
                allOf(withId(org.liberty.android.fantastischmemo.R.id.viewpager),
                        childAtPosition(
                                allOf(withId(org.liberty.android.fantastischmemo.R.id.main_content),
                                        childAtPosition(
                                                withId(org.liberty.android.fantastischmemo.R.id.drawer_layout),
                                                0)),
                                1),
                        isDisplayed()));

        ViewInteraction recyclerView = onView(
                allOf(withId(org.liberty.android.fantastischmemo.R.id.file_list),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)));
        recyclerView.perform(actionOnItemAtPosition(1, longClick()));

        DataInteraction appCompatTextView = onData(anything())
                .inAdapterView(allOf(withClassName(is("com.android.internal.app.AlertController$RecycleListView")),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)))
                .atPosition(1);
        appCompatTextView.perform(click());

        ViewInteraction editText = onView(
                allOf(withText("0.0"),
                        childAtPosition(
                                allOf(withId(android.R.id.custom),
                                        childAtPosition(
                                                withClassName(is("android.widget.FrameLayout")),
                                                0)),
                                0),
                        isDisplayed()));
        editText.perform(click());

        ViewInteraction editText2 = onView(
                allOf(withText("0.0"),
                        childAtPosition(
                                allOf(withId(android.R.id.custom),
                                        childAtPosition(
                                                withClassName(is("android.widget.FrameLayout")),
                                                0)),
                                0),
                        isDisplayed()));
        editText2.perform(replaceText("4.0"));

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button1), withText("Confirm Rating"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction tabView2 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(org.liberty.android.fantastischmemo.R.id.tabs),
                                0),
                        0),
                        isDisplayed()));
        tabView2.perform(click());

        ViewInteraction viewPager2 = onView(
                allOf(withId(org.liberty.android.fantastischmemo.R.id.viewpager),
                        childAtPosition(
                                allOf(withId(org.liberty.android.fantastischmemo.R.id.main_content),
                                        childAtPosition(
                                                withId(org.liberty.android.fantastischmemo.R.id.drawer_layout),
                                                0)),
                                1),
                        isDisplayed()));

        ViewInteraction ratingBar = onView(
                allOf(withId(org.liberty.android.fantastischmemo.R.id.deck_rating),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                2),
                        isDisplayed()));
        ratingBar.check(matches(isDisplayed()));

        ViewInteraction tabView3 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(org.liberty.android.fantastischmemo.R.id.tabs),
                                0),
                        1),
                        isDisplayed()));
        tabView3.perform(click());

        ViewInteraction viewPager3 = onView(
                allOf(withId(org.liberty.android.fantastischmemo.R.id.viewpager),
                        childAtPosition(
                                allOf(withId(org.liberty.android.fantastischmemo.R.id.main_content),
                                        childAtPosition(
                                                withId(org.liberty.android.fantastischmemo.R.id.drawer_layout),
                                                0)),
                                1),
                        isDisplayed()));

        ViewInteraction recyclerView2 = onView(
                allOf(withId(org.liberty.android.fantastischmemo.R.id.file_list),
                        childAtPosition(
                                withClassName(is("android.widget.LinearLayout")),
                                1)));
        recyclerView2.perform(actionOnItemAtPosition(1, longClick()));

        DataInteraction appCompatTextView2 = onData(anything())
                .inAdapterView(allOf(withClassName(is("com.android.internal.app.AlertController$RecycleListView")),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)))
                .atPosition(1);
        appCompatTextView2.perform(click());

        ViewInteraction editText3 = onView(
                allOf(withText("4.0"),
                        childAtPosition(
                                allOf(withId(android.R.id.custom),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                                0)),
                                0),
                        isDisplayed()));
        editText3.check(matches(withText("4.0")));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
