package org.liberty.android.fantastischmemo.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.liberty.android.fantastischmemo.R;
import org.liberty.android.fantastischmemo.common.AMEnv;
import org.liberty.android.fantastischmemo.common.AnyMemoBaseDBOpenHelper;
import org.liberty.android.fantastischmemo.common.AnyMemoBaseDBOpenHelperManager;
import org.liberty.android.fantastischmemo.common.BaseActivity;
import org.liberty.android.fantastischmemo.dao.AchievementPointDao;
import org.liberty.android.fantastischmemo.dao.AchievementTagPointsJoinDao;
import org.liberty.android.fantastischmemo.dao.DailyPointsDao;
import org.liberty.android.fantastischmemo.dao.DeckPointsDao;
import org.liberty.android.fantastischmemo.dao.TagPointsDao;
import org.liberty.android.fantastischmemo.dao.UserDao;
import org.liberty.android.fantastischmemo.dao.UserStatisticsDao;
import org.liberty.android.fantastischmemo.entity.AchievementPoint;
import org.liberty.android.fantastischmemo.entity.AchievementTagPointsJoin;
import org.liberty.android.fantastischmemo.entity.DailyPoints;
import org.liberty.android.fantastischmemo.entity.DeckPoints;
import org.liberty.android.fantastischmemo.entity.TagPoints;
import org.liberty.android.fantastischmemo.entity.User;
import org.liberty.android.fantastischmemo.entity.UserStatistics;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Emily on 2018-03-15.
 */

public class AccountPage extends BaseActivity{
    public static final int REQUEST_CODE_UPDATE_NAME = 1;

    private TextView user_Name;
    private TextView username;
    private TextView longest_streak;
    private TextView current_streak;
    private Button view_statistic;

    private User user;
    private UserStatistics userStat;
    private UserDao userDao;
    private UserStatisticsDao userStatDao;
    private List<AchievementPoint> achPointList;
    private AchievementPointDao achPointDao;
    private List<AchievementTagPointsJoin> achPointJoinList;
    private AchievementTagPointsJoinDao achPointJoinDao;
    private List<TagPoints> tagPointList;
    private TagPointsDao tagPointDao;
    private List<DeckPoints> deckPointList;
    private DeckPointsDao deckPointDao;
    private List<DailyPoints> dailyPointList;
    private DailyPointsDao dailyPointDao;
    private AnyMemoBaseDBOpenHelper baseHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponents().inject(this);
        setContentView(R.layout.account_page_tab);

        baseHelper = AnyMemoBaseDBOpenHelperManager.getHelper();
        userDao = baseHelper.getUserDao();
        userStatDao = baseHelper.getUserStatisticsDao();
        achPointDao = baseHelper.getAchievementPointDao();
        achPointJoinDao = baseHelper.getAchievementTagPointsJoinDao();
        deckPointDao = baseHelper.getDeckPointsDao();
        tagPointDao = baseHelper.getTagPointsDao();
        dailyPointDao = baseHelper.getDailyPointsDao();


        user = userDao.returnFirstUser();
        userStat = userStatDao.createOrReturn(user);

        user_Name = (TextView)findViewById(R.id.account_user_name);
        user_Name.setText(user.getName());

        username = (TextView)findViewById(R.id.account_username);
        username.setText(user.getUsername());

        longest_streak = (TextView)findViewById(R.id.account_longest_streak);
        longest_streak.setText(String.valueOf(userStat.getLongestStreak()));

        current_streak = (TextView)findViewById(R.id.account_current_streak);
        current_streak.setText(String.valueOf(userStat.getStreak()));
      
        view_statistic = (Button)findViewById(R.id.view_statistic);
        view_statistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountPage.this, PointStatisticActivity.class);
                startActivity(intent);
            }
        });
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
                startActivityForResult(intent, REQUEST_CODE_UPDATE_NAME);
                return true;
            }
            case R.id.delete_account:{
                try{
                    achPointList = achPointDao.queryForAll();
                    for(AchievementPoint ap: achPointList){
                        achPointDao.delete(ap);
                    }

                    achPointJoinList = achPointJoinDao.queryForAll();
                    for(AchievementTagPointsJoin atpj: achPointJoinList){
                        achPointJoinDao.delete(atpj);
                    }

                    deckPointList = deckPointDao.queryForAll();
                    for(DeckPoints deckp: deckPointList){
                        deckPointDao.delete(deckp);
                    }

                    tagPointList = tagPointDao.queryForAll();
                    for(TagPoints tp: tagPointList){
                        tagPointDao.delete(tp);
                    }

                    dailyPointList = dailyPointDao.queryForAll();
                    for(DailyPoints dailyp: dailyPointList){
                        dailyPointDao.delete(dailyp);
                    }

                }catch (SQLException e){
                    e.printStackTrace();
                }

                userDao.delete(user);
                Toast.makeText(this, R.string.delete_account_message, Toast.LENGTH_LONG)
                        .show();
                Intent intent = new Intent(AccountPage.this, AnyMemo.class);
                startActivity(intent);
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_UPDATE_NAME) {
            if(resultCode == Activity.RESULT_OK) {
                user = userDao.returnFirstUser();
                user_Name.setText(user.getName());
            }
        }
    }
}

