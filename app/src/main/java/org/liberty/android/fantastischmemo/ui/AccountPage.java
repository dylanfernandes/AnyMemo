package org.liberty.android.fantastischmemo.ui;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;
import org.liberty.android.fantastischmemo.common.AMEnv;

import org.liberty.android.fantastischmemo.R;
import org.liberty.android.fantastischmemo.common.AnyMemoBaseDBOpenHelper;
import org.liberty.android.fantastischmemo.common.AnyMemoBaseDBOpenHelperManager;
import org.liberty.android.fantastischmemo.common.BaseActivity;
import org.liberty.android.fantastischmemo.dao.UserStatisticsDao;
import org.liberty.android.fantastischmemo.entity.User;
import org.liberty.android.fantastischmemo.entity.UserStatistics;
import org.liberty.android.fantastischmemo.dao.UserDao;

import java.util.Collection;

/**
 * Created by Emily on 2018-03-15.
 */

public class AccountPage extends BaseActivity{
    private TextView user_Name;
    private TextView username;
    private TextView longest_streak;
    private TextView current_streak;
    private ImageButton edit_button;

    private User user;
    private UserStatistics userStat;
    private UserDao userDao;
    private UserStatisticsDao userStatDao;
    private AnyMemoBaseDBOpenHelper baseHelper;
    private String dbPath = AMEnv.CENTRAL_DB_NAME;

    User fakeUser = new User("Thomas", "blue_fish");
    UserStatistics fakeUserStat = new UserStatistics(17, 3);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponents().inject(this);
        setContentView(R.layout.account_page_tab);


        baseHelper = AnyMemoBaseDBOpenHelperManager.getHelper(AccountPage.this, dbPath);
        userDao = baseHelper.getUserDao();
        userStatDao = baseHelper.getUserStatisticsDao();

        user = userDao.createOrReturn("DefaultUsername");
        userStat = userStatDao.createOrReturn(user);

        user_Name = (TextView)findViewById(R.id.account_user_name);
        user_Name.setText(user.getName());

        username = (TextView)findViewById(R.id.account_username);
        username.setText(user.getUsername());

        longest_streak = (TextView)findViewById(R.id.account_longest_streak);
        longest_streak.setText(String.valueOf(userStat.getLongestStreak()));

        current_streak = (TextView)findViewById(R.id.account_current_streak);
        current_streak.setText(String.valueOf(userStat.getStreak()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.account_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_account: {
                Intent intent = new Intent(AccountPage.this, AccountEdit.class);
                intent.putExtra("CURRENT_USER_NAME", user_Name.getText().toString());
                intent.putExtra("CURRENT_USERNAME", username.getText().toString());
                startActivity(intent);
                return true;
            }
            case R.id.delete_account:{
                userDao.delete(user);
                return true;
            }
        }
        return false;
    }

}

