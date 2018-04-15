package org.liberty.android.fantastischmemo.ui;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import org.liberty.android.fantastischmemo.R;
import org.liberty.android.fantastischmemo.common.AnyMemoBaseDBOpenHelper;
import org.liberty.android.fantastischmemo.common.AnyMemoBaseDBOpenHelperManager;
import org.liberty.android.fantastischmemo.common.BaseActivity;
import org.liberty.android.fantastischmemo.dao.AchievementPointDao;
import org.liberty.android.fantastischmemo.entity.AchievementPoint;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.DateFormat;

/**
 * Created by Paul on 2018-04-03.
 */

public class PointStatisticActivity extends BaseActivity {

    private AnyMemoBaseDBOpenHelper helper;
    AchievementPointDao pointDao;
    private static DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_stats);
        helper = AnyMemoBaseDBOpenHelperManager.getHelper();
        pointDao = helper.getAchievementPointDao();

        RecyclerView pointsRecyclerView = (RecyclerView) findViewById(R.id.stats_list);
        pointsRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        pointsRecyclerView.setLayoutManager(linearLayoutManager);


        AchievementPoint pointee = new AchievementPoint();
        pointee.setCreationDate(new Date());
        pointee.setValue(5);
        pointee.setTime(new Date());
        List<AchievementPoint> points = new ArrayList<>();
        points.add(pointee);

        PointsAdapter pointsAdapter = new PointsAdapter(points);
        pointsRecyclerView.setAdapter(pointsAdapter);

    }

    public List<AchievementPoint> loadPoints() {
        try {
            return pointDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    class PointsAdapter extends RecyclerView.Adapter<PointsAdapter.PointsViewHolder> {

        private List<AchievementPoint> points;

        class PointsViewHolder extends RecyclerView.ViewHolder {
            TextView dateTextView;
            TextView pointsTextView;

            PointsViewHolder(View itemView) {
                super(itemView);
                dateTextView = (TextView) itemView.findViewById(R.id.date_text);
                pointsTextView = (TextView) itemView.findViewById(R.id.points_text);
            }
        }

        PointsAdapter(List<AchievementPoint> points) {
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
            final AchievementPoint point = points.get(position);
            holder.dateTextView.setText(dateFormat.format(point.getTime()));
            holder.pointsTextView.setText(point.getValue().toString());
        }
    }

}
