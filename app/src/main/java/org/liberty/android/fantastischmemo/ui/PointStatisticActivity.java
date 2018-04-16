package org.liberty.android.fantastischmemo.ui;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.liberty.android.fantastischmemo.R;
import org.liberty.android.fantastischmemo.common.AnyMemoBaseDBOpenHelper;
import org.liberty.android.fantastischmemo.common.AnyMemoBaseDBOpenHelperManager;
import org.liberty.android.fantastischmemo.common.BaseActivity;
import org.liberty.android.fantastischmemo.dao.AchievementPointDao;
import org.liberty.android.fantastischmemo.entity.AchievementPoint;
import org.liberty.android.fantastischmemo.entity.DailyPoints;
import org.liberty.android.fantastischmemo.entity.DeckPoints;
import org.liberty.android.fantastischmemo.dao.DailyPointsDao;
import org.liberty.android.fantastischmemo.dao.DeckPointsDao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.text.DateFormat;

/**
 * Created by Paul on 2018-04-03.
 */

public class PointStatisticActivity extends BaseActivity {

    private AnyMemoBaseDBOpenHelper helper;
    AchievementPointDao pointDao;
    DailyPointsDao dailyPointDao;
    DeckPointsDao deckPointsDao;
    private static DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_stats);
        helper = AnyMemoBaseDBOpenHelperManager.getHelper();
        pointDao = helper.getAchievementPointDao();
        dailyPointDao = helper.getDailyPointsDao();
        deckPointsDao = helper.getDeckPointsDao();

        final RecyclerView pointsRecyclerView = (RecyclerView) findViewById(R.id.stats_list);
        pointsRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        pointsRecyclerView.setLayoutManager(linearLayoutManager);


        AchievementPoint pointee = new AchievementPoint();
        pointee.setCreationDate(new Date());
        pointee.setTime(new Date());
        pointee.setValue(5);
        AchievementPoint ap = new AchievementPoint();
        ap.setCreationDate(new Date());
        ap.setValue(10);
        ap.setTime(new Date());


        DailyPoints dailyPoints = dailyPointDao.createOrReturn();
        DeckPoints deckPoints = new DeckPoints();
        deckPoints.setDeckName("German");


        pointee.setDailyPoints(dailyPoints);
        ap.setDailyPoints(dailyPoints);
        pointee.setDeckPoints(deckPoints);
        ap.setDeckPoints(deckPoints);

        deckPointsDao.create(deckPoints);

        try {
            pointDao.create(pointee);
            pointDao.create(ap);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dailyPointDao.update(dailyPoints);

        final RadioGroup pointDisplays = (RadioGroup) findViewById(R.id.point_display);

        pointDisplays.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View radioButton = pointDisplays.findViewById(checkedId);
                int index = pointDisplays.indexOfChild(radioButton);

                switch (index) {
                    case 0:
                        DailyPointsAdapter pointsAdapter = new DailyPointsAdapter(loadDailyPoints());
                        pointsRecyclerView.setAdapter(pointsAdapter);
                        break;
                    case 1:
                        DeckPointsAdapter deckPointsAdapter = new DeckPointsAdapter(loadDeckPoints());
                        pointsRecyclerView.setAdapter(deckPointsAdapter);
                }
            }
        });

    }

    public List<DailyPoints> loadDailyPoints() {
        List<DailyPoints> dailyPointsList = dailyPointDao.queryForAll();
        return dailyPointsList;
    }

    public List<DeckPoints> loadDeckPoints() {
        List<DeckPoints> deckPointsList = deckPointsDao.queryForAll();
        return deckPointsList;

    }


    class DailyPointsAdapter extends RecyclerView.Adapter<DailyPointsAdapter.PointsViewHolder> {

        private List<DailyPoints> points;

        class PointsViewHolder extends RecyclerView.ViewHolder {
            TextView dateTextView;
            TextView pointsTextView;

            PointsViewHolder(View itemView) {
                super(itemView);
                dateTextView = (TextView) itemView.findViewById(R.id.date_text);
                pointsTextView = (TextView) itemView.findViewById(R.id.points_text);
            }
        }

        DailyPointsAdapter(List<DailyPoints> points) {
            this.points = points;
        }

        @Override
        public PointsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View pointsRowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_points_row, parent, false);
            return new PointsViewHolder(pointsRowView);
        }


        @Override
        public int getItemCount() {
            return points.size();
        }

        @Override
        public void onBindViewHolder(PointsViewHolder holder, int position) {
            final DailyPoints point = points.get(position);
            holder.dateTextView.setText(dateFormat.format(point.getTime()));
            holder.pointsTextView.setText(point.getSum().toString());
        }
    }

    class DeckPointsAdapter extends RecyclerView.Adapter<DeckPointsAdapter.PointsViewHolder> {

        private List<DeckPoints> points;

        class PointsViewHolder extends RecyclerView.ViewHolder {
            TextView dateTextView;
            TextView pointsTextView;

            PointsViewHolder(View itemView) {
                super(itemView);
                dateTextView = (TextView) itemView.findViewById(R.id.date_text);
                pointsTextView = (TextView) itemView.findViewById(R.id.points_text);
            }
        }

        DeckPointsAdapter(List<DeckPoints> points) {
            this.points = points;
        }

        @Override
        public PointsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View pointsRowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_points_row, parent, false);
            return new PointsViewHolder(pointsRowView);
        }


        @Override
        public int getItemCount() {
            return points.size();
        }

        @Override
        public void onBindViewHolder(PointsViewHolder holder, int position) {
            final DeckPoints point = points.get(position);
            holder.dateTextView.setText(point.getDeckName());
            holder.pointsTextView.setText(point.getSum().toString());
        }
    }

}
