package org.liberty.android.fantastischmemo.ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.liberty.android.fantastischmemo.R;
import org.liberty.android.fantastischmemo.common.BaseActivity;
import org.liberty.android.fantastischmemo.entity.Tag;

import java.util.ArrayList;
import java.util.List;

public class TagsActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_tags_layout);

        final RecyclerView tagsRecyclerView = (RecyclerView) findViewById(R.id.tags_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        tagsRecyclerView.setLayoutManager(linearLayoutManager);
        tagsRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        final List<Tag> tags = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            tags.add(new Tag("Some Tag Name " + (i + 1)));
        }

        final TagsAdapter tagsAdapter = new TagsAdapter(tags);
        tagsRecyclerView.setAdapter(tagsAdapter);

        FloatingActionButton addTagButton = (FloatingActionButton) findViewById(R.id.add_tag_fab);
        addTagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText input = new EditText(v.getContext());
                new AlertDialog.Builder(v.getContext())
                        .setTitle("Create Tag")
                        .setMessage("Enter new name for tag below.")
                        .setView(input)
                        .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String tagName = input.getText().toString();
                                tagsAdapter.addTag(new Tag(tagName));
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });
    }

    class TagsAdapter extends RecyclerView.Adapter<TagsAdapter.TagsViewHolder> {

        class TagsViewHolder extends RecyclerView.ViewHolder {
            TextView tagTextView;
            ImageButton tagEditButton;

            TagsViewHolder(View itemView) {
                super(itemView);
                tagTextView = (TextView) itemView.findViewById(R.id.tag_text_view);
                tagEditButton = (ImageButton) itemView.findViewById(R.id.tag_edit_button);
            }
        }

        private List<Tag> tags;

        TagsAdapter() {
            this.tags = new ArrayList<>();
        }

        TagsAdapter(List<Tag> tags) {
            this.tags = tags;
        }

        public void addTag(Tag tag) {
            tags.add(tag);
            this.notifyDataSetChanged();
        }

        private void deleteTag(int position) {
            tags.remove(position);
            this.notifyItemRemoved(position);
        }

        @Override
        public TagsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View tagRowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tag_row, parent, false);
            return new TagsViewHolder(tagRowView);
        }

        @Override
        public void onBindViewHolder(TagsViewHolder holder, @SuppressLint("RecyclerView") final int position) {
            final Tag tag = tags.get(position);
            holder.tagTextView.setText(tag.getName());
            holder.tagEditButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(final View v) {
                    final EditText input = new EditText(v.getContext());
                    input.setText(tag.getName());
                    input.setSelection(input.getText().length());
                    new AlertDialog.Builder(v.getContext())
                            .setTitle(v.getContext().getString(R.string.edit_text))
                            .setMessage("Enter new name for tag below.")
                            .setView(input)
                            .setPositiveButton(v.getContext().getString(R.string.settings_save), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String tagName = input.getText().toString();
                                    tag.setName(tagName);
                                    TagsAdapter.this.notifyItemChanged(position);
                                }
                            })
                            .setNegativeButton(v.getContext().getString(R.string.cancel_text), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .setNeutralButton(v.getContext().getString(R.string.delete_text), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    new AlertDialog.Builder(v.getContext())
                                            .setTitle(v.getContext().getString(R.string.delete_text))
                                            .setMessage("Are you sure you want to delete this tag?")
                                            .setPositiveButton(v.getContext().getString(R.string.delete_text), new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    deleteTag(position);
                                                }
                                            })
                                            .setNegativeButton(v.getContext().getString(R.string.cancel_text), new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                }
                                            })
                                            .show();
                                }
                            })
                            .show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return tags.size();
        }
    }
}
