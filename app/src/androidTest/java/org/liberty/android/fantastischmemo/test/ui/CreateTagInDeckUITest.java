package org.liberty.android.fantastischmemo.test.ui;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.liberty.android.fantastischmemo.R;
import org.liberty.android.fantastischmemo.dao.TagDao;
import org.liberty.android.fantastischmemo.entity.DeckMap;
import org.liberty.android.fantastischmemo.entity.DeckMock;
import org.liberty.android.fantastischmemo.entity.Tag;
import org.liberty.android.fantastischmemo.integrationtest.TestHelper;
import org.liberty.android.fantastischmemo.test.AbstractExistingDBTest;
import org.liberty.android.fantastischmemo.ui.TagsActivity;

import java.sql.SQLException;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withTagValue;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

/**
 * Created by dylanfernandes on 2018-03-05.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CreateTagInDeckUITest extends AbstractExistingDBTest {
    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(TagsActivity.class,
            true,   // initialTouchMode
            false); // Lazy launching

    @Test
    public void testCreateNewTag() throws InterruptedException {
        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        TagDao tagDao = helper.getTagDao();
        DeckMap.getInstance().findOrCreate(new DeckMock("french-body-parts.db", TestHelper.SAMPLE_DB_PATH));
        Intent intent = new Intent(targetContext, TagsActivity.class);
        intent.putExtra("deckPath", "/sdcard/anymemo/french-body-parts.db");
        mActivityRule.launchActivity(intent);

        onView(withId(R.id.add_tag_fab)).perform(click());
        Thread.sleep(1500);
        onView(withId(R.id.create_new_fab)).perform(click());
        onView(allOf(withTagValue(is((Object) "create_tag_input")), isDisplayed())).perform(typeText("testTag"), closeSoftKeyboard());
        onView(allOf(withText("Create"), isDisplayed())).perform(click());

        onView(allOf(withText("testTag"), isDisplayed())).check(matches(withText("testTag")));

        int countTagByName = 0;
        try {
            countTagByName = (int) tagDao.queryBuilder().where().eq("name", "testTag").countOf();
        } catch (SQLException e) {
            fail("Tag failed to be saved to database.");
        }
        assertTrue(countTagByName != 0);
    }
}
