package org.liberty.android.fantastischmemo.ui;

import android.os.Bundle;
import android.widget.TextView;

import org.liberty.android.fantastischmemo.R;
import org.liberty.android.fantastischmemo.common.BaseActivity;
import org.liberty.android.fantastischmemo.entity.User;
import org.liberty.android.fantastischmemo.entity.UserStatistics;


/**
 * Created by Emily on 2018-03-15.
 */

public class AccountPage extends BaseActivity{
    private TextView user_Name;
    private TextView username;
    private TextView longest_streak;
    private TextView current_streak;

    User fakeUser = new User("Thomas", "blue_fish");
    UserStatistics fakeUserStat = new UserStatistics(17, 3);

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_page_tab);
        user_Name = (TextView)findViewById(R.id.account_user_name);
        user_Name.setText(fakeUser.getName());
        username = (TextView)findViewById(R.id.account_username);
        username.setText(fakeUser.getUsername());
        longest_streak = (TextView)findViewById(R.id.account_longest_streak);
        longest_streak.setText(String.valueOf(fakeUserStat.getLongestStreak()));
        current_streak = (TextView)findViewById(R.id.account_current_streak);
        current_streak.setText(String.valueOf(fakeUserStat.getStreak()));

    };

}
