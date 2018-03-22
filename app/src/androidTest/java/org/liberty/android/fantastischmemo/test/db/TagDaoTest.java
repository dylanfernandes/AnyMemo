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

    @Test
    public void testAddTags() throws Exception {
        TagDao tagDao = helper.getTagDao();
        List<Tag> tags = tagDao.queryForAll();
        int initsize = tags.size();
        Tag t1 = tagDao.createOrReturn("t1");
        assertNotNull(t1);
        assertEquals(t1.getName(), "t1");
    }

    @Test
    public void testAddDuplicateTags() throws Exception {
        TagDao tagDao = helper.getTagDao();
        List<Tag> tags = tagDao.queryForAll();
        int initsize = tags.size();
        Tag t1 = tagDao.createOrReturn("t1");
        Tag t2 = tagDao.createOrReturn("t2");
        tags = tagDao.queryForAll();
        assertEquals("t1",tags.get(0).getName());
        assertEquals("t2",tags.get(1).getName());
        Tag t3 = tagDao.createOrReturn("t1");
        tags = tagDao.queryForAll();
        assertEquals("t1",tags.get(0).getName());
        assertEquals("t2",tags.get(1).getName());
        assertEquals(tags.size(), initsize + 2);
    }

    @Test
    public void testDeleteTags() throws Exception {
        TagDao tagDao = helper.getTagDao();
        List<Tag> tags = tagDao.queryForAll();
        Tag t1 = tagDao.createOrReturn("t1");
        Tag t2 = tagDao.createOrReturn("t2");
        int initsize = tags.size();
        tagDao.delete(t1);
        tags = tagDao.queryForAll();
        assertEquals(tags.size(), initsize+1);
    }
    
    @Test
    public void testTagMaintainOrdinal() throws Exception {
        TagDao tagDao = helper.getTagDao();
        List<Tag> tags = tagDao.queryForAll();
        Tag t1 = tagDao.createOrReturn("t1");
        Tag t2 = tagDao.createOrReturn("t2");
        Tag t3 = tagDao.createOrReturn("t3");
        tags = tagDao.queryForAll();
        assertEquals("t1",tags.get(0).getName());
        assertEquals("t2",tags.get(1).getName());
        assertEquals("t3",tags.get(2).getName());
        tagDao.delete(t2);
        tags = tagDao.queryForAll();
        assertEquals("t1",tags.get(0).getName());
        assertEquals("t3",tags.get(1).getName());

    }
}
