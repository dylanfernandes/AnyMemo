package org.liberty.android.fantastischmemo.integrationtest;

import android.content.ComponentName;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.liberty.android.fantastischmemo.R;
import org.liberty.android.fantastischmemo.ui.AnyMemo;
import org.liberty.android.fantastischmemo.ui.StudyActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

public class AnyMemoActivityTest {
    TestHelper testHelper;
    @Rule
    public ActivityTestRule<AnyMemo> mActivityRule = new IntentsTestRule<>(AnyMemo.class);

    @Before
    public void setUp() {
        testHelper = new TestHelper(InstrumentationRegistry.getInstrumentation());
        testHelper.clearPreferences();
        testHelper.markNotFirstTime();
    }

    @Test
    public void test() {}

    @After
    public void tearDown() throws Exception {
        Thread.sleep(2000);
    }
}
