package org.liberty.android.fantastischmemo.ui;

import android.animation.Animator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.liberty.android.fantastischmemo.R;
import org.liberty.android.fantastischmemo.common.AnyMemoBaseDBOpenHelper;
import org.liberty.android.fantastischmemo.common.AnyMemoBaseDBOpenHelperManager;
import org.liberty.android.fantastischmemo.common.BaseActivity;
import org.liberty.android.fantastischmemo.dao.TagDao;
import org.liberty.android.fantastischmemo.entity.DeckMap;
import org.liberty.android.fantastischmemo.entity.DeckMock;
import org.liberty.android.fantastischmemo.entity.Tag;
import org.liberty.android.fantastischmemo.ui.adapters.AbstractTagsAdapter;

import java.sql.SQLException;
import java.util.List;

public class TagsActivity extends BaseActivity {
    private RecyclerView tagsAddRecyclerView;
    private LinearLayoutManager linearAddLayoutManager;
    boolean isFABOpen;
    FloatingActionButton createNewButton, addExistingButton, addTagButton;
    LinearLayout createLayout, addLayout;
    View fabBGLayout;
    private TagDao centralTagDao;
    private TagsFragment tagsFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_tags_layout);

        AnyMemoBaseDBOpenHelper helper = AnyMemoBaseDBOpenHelperManager.getHelper("central.db");
        centralTagDao = helper.getTagDao();

        String deckPath = getIntent().getStringExtra("deckPath");

        DeckMock deck = DeckMap.getInstance().getDecksMap().get(deckPath);
        setTitle("Tags for: " + deck.getName());

        Bundle bundle = new Bundle();
        bundle.putString("deckPath", deckPath);
        tagsFragment = new TagsFragment();
        tagsFragment.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.tags_fragment_root, tagsFragment);
        transaction.commit();

        createLayout = (LinearLayout) findViewById(R.id.createLayout);
        addLayout = (LinearLayout) findViewById(R.id.existingLayout);

        addTagButton = (FloatingActionButton) findViewById(R.id.add_tag_fab);
        createNewButton = (FloatingActionButton) findViewById(R.id.create_new_fab);
        addExistingButton = (FloatingActionButton) findViewById(R.id.add_existing_fab);

        fabBGLayout = findViewById(R.id.fabBGLayout);

        createNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText input = new EditText(v.getContext());
                input.setTag("create_tag_input");
                new AlertDialog.Builder(v.getContext())
                        .setTitle("Create Tag")
                        .setMessage("Enter new name for tag below.")
                        .setView(input)
                        .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String tagName = input.getText().toString();
                                Tag tag = new Tag(tagName);
                                try {
                                    centralTagDao.createIfNotExists(tag);
                                    tagsFragment.tagsAdapter.addTag(tag);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
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

        addExistingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Tag> tagsToAdd = loadAllTagsExceptInDeck();

                LayoutInflater inflater = getLayoutInflater();
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                View addDialog = inflater.inflate(R.layout.tag_add_dialog, null);
                Context currentContext = addDialog.getContext();
                builder.setView(addDialog);
                builder.setTitle("Add Existing Tag to Deck" );
                builder.setNegativeButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tagsFragment.tagsAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                tagsAddRecyclerView = (RecyclerView) addDialog.findViewById(R.id.tags_add_list);
                linearAddLayoutManager = new LinearLayoutManager(currentContext);
                tagsAddRecyclerView.setLayoutManager(linearAddLayoutManager);
                tagsAddRecyclerView.addItemDecoration(new DividerItemDecoration(currentContext, LinearLayoutManager.VERTICAL));
                final TagsAddAdapter addAdapter = new TagsAddAdapter(tagsToAdd, tagsFragment);
                tagsAddRecyclerView.setAdapter(addAdapter);

                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    private void showFABMenu(){
        isFABOpen = true;
        createLayout.setVisibility(View.VISIBLE);
        addLayout.setVisibility(View.VISIBLE);
        fabBGLayout.setVisibility(View.VISIBLE);

        addTagButton.animate().rotationBy(180);
        createLayout.animate().translationY(-getResources().getDimension(R.dimen.standard_55));
        addLayout.animate().translationY(-getResources().getDimension(R.dimen.standard_100));
    }

    private void closeFABMenu(){
        isFABOpen = false;
        fabBGLayout.setVisibility(View.GONE);
        addTagButton.animate().rotationBy(-180);
        createLayout.animate().translationY(0);
        addLayout.animate().translationY(0).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {}

            @Override
            public void onAnimationEnd(Animator animator) {
                if(!isFABOpen){
                    createLayout.setVisibility(View.GONE);
                    addLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {}

            @Override
            public void onAnimationRepeat(Animator animator) {}
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tag_activity_menu, menu);
        return true;
    }

    /*
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

    public List<Tag> loadAllTags(){
        ArrayList<Tag> tagList = new ArrayList<Tag>();
        HashMap<String, DeckMock> deckList = DeckMap.getInstance().getDecksMap();

        for(DeckMock deck : deckList.values()){
            tagList.addAll(deck.getTags());
        }
        return tagList;
    }
    */

    public List<Tag> loadAllTagsExceptInDeck(){
        try {
            List<Tag> tags = centralTagDao.queryForAll();
            tags.removeAll(tagsFragment.loadTags());
            return tags;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    class TagsAddAdapter extends AbstractTagsAdapter<TagsAddAdapter.TagsAddViewHolder> {

        class TagsAddViewHolder extends RecyclerView.ViewHolder {
            TextView tagTextView;
            ImageButton tagAddButton;

            TagsAddViewHolder(View itemView) {
                super(itemView);
                tagTextView = (TextView) itemView.findViewById(R.id.tag_add_text_view);
                tagAddButton = (ImageButton) itemView.findViewById(R.id.tag_add_button);
            }
        }

        private TagsFragment tagsFragment;

        TagsAddAdapter(List<Tag> tags, TagsFragment tagsFragment) {
            super(tags);
            this.tagsFragment = tagsFragment;
        }

        @Override
        public TagsAddViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View tagRowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tag_add_row, parent, false);
            return new TagsAddViewHolder(tagRowView);
        }

        @Override
        public void onBindViewHolder(TagsAddViewHolder holder, int position) {
            final Tag tag = tags.get(position);
            holder.tagTextView.setText(tag.getName());
            holder.tagAddButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(final View v) {
                    Toast.makeText(v.getContext(), tag + " Tag added to Deck", Toast.LENGTH_LONG).show();
                    tagsFragment.tagsAdapter.addTag(tag);
                    deleteTag(tag);
                }
            });
        }
    }
}
