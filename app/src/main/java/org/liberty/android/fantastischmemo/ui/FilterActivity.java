package org.liberty.android.fantastischmemo.ui;

import android.app.AlertDialog;
import android.content.Context;
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
import android.widget.CheckedTextView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_layout);

        Context context = getBaseContext();
        RecyclerView decksRecyclerView = (RecyclerView) findViewById(R.id.deck_list);
        decksRecyclerView.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        decksRecyclerView.setLayoutManager(linearLayoutManager);

        List<Deck> decks = loadDecks();

        DecksAdapter decksAdapter = new DecksAdapter(decks);
        decksRecyclerView.setAdapter(decksAdapter);
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
        recyclerView.setAdapter(new FilterAdapter(loadTags()));

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Filter by Tag");
        builder.setView(view);

        return builder.create();
    }

    private List<Deck> loadDecks() {
        List<Deck> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Deck deck = new Deck();
            deck.setName("Deck " + i);
            list.add(deck);
        }
        return list;
    }

    private List<Tag> loadTags() {
        List<Tag> tags = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            tags.add(new Tag("Random " + i));
        }

        return tags;
        /*try {
            return AnyMemoBaseDBOpenHelperManager.getHelper("central.db").getTagDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;*/
    }

    class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.FilterViewHolder> {

        class FilterViewHolder extends RecyclerView.ViewHolder {
            TextView tagTextView;

            FilterViewHolder(View itemView) {
                super(itemView);
                tagTextView = (TextView) itemView.findViewById(R.id.deck_text);
            }
        }

        private List<Tag> tags;

        FilterAdapter(List<Tag> tags) {
            this.tags = tags;
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
        }

        @Override
        public int getItemCount() {
            return tags.size();
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
                    new AlertDialog.Builder(v.getContext())
                            .setTitle("Deck")
                            .setMessage("TO IMPLEMENT")
                            .show();
                }
            });
        }
    }
}
