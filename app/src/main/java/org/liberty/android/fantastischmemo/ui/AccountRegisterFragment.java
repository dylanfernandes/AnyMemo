package org.liberty.android.fantastischmemo.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.liberty.android.fantastischmemo.R;
import org.liberty.android.fantastischmemo.common.AnyMemoBaseDBOpenHelper;
import org.liberty.android.fantastischmemo.common.AnyMemoBaseDBOpenHelperManager;
import org.liberty.android.fantastischmemo.common.BaseDialogFragment;

/**
 * Created by Emily on 2018-03-25.
 */

public class AccountRegisterFragment extends BaseDialogFragment {

    private Activity mActivity;
    private EditText usernameInput;
    private EditText nameInput;
    private Button confirm;

    public static String EXTRA_DBPATH = "dbpath";
    private String dbPath;
    private AnyMemoBaseDBOpenHelper baseDBOpenHelper;

    public AccountRegisterFragment(){}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle args = this.getArguments();
        dbPath = args.getString(EXTRA_DBPATH);
        baseDBOpenHelper = AnyMemoBaseDBOpenHelperManager.getHelper(mActivity, dbPath);
        setStyle(DialogFragment.STYLE_NO_TITLE, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.account_register_dialog, container, false);
        usernameInput = (EditText)v.findViewById(R.id.account_register_username);
        nameInput = (EditText)v.findViewById(R.id.account_register_name);
        confirm = (Button)v.findViewById(R.id.button_confirm);
        return v;
    }
}
