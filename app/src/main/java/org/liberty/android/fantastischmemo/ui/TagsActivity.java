package org.liberty.android.fantastischmemo.ui;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.liberty.android.fantastischmemo.R;
import org.liberty.android.fantastischmemo.common.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class TagsActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_tags_layout);

        RecyclerView tagsRecyclerView = (RecyclerView) findViewById(R.id.tags_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        tagsRecyclerView.setLayoutManager(linearLayoutManager);
        tagsRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        List<String> tags = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            tags.add("Some Tag Name " + (i + 1));
        }

        tagsRecyclerView.setAdapter(new TagsAdapter(tags));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.edit_tags_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    class TagsAdapter extends RecyclerView.Adapter<TagsAdapter.TagsViewHolder> {

        class TagsViewHolder extends RecyclerView.ViewHolder {
            TextView tagTextView;

            TagsViewHolder(View itemView) {
                super(itemView);
                tagTextView = (TextView) itemView.findViewById(R.id.tag_text_view);
            }
        }

        private List<String> tags;

        TagsAdapter(List<String> tags) {
            this.tags = tags;
        }

        @Override
        public TagsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View tagRowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tag_row, parent, false);
            return new TagsViewHolder(tagRowView);
        }

        @Override
        public void onBindViewHolder(TagsViewHolder holder, int position) {
            String tag = tags.get(position);
            holder.tagTextView.setText(tag);
        }

        @Override
        public int getItemCount() {
            return tags.size();
        }
    }
}
