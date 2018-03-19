package org.liberty.android.fantastischmemo.test.db;

import android.support.test.filters.SmallTest;

import org.junit.Test;
import org.liberty.android.fantastischmemo.dao.CardDao;
import org.liberty.android.fantastischmemo.entity.Card;
import org.liberty.android.fantastischmemo.test.AbstractExistingBaseDBTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class NewDbTest extends AbstractExistingBaseDBTest {

    @SmallTest
    @Test
    public void testCreateFirstCardWithCorrectOrdinal() throws Exception {
        CardDao cardDao = helper.getCardDao();
        // Create card has null ordinal, append to the end
        Card nc = new Card();
        assertNull(nc.getOrdinal());
        cardDao.create(nc);
        //queries frech-body-parts for last ordinal: last card is 28
        assertEquals(29, (int)nc.getOrdinal());
    }
}
