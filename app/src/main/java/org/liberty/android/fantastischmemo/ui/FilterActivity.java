package org.liberty.android.fantastischmemo.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import org.liberty.android.fantastischmemo.R;
import org.liberty.android.fantastischmemo.common.AnyMemoBaseDBOpenHelperManager;
import org.liberty.android.fantastischmemo.common.BaseActivity;
import org.liberty.android.fantastischmemo.entity.Deck;
import org.liberty.android.fantastischmemo.entity.Tag;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilterActivity extends BaseActivity {
    private List<Tag> filteredChecked = new ArrayList<>();
    private DecksAdapter decksAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_layout);

        Context context = getBaseContext();
        RecyclerView decksRecyclerView = (RecyclerView) findViewById(R.id.deck_list);
        decksRecyclerView.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        decksRecyclerView.setLayoutManager(linearLayoutManager);

        List<Deck> decks = filterDecks();

        decksAdapter = new DecksAdapter(decks);
        decksRecyclerView.setAdapter(decksAdapter);

        /*DeckDao deckDao = AnyMemoBaseDBOpenHelperManager.getHelper("central.db").getDeckDao();
        try {
            deckDao.delete(deckDao.queryForAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.filter_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter:
                AlertDialog alertDialog = buildFilterDialog();
                alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

    private AlertDialog buildFilterDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.filter_layout, null);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.deck_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        List<Tag> list = loadTags();
        final FilterAdapter filterAdapter = new FilterAdapter(list, filteredChecked);
        recyclerView.setAdapter(filterAdapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Filter by Tag");
        builder.setView(view);
        builder.setPositiveButton(getString(R.string.filter_text), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                filteredChecked.clear();
                filteredChecked.addAll(filterAdapter.getCheckedTags());
                decksAdapter.setDecks(filterDecks());
                decksAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton(getString(R.string.cancel_text), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        return builder.create();
    }

    private List<Deck> filterDecks() {
        List<Deck> allDecks = loadDecks();

        if (filteredChecked.isEmpty()) {
            return allDecks;
        } else {
            List<Deck> filteredDecks = new ArrayList<>();

            for (Tag tag : filteredChecked) {
                List<Deck> decksTagBelongTo = tag.getDecks();
                for (Deck deck : allDecks) {
                    for (Deck taggedDecks : decksTagBelongTo) {
                        if (taggedDecks.getId().intValue() == deck.getId().intValue()) {
                            filteredDecks.add(deck);
                        }
                    }
                }
            }

            return filteredDecks;
        }
    }

    private List<Deck> loadDecks() {
        try {
            return AnyMemoBaseDBOpenHelperManager.getHelper("central.db").getDeckDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Tag> loadTags() {
        try {
            return AnyMemoBaseDBOpenHelperManager.getHelper("central.db").getTagDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.FilterViewHolder> {

        class FilterViewHolder extends RecyclerView.ViewHolder {
            TextView tagTextView;
            CheckBox tagCheckBox;

            FilterViewHolder(View itemView) {
                super(itemView);
                tagTextView = (TextView) itemView.findViewById(R.id.tag_text);
                tagCheckBox = (CheckBox) itemView.findViewById(R.id.tag_check_box);
            }
        }

        private List<Tag> tags;
        private List<Tag> filteredTags;

        FilterAdapter(List<Tag> tags, List<Tag> checked) {
            this.tags = tags;
            this.filteredTags = new ArrayList<>();
            this.filteredTags.addAll(checked);
        }

        @Override
        public FilterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View filterRowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.filter_row, parent, false);
            return new FilterAdapter.FilterViewHolder(filterRowView);
        }

        @Override
        public void onBindViewHolder(FilterViewHolder holder, int position) {
            final Tag tag = tags.get(position);
            holder.tagTextView.setText(tag.getName());
            holder.tagCheckBox.setChecked(filteredTags.contains(tag));
            holder.tagCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        filteredTags.add(tag);
                    } else {
//                        int index = filteredTags.indexOf(tag.getId());
//                        filteredTags.remove(index);
                        filteredTags.remove(tag);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return tags.size();
        }

        List<Tag> getCheckedTags() {
            return this.filteredTags;
        }
    }

    class DecksAdapter extends RecyclerView.Adapter<DecksAdapter.DecksViewHolder> {

        class DecksViewHolder extends RecyclerView.ViewHolder {
            TextView textView;
            ImageButton imageButton;

            DecksViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.deck_text);
                imageButton = (ImageButton) itemView.findViewById(R.id.deck_edit_button);
            }
        }

        private List<Deck> decks;

        DecksAdapter(List<Deck> decks) {
            this.decks = decks;
        }

        @Override
        public DecksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View deckRowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.deck_row, parent, false);
            return new DecksViewHolder(deckRowView);
        }

        @Override
        public int getItemCount() {
            return decks.size();
        }

        @Override
        public void onBindViewHolder(DecksViewHolder holder, int position) {
            final Deck deck = decks.get(position);
            holder.textView.setText(deck.getName());
            holder.imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(FilterActivity.this, TagsActivity.class);
                    intent.putExtra("deckPath", deck.getDbPath());
                    startActivity(intent);
                }
            });
        }

        public void setDecks(List<Deck> decks) {
            this.decks = decks;
        }
    }
}
