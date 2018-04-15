package org.liberty.android.fantastischmemo.common;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.DaoManager;

import org.apache.commons.io.FilenameUtils;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Olivier on 2018-03-01.
 */

public class AnyMemoBaseDBOpenHelperManager {


    private static final String TAG = AnyMemoBaseDBOpenHelperManager.class.getSimpleName();

    // Comparator for determining if two files are the same
    private static Comparator<String> filenameComparator = new Comparator<String>() {
        @Override
        public int compare(String lhs, String rhs) {
            if (FilenameUtils.equalsNormalizedOnSystem(lhs, rhs)) {
                return 0;
            } else {
                return lhs.compareTo(rhs);
            }
        }
    };

    private static volatile Map<String, WeakReference<AnyMemoBaseDBOpenHelper>> helpers = Collections.synchronizedMap(new TreeMap<String, WeakReference<AnyMemoBaseDBOpenHelper>>(filenameComparator));

    private static volatile Map<String, Integer> refCounts = Collections.synchronizedMap(new TreeMap<String, Integer>(filenameComparator));

    /* Used to synchronize different method, i. e. creating and releasing helper. */
    private static volatile ReentrantLock bigLock = new ReentrantLock();

    /**
     * Get a BaseDBOpenHelper with application context.
     */
    public static AnyMemoBaseDBOpenHelper getHelper() {
        return getHelper(AMApplication.getCurrentApplicationContext(), AMEnv.HIDDEN_DB_FOLDER_PATH + AMEnv.CENTRAL_DB_NAME);
    }

    /* Get a db open helper and return a cached one if it was called before for the same db */
    public static AnyMemoBaseDBOpenHelper getHelper(Context context, String dbpath) {
        bigLock.lock();
        try {
            assert dbpath != null : "dbpath should not be null";
            if (!helpers.containsKey(dbpath)) {
                Log.i(TAG, "Call get AnyMemoBaseDBOpenHelper for first time for db: " + dbpath);
                AnyMemoBaseDBOpenHelper helper = new AnyMemoBaseDBOpenHelper(context, dbpath);
                helpers.put(dbpath, new WeakReference<AnyMemoBaseDBOpenHelper>(helper));
                refCounts.put(dbpath, 1);
                return helpers.get(dbpath).get();
            } else {
                Log.i(TAG, "Call get AnyMemoBaseDBOpenHelper for " + dbpath + " again, return existing helper.");
                refCounts.put(dbpath, refCounts.get(dbpath) + 1);
                return helpers.get(dbpath).get();
            }
        } finally {
            bigLock.unlock();
        }
    }

    /* Release a db open helper if there is no open connection to it */
    public static void releaseHelper(AnyMemoBaseDBOpenHelper helper) {
        bigLock.lock();
        try {
            String dbpath = helper.getDbPath();
            if (!helpers.containsKey(dbpath)) {
                Log.w(TAG, "Release a wrong db path or release an already been released helper!");
                return;
            }

            Log.i(TAG, "Release AnyMemoBaseDBOpenHelper: " + dbpath + " Ref count: " + refCounts.get(dbpath));

            refCounts.put(dbpath, refCounts.get(dbpath) - 1);

            if (refCounts.get(dbpath) == 0) {
                helper.close();
                DaoManager.clearCache();
                DaoManager.clearDaoCache();
                helpers.remove(dbpath);
                Log.i(TAG, "All connection released. Close helper. DB: " + dbpath);
            }
        } finally {
            bigLock.unlock();
        }
    }

    public static void forceRelease(String dbpath) {
        bigLock.lock();
        try {
            if (!helpers.containsKey(dbpath)) {
                Log.w(TAG, "Force release a file that is not opened yet. Do nothing");
                return;
            }

            AnyMemoBaseDBOpenHelper helper = helpers.get(dbpath).get();

            Log.i(TAG, "force releasing " + dbpath + " It contains " + refCounts.get(dbpath) + " refs");
            // Weak reference can return null, so we must check here.
            if (helper != null) {
                helper.close();
            } else {
                Log.w(TAG, "forceRelease a path that has already been released by GC.");
            }
            DaoManager.clearCache();
            DaoManager.clearDaoCache();
            helpers.remove(dbpath);
            refCounts.get(dbpath);
            Log.i(TAG, "Force released a db file. DB: " + dbpath);
        } finally {
            bigLock.unlock();
        }

    }


}
