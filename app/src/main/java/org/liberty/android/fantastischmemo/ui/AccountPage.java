package org.liberty.android.fantastischmemo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import org.liberty.android.fantastischmemo.R;
import org.liberty.android.fantastischmemo.common.BaseActivity;
import org.liberty.android.fantastischmemo.entity.User;
import org.liberty.android.fantastischmemo.entity.UserStatistics;


/**
 * Created by Emily on 2018-03-15.
 */

public class AccountPage extends BaseActivity{
    private TextView userID;

    User fakeUser = new User(123, "fakeName", "fakeUserName");
    UserStatistics fakeUserStat = new UserStatistics(17);

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_page_tab);
        userID = (TextView)findViewById(R.id.account_id);
        userID.setText("" + fakeUser.getId());
    };

}
