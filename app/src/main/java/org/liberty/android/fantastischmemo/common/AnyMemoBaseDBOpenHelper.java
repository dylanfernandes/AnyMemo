package org.liberty.android.fantastischmemo.common;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.table.TableUtils;

import org.liberty.android.fantastischmemo.dao.DeckDao;
import org.liberty.android.fantastischmemo.dao.TagDao;
import org.liberty.android.fantastischmemo.entity.Deck;
import org.liberty.android.fantastischmemo.entity.Tag;

import java.sql.SQLException;

/**
 * Created by Olivier on 2018-03-01.
 */

public class AnyMemoBaseDBOpenHelper extends OrmLiteSqliteOpenHelper {

    private final String TAG = getClass().getSimpleName();

    private static final int CURRENT_VERSION = 2;

    private final String dbPath;

    private DeckDao deckDao = null;

    private TagDao tagDao = null;

    private boolean isReleased = false;

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        Log.v(TAG, "Now we are creating a new database!");
        Log.i(TAG, "Newly created db version: " + database.getVersion());

        try {
            TableUtils.createTable(connectionSource, Deck.class);
            TableUtils.createTable(connectionSource, Tag.class);

        } catch (SQLException e) {
            throw new RuntimeException("Database creation error: " + e.toString());
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        Log.v(TAG, "Old version" + oldVersion + " new version: " + newVersion);
        // Update possible card with null category field
        if (oldVersion <= 2) {
            try {
                database.execSQL("alter table decks add column dbPath VARCHAR");
                TableUtils.createTable(connectionSource, Tag.class);
            } catch (SQLException e) {
                Log.e(TAG, "Upgrading failed, the tags table might already exist.", e);
            } finally {
                oldVersion = 2;
            }
        }
        database.setVersion(oldVersion);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, String.format("Downgrading database from version %1$d to %2$d", oldVersion, newVersion));
    }

    /**
     * Do not call this method directly, use AnyMemoBaseDBOpenHelperManager instead.
     */
    @Override
    public void close() {
        isReleased = true;
        try {
            DatabaseConnection connection = getConnectionSource().getReadWriteConnection();
            getConnectionSource().releaseConnection(connection);
        } catch (SQLException e) {
            Log.e(TAG, "Error releasing the connection.", e);
        }
        super.close();
    }

    public synchronized DeckDao getDeckDao() {
        try {
            if (deckDao == null) {
                deckDao = getDao(Deck.class);
            }
            return deckDao;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public synchronized TagDao getTagDao() {
        try {
            if (tagDao == null) {
                tagDao = getDao(Tag.class);
            }
            return tagDao;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * Override the finalize in case the helper is not release.
     */
    @Override
    public void finalize() throws Throwable {
        super.finalize();
        // If the finalize kicked in before the db is released.
        // force release the helper!
        // This is usually a bug in program.
        if (!isReleased) {
            Log.w(TAG, "AnyMemoBaseDBOpenHelper for db " + dbPath + " is not released before being GCed. This class must be explicitly released! Force releasing now.");
            AnyMemoBaseDBOpenHelperManager.forceRelease(dbPath);
        }
    }

    /* Package private constructor used in Manager. */
    AnyMemoBaseDBOpenHelper(Context context, String dbpath) {
        // R.raw.ormlite_config is used to accelerate the DAO creation.
        super(context, dbpath, null, CURRENT_VERSION);
        this.dbPath = dbpath;
    }

    /* Package private getDbPath used in Manager. */
    String getDbPath() {
        return dbPath;
    }

}


