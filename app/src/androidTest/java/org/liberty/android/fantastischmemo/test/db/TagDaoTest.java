package org.liberty.android.fantastischmemo.test.db;

import android.support.test.filters.SmallTest;

import org.junit.Test;
import org.liberty.android.fantastischmemo.dao.TagDao;
import org.liberty.android.fantastischmemo.entity.Tag;
import org.liberty.android.fantastischmemo.test.AbstractExistingDBTest;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Adam on 2018-02-14.
 */

public class TagDaoTest extends AbstractExistingDBTest {

    @SmallTest
    @Test
    public void testAddTags() throws Exception {
        TagDao tagDao = helper.getTagDao();
        List<Tag> tags = tagDao.queryForAll();
        int initsize = tags.size();
        Tag t1 = tagDao.createOrReturn("t1");
        assertNotNull(t1);
        assertEquals(t1.getName(), "t1");
        tags = tagDao.queryForAll();
        assertEquals(tags.size(), initsize + 1);
        Tag t2 = tagDao.createOrReturn("t1");
        assertEquals(t2.getName(), "t1");
        assertEquals(tags.size(), initsize + 1);
    }
}
