package org.liberty.android.fantastischmemo.integrationtest;

import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.liberty.android.fantastischmemo.ui.AnyMemo;

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

    @Ignore
    @Test
    public void test() {}

    @After
    public void tearDown() throws Exception {
        Thread.sleep(2000);
    }
}
