package org.liberty.android.fantastischmemo.utils;

import android.content.Context;

import org.liberty.android.fantastischmemo.common.AMEnv;
import org.liberty.android.fantastischmemo.common.AnyMemoBaseDBOpenHelper;
import org.liberty.android.fantastischmemo.common.AnyMemoBaseDBOpenHelperManager;
import org.liberty.android.fantastischmemo.dao.DeckDao;
import org.liberty.android.fantastischmemo.entity.Deck;
import org.liberty.android.fantastischmemo.modules.ForApplication;
import org.liberty.android.fantastischmemo.modules.PerApplication;

import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

/**
 * Created by Olivier on 2018-03-01.
 */

@PerApplication
public class BaseDatabaseUtil {

    private Context mContext;

    @Inject
    public BaseDatabaseUtil(@ForApplication Context context) {
        mContext = context;
    }

    public void setupDatabase(String dbPath) {
        AnyMemoBaseDBOpenHelper helper = null;
        String dbName = new File(dbPath).getName();
        try {
            helper = AnyMemoBaseDBOpenHelperManager.getHelper(mContext, dbPath);
            helper.getWritableDatabase(); //If new database, calls onCreate method to make new database. If database already exists, calls onUpgrade method instead.
            DeckDao deckDao = helper.getDeckDao();
            Deck deck = new Deck();
            deck.setName(dbName);
            deck.setDbPath(dbPath);
            deckDao.create(deck);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (helper != null) {
                AnyMemoBaseDBOpenHelperManager.releaseHelper(helper);
            }
        }
    }

}








