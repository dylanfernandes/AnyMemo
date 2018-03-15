package org.liberty.android.fantastischmemo.ui.adapters;

import android.support.v7.widget.RecyclerView;

import org.liberty.android.fantastischmemo.entity.Tag;

import java.util.List;

public abstract class AbstractTagsAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected List<Tag> tags;

    public AbstractTagsAdapter(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }

    public void addTag(Tag tag) {
        tags.add(tag);
        this.notifyDataSetChanged();
    }

    public void deleteTag(Tag tag) {
        int position = tags.indexOf(tag);
        tags.remove(tag);
        this.notifyItemRemoved(position);
    }
}