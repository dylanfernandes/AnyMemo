package org.liberty.android.fantastischmemo.test;


import org.liberty.android.fantastischmemo.common.AnyMemoBaseDBOpenHelper;
import org.liberty.android.fantastischmemo.common.AnyMemoBaseDBOpenHelperManager;


import java.io.File;


/**
 * Created by Paul on 2018-03-16.
 */

public class AbstractExistingBaseDBTest extends AbstractExistingDBTest {


    protected AnyMemoBaseDBOpenHelper centralDbHelper;
    public static final String dbPath = "/sdcard/anymemo/centralDB/central.db";
    public static final String CENTRAL_DB_NAME= "central.db";

    @Override
    public void setUp() throws Exception {
        super.setUp();
        File newdbFile = new File(dbPath);
        newdbFile.delete();
        centralDbHelper = AnyMemoBaseDBOpenHelperManager.getHelper(getContext(), dbPath);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        AnyMemoBaseDBOpenHelperManager.releaseHelper(centralDbHelper);
        File newdbFile = new File(dbPath);
        newdbFile.delete();
    }


}
