package org.liberty.android.fantastischmemo.ui;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
    public static String EXTRA_DBPATH = "dbpath";

    User fakeUser = new User("Thomas", "blue_fish");
    UserStatistics fakeUserStat = new UserStatistics(17, 3);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_page_tab);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            dbPath = extras.getString(EXTRA_DBPATH);
        }

        baseHelper = AnyMemoBaseDBOpenHelperManager.getHelper(AccountPage.this, dbPath);
        userDao = baseHelper.getUserDao();
        userStatDao = baseHelper.getUserStatisticsDao();

        User defaultUser = userDao.createOrReturn("DefaultUsername");
        defaultUser.setName("DefaultName");
        defaultUser.setSurname("DefaultSurname");
        userDao.update(defaultUser);
        userStatDao.createOrReturn(defaultUser);

        user = userDao.queryForId(1);
        userStat = userStatDao.queryForId(1);

        user_Name = (TextView)findViewById(R.id.account_user_name);
        user_Name.setText(user.getName());

        username = (TextView)findViewById(R.id.account_username);
        username.setText(user.getUsername());

        longest_streak = (TextView)findViewById(R.id.account_longest_streak);
        longest_streak.setText(String.valueOf(userStat.getLongestStreak()));

        current_streak = (TextView)findViewById(R.id.account_current_streak);
        current_streak.setText(String.valueOf(userStat.getStreak()));

        edit_button = (ImageButton)findViewById(R.id.edit_account);
        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountPage.this, AccountEdit.class);
                intent.putExtra("CURRENT_USER_NAME",user_Name.getText().toString());
                startActivity(intent);}
        });
    }


}

