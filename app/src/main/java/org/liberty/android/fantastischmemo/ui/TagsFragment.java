package org.liberty.android.fantastischmemo.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import org.liberty.android.fantastischmemo.R;
import org.liberty.android.fantastischmemo.common.AnyMemoDBOpenHelper;
import org.liberty.android.fantastischmemo.common.AnyMemoDBOpenHelperManager;
import org.liberty.android.fantastischmemo.common.BaseFragment;
import org.liberty.android.fantastischmemo.dao.TagDao;
import org.liberty.android.fantastischmemo.entity.Tag;
import org.liberty.android.fantastischmemo.ui.adapters.AbstractTagsAdapter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TagsFragment extends BaseFragment {

    TagsAdapter tagsAdapter;
    TagDao tagDao;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tags_fragment, container, false);
        Context context = view.getContext();

        RecyclerView tagsRecyclerView = (RecyclerView) view.findViewById(R.id.tags_list);
        tagsRecyclerView.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        tagsRecyclerView.setLayoutManager(linearLayoutManager);

        Bundle bundle = this.getArguments();
        String deckPath = bundle.getString("deckPath");
        AnyMemoDBOpenHelper deckDBHelper = AnyMemoDBOpenHelperManager.getHelper(deckPath);
        tagDao = deckDBHelper.getTagDao();

        List<Tag> tags = loadTags();

        tagsAdapter = new TagsAdapter(tags);
        tagsRecyclerView.setAdapter(tagsAdapter);

        return view;
    }

    public List<Tag> loadTags() {
        try {
            return tagDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    class TagsAdapter extends AbstractTagsAdapter<TagsAdapter.TagsViewHolder> {

        class TagsViewHolder extends RecyclerView.ViewHolder {
            TextView tagTextView;
            ImageButton tagDeleteButton;

            TagsViewHolder(View itemView) {
                super(itemView);
                tagTextView = (TextView) itemView.findViewById(R.id.tag_text_view);
                tagDeleteButton = (ImageButton) itemView.findViewById(R.id.tag_delete_button);
            }
        }

        TagsAdapter(List<Tag> tags) {
            super(tags);
        }

        @Override
        public TagsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View tagRowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tag_row, parent, false);
            return new TagsViewHolder(tagRowView);
        }

        @Override
        public void onBindViewHolder(TagsViewHolder holder, int position) {
            final Tag tag = tags.get(position);
            holder.tagTextView.setText(tag.getName());
            holder.tagDeleteButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(final View v) {
                    AlertDialog alertDialog = buildDeleteDialog(v.getContext(), tag);
                    alertDialog.show();
                }
            });
        }

        private AlertDialog buildDeleteDialog(Context context, final Tag tag) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            builder.setTitle(context.getString(R.string.delete_text));
            builder.setMessage("Are you sure you want to delete this tag from this deck?");
            builder.setPositiveButton(context.getString(R.string.delete_text), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    deleteTag(tag);
                }
            });
            builder.setNegativeButton(context.getString(R.string.cancel_text), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            return builder.create();
        }

        @Override
        public void deleteTag(Tag tag) {
            try {
                tagDao.delete(tag);
                super.deleteTag(tag);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void addTag(Tag tag) {
            try {
                tagDao.createIfNotExists(tag);
                super.addTag(tag);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
