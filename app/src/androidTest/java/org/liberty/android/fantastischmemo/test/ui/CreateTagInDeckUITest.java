package org.liberty.android.fantastischmemo.test.ui;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.liberty.android.fantastischmemo.R;
import org.liberty.android.fantastischmemo.ui.TagsActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by dylanfernandes on 2018-03-05.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CreateTagInDeckUITest {
    @Rule
    public ActivityTestRule<TagsActivity> mActivityRule = new ActivityTestRule<>(TagsActivity.class);

    @Test
    public void createTagTest() {
        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Intent intent = new Intent(targetContext, TagsActivity.class);
        intent.putExtra("deckPath", "/sdcard/anymemo/french-body-parts.db");

        mActivityRule.launchActivity(intent);
        onView(withId(R.id.add_tag_fab)).perform(click());
        onView(withId(R.id.create_new_fab)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
    }
}
