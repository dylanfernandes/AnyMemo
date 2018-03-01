package org.liberty.android.fantastischmemo.ui;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.liberty.android.fantastischmemo.R;
import org.liberty.android.fantastischmemo.common.BaseActivity;
import org.liberty.android.fantastischmemo.entity.DeckMap;
import org.liberty.android.fantastischmemo.entity.DeckMock;
import org.liberty.android.fantastischmemo.entity.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TagsActivity extends BaseActivity {
    private DeckMap deckMap;
    private DeckMock deck;
    private List<Tag> tags;
    private RecyclerView tagsRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private TagsAdapter tagsAdapter;
    boolean isFABOpen;
    FloatingActionButton createNewButton, addExistingButton, addTagButton;
    LinearLayout createLayout, addLayout;
    View fabBGLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_tags_layout);

        deck = DeckMap.getInstance().getDecksMap().get(getIntent().getStringExtra("deckPath"));

        tagsRecyclerView = (RecyclerView) findViewById(R.id.tags_list);
        linearLayoutManager = new LinearLayoutManager(this);
        tagsRecyclerView.setLayoutManager(linearLayoutManager);
        tagsRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        tags = loadTags();

        tagsAdapter = new TagsAdapter(tags);
        tagsRecyclerView.setAdapter(tagsAdapter);

        createLayout = (LinearLayout) findViewById(R.id.createLayout);
        addLayout = (LinearLayout) findViewById(R.id.existingLayout);

        addTagButton = (FloatingActionButton) findViewById(R.id.add_tag_fab);
        createNewButton = (FloatingActionButton) findViewById(R.id.create_new_fab);
        addExistingButton = (FloatingActionButton) findViewById(R.id.add_existing_fab);

        fabBGLayout=findViewById(R.id.fabBGLayout);

        createNewButton.setOnClickListener(new View.OnClickListener() {
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

        addTagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFABOpen){
                    showFABMenu();
                }else{
                    closeFABMenu();
                }
            }
        });
    }


    private void showFABMenu(){
        isFABOpen=true;
        createLayout.setVisibility(View.VISIBLE);
        addLayout.setVisibility(View.VISIBLE);
        fabBGLayout.setVisibility(View.VISIBLE);

        addTagButton.animate().rotationBy(180);
        createLayout.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        addLayout.animate().translationY(-getResources().getDimension(R.dimen.standard_100));
    }

    private void closeFABMenu(){
        isFABOpen=false;
        fabBGLayout.setVisibility(View.GONE);
        addTagButton.animate().rotationBy(-180);
        createLayout.animate().translationY(0);
        addLayout.animate().translationY(0).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if(!isFABOpen){
                    createLayout.setVisibility(View.GONE);
                    addLayout.setVisibility(View.GONE);
                }

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tag_activity_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.tag_search:
            {
                tags = loadAllTags();
                tagsAdapter = new TagsAdapter(tags);
                tagsRecyclerView.setAdapter(tagsAdapter);
                return true;
            }
        }
        return false;
    }

    public List<Tag> loadTags() {
//        List<Tag> tags = new ArrayList<>();
//        for (int i = 0; i < 50; i++) {
//            tags.add(new Tag("Some Tag Name " + (i + 1)));
//        }
//        tags.add(new Tag("Language"));
//        tags.add(new Tag("French"));
//        tags.add(new Tag("Body Parts"));

//        return tags;
        return deck.getTags();
    }

    public List<Tag> loadAllTags(){
        ArrayList<Tag> tagList = new ArrayList<Tag>();
        HashMap<String, DeckMock> deckList = DeckMap.getInstance().getDecksMap();

        for(DeckMock deck : deckList.values()){
            tagList.addAll(deck.getTags());
        }
        return tagList;
    }

    class TagsAdapter extends RecyclerView.Adapter<TagsAdapter.TagsViewHolder> {

        class TagsViewHolder extends RecyclerView.ViewHolder {
            TextView tagTextView;
            ImageButton tagDeleteButton;

            TagsViewHolder(View itemView) {
                super(itemView);
                tagTextView = (TextView) itemView.findViewById(R.id.tag_text_view);
                tagDeleteButton = (ImageButton) itemView.findViewById(R.id.tag_delete_button);
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
//            deck.addTag(tag);
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
            holder.tagDeleteButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(final View v) {
                    new AlertDialog.Builder(v.getContext())
                        .setTitle(v.getContext().getString(R.string.delete_text))
                        .setMessage("Are you sure you want to delete this tag from this deck?")
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
            });
        }

        @Override
        public int getItemCount() {
            return tags.size();
        }
    }
}
