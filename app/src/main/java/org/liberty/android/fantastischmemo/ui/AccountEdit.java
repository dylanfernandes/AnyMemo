package org.liberty.android.fantastischmemo.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import org.liberty.android.fantastischmemo.R;
import org.liberty.android.fantastischmemo.common.AnyMemoBaseDBOpenHelper;
import org.liberty.android.fantastischmemo.common.AnyMemoBaseDBOpenHelperManager;
import org.liberty.android.fantastischmemo.common.BaseActivity;
import org.liberty.android.fantastischmemo.dao.UserDao;

/**
 * Created by Olivier on 2018-03-15.
 */

public class AccountEdit extends BaseActivity {
    private EditText currentNameEntry;
    private EditText updateNameEntry;
    private Button updateButton;

    String currentName;

    private AnyMemoBaseDBOpenHelper helper;
    private UserDao userdao;

    private InitTask initTask;

    protected String dbPath = null;
    public static String EXTRA_DBPATH = "dbpath";


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_account_layout);
        initTask = new InitTask();
        initTask.execute();
        setUpdateButtonListener();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AnyMemoBaseDBOpenHelperManager.releaseHelper(helper);
    }

    private void setUpdateButtonListener() {
        updateButton = (Button) findViewById(R.id.update_profile_button);
        updateButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                new AlertDialog.Builder(AccountEdit.this)
                        .setMessage(R.string.item_update_warning)
                        .setPositiveButton(R.string.yes_text, new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface  d, int which){
                                UpdateTask updatetask = new UpdateTask();
                                updatetask.execute();
                            }
                        }).setTitle(R.string.warning_text)

                        .setNegativeButton(R.string.cancel_text, null)
                        .show();
            }
        });
    }

    private class InitTask extends AsyncTask<Void, Void, Void> {
        private ProgressDialog progressDialog;

        @Override
        public void onPreExecute() {
            setTitle(R.string.memo_edit_dialog_title);
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                dbPath = extras.getString(EXTRA_DBPATH);
            }

            progressDialog = new ProgressDialog(AccountEdit.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setTitle(getString(R.string.loading_please_wait));
            progressDialog.setMessage(getString(R.string.loading_database));
            progressDialog.setCancelable(false);
            progressDialog.show();
            assert dbPath != null : "dbPath shouldn't be null";
        }

        @Override
        public Void doInBackground(Void... params) {
            helper = AnyMemoBaseDBOpenHelperManager.getHelper(AccountEdit.this, dbPath);

            userdao = helper.getUserDao();
            
            return null;
        }

        @Override
        public void onPostExecute(Void result){
            currentNameEntry = (EditText)findViewById(R.id.edit_profile_current_name_entry);
            updateNameEntry = (EditText)findViewById(R.id.edit_profile_update_name_entry);

            currentName = getIntent().getStringExtra("CURRENT_USER_NAME");
            currentNameEntry.setText(userdao.createOrReturn(currentName).getName());
            currentNameEntry.setEnabled(false);

            progressDialog.dismiss();
        }
    }

    private class UpdateTask extends AsyncTask<Void, Void, Void> {
        private ProgressDialog progressDialog;
        private String updatedName;

        @Override
        public void onPreExecute() {
            progressDialog = new ProgressDialog(AccountEdit.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setTitle(getString(R.string.loading_please_wait));
            progressDialog.setMessage(getString(R.string.loading_database));
            progressDialog.setCancelable(false);
            progressDialog.show();

            updatedName = updateNameEntry.getText().toString();
        }

        @Override
        public Void doInBackground(Void... params) {
            //Update user name
            userdao.editName("","");

            return null;
        }

        @Override
        public void onPostExecute(Void result){
            currentNameEntry.setEnabled(true);
            currentNameEntry.setText(userdao.createOrReturn(currentName).getName());
        }
    }

}
