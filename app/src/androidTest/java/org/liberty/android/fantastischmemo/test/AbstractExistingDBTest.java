package org.liberty.android.fantastischmemo.test;

import android.content.Context;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.liberty.android.fantastischmemo.common.AMEnv;
import org.liberty.android.fantastischmemo.common.AnyMemoBaseDBOpenHelper;
import org.liberty.android.fantastischmemo.common.AnyMemoBaseDBOpenHelperManager;
import org.liberty.android.fantastischmemo.common.AnyMemoDBOpenHelper;
import org.liberty.android.fantastischmemo.common.AnyMemoDBOpenHelperManager;
import org.liberty.android.fantastischmemo.integrationtest.TestHelper;

import java.io.File;
import java.io.InputStream;

public class AbstractExistingDBTest extends BaseTest {

    protected AnyMemoDBOpenHelper helper;
    protected AnyMemoBaseDBOpenHelper centralDbHelper;

    @Before
    public void setUp() throws Exception {
        Context testContext = getContext();
        InputStream in = testContext.getResources().getAssets().open(AMEnv.DEFAULT_DB_NAME);
        File outFile = new File(TestHelper.SAMPLE_DB_PATH);
        outFile.delete();

        FileUtils.copyInputStreamToFile(in, outFile);
        in.close();
        helper = AnyMemoDBOpenHelperManager.getHelper(testContext, TestHelper.SAMPLE_DB_PATH);
    }

    @After
    public void tearDown() throws Exception {
        AnyMemoDBOpenHelperManager.releaseHelper(helper);
        helper = null;
        File outFile = new File(TestHelper.SAMPLE_DB_PATH);
        outFile.delete();
    }


    @Before
    public void setUpCentralDbHelper() throws Exception {
        Context testContext = getContext();
        InputStream in = testContext.getResources().getAssets().open(AMEnv.DEFAULT_DB_NAME);
        File outFile = new File(TestHelper.SAMPLE_DB_PATH);
        outFile.delete();

        FileUtils.copyInputStreamToFile(in, outFile);
        in.close();
        centralDbHelper = AnyMemoBaseDBOpenHelperManager.getHelper(testContext, TestHelper.SAMPLE_DB_PATH);
    }

    @After
    public void centralTearDown() throws Exception {
        AnyMemoBaseDBOpenHelperManager.releaseHelper(centralDbHelper);
        centralDbHelper = null;
        File outFile = new File(TestHelper.SAMPLE_DB_PATH);
        outFile.delete();
    }

}
