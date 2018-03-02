package org.liberty.android.fantastischmemo.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.EditText;

import org.apache.commons.io.FileUtils;
import org.liberty.android.fantastischmemo.R;
import org.liberty.android.fantastischmemo.modules.PerActivity;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;

@PerActivity
public class DatabaseOperationDialogUtil extends GenericDatabaseDialogUtil {
    private static final String TAG = DatabaseOperationDialogUtil.class.getName();

    private Activity activity = super.getActivity();
    private AMFileUtil amFileUtil = super.getAmFileUtil();
    private RecentListUtil recentListUtil = super.getRecentListUtil();
    private DatabaseUtil databaseUtil;

    @Inject
    public DatabaseOperationDialogUtil(@NonNull final Activity activity,
                                       @NonNull final AMFileUtil amFileUtil,
                                       @NonNull final RecentListUtil recentListUtil,
                                       @NonNull final DatabaseUtil databaseUtil) {
        super(activity, amFileUtil, recentListUtil);
        this.databaseUtil = databaseUtil;
    }

    public Maybe<File> showCreateDbDialog(@NonNull final String directoryPath) {
        final EditText input = new EditText(activity);
        return Maybe.create(new MaybeOnSubscribe<File>() {
            @Override
            public void subscribe(final MaybeEmitter<File> emitter) throws Exception {
                new AlertDialog.Builder(activity)
                        .setTitle(activity.getString(R.string.fb_create_db))
                        .setMessage(activity.getString(R.string.fb_create_db_message))
                        .setView(input)
                        .setPositiveButton(activity.getString(R.string.ok_text), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String value = input.getText().toString();
                                if (!value.endsWith(".db")) {
                                    value += ".db";
                                }
                                File newDbFile = new File(directoryPath + "/" + value);
                                try {
                                    if (newDbFile.exists()) {
                                        amFileUtil.deleteFileWithBackup(newDbFile.getAbsolutePath());
                                    }
                                    databaseUtil.setupDatabase(newDbFile.toString());
                                    emitter.onSuccess(newDbFile);
                                } catch (IOException e) {
                                    Log.e(TAG, "Fail to create file", e);
                                }
                            }
                        })
                        .setNegativeButton(activity.getString(R.string.cancel_text), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                emitter.onComplete();
                            }
                        })
                        .setOnCancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialog) {
                                emitter.onComplete();
                            }
                        })
                        .create()
                        .show();
            }
        });
    }
}