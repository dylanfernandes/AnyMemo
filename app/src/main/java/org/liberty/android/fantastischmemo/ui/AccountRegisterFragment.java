package org.liberty.android.fantastischmemo.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.liberty.android.fantastischmemo.R;
import org.liberty.android.fantastischmemo.common.AMEnv;
import org.liberty.android.fantastischmemo.common.AnyMemoBaseDBOpenHelper;
import org.liberty.android.fantastischmemo.common.AnyMemoBaseDBOpenHelperManager;
import org.liberty.android.fantastischmemo.common.BaseDialogFragment;
import org.liberty.android.fantastischmemo.dao.UserDao;
import org.liberty.android.fantastischmemo.dao.UserStatisticsDao;
import org.liberty.android.fantastischmemo.entity.User;

/**
 * Created by Emily on 2018-03-25.
 */

public class AccountRegisterFragment extends BaseDialogFragment {

    private Activity mActivity;
    private EditText usernameInput;
    private EditText nameInput;
    private Button confirm;

    private AnyMemoBaseDBOpenHelper baseDBOpenHelper;
    private UserDao userDao;
    private UserStatisticsDao userStatDao;

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
        baseDBOpenHelper = AnyMemoBaseDBOpenHelperManager.getHelper();
        userDao = baseDBOpenHelper.getUserDao();
        userStatDao = baseDBOpenHelper.getUserStatisticsDao();
        setStyle(DialogFragment.STYLE_NO_TITLE, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.account_register_dialog, container, false);
        usernameInput = (EditText)v.findViewById(R.id.account_register_username);
        nameInput = (EditText)v.findViewById(R.id.account_register_name);
        confirm = (Button)v.findViewById(R.id.button_confirm);
        confirm.setOnClickListener(registerButtonOnClickListener);
        return v;
    }

    private View.OnClickListener registerButtonOnClickListener = new View.OnClickListener() {

        public void onClick(View v) {
            if(usernameInput.getText().toString().equals("") || nameInput.getText().toString().equals("")){
                AlertDialog alertDialog = new AlertDialog.Builder(mActivity).create();
                alertDialog.setTitle(getString(R.string.unable_to_register));
                alertDialog.setMessage(getString(R.string.empty_field));
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }else {
                User newAcc = userDao.createOrReturn(usernameInput.getText().toString());
                newAcc.setName(nameInput.getText().toString());
                userDao.update(newAcc);
                userStatDao.createOrReturn(newAcc);
                dismiss();
            }
        }
    };

}
