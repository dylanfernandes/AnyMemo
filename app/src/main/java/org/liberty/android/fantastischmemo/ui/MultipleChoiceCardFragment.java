package org.liberty.android.fantastischmemo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import org.apache.commons.io.FilenameUtils;
import org.liberty.android.fantastischmemo.R;
import org.liberty.android.fantastischmemo.common.AMEnv;
import org.liberty.android.fantastischmemo.common.AnyMemoBaseDBOpenHelper;
import org.liberty.android.fantastischmemo.common.AnyMemoBaseDBOpenHelperManager;
import org.liberty.android.fantastischmemo.common.AnyMemoDBOpenHelper;
import org.liberty.android.fantastischmemo.common.AnyMemoDBOpenHelperManager;
import org.liberty.android.fantastischmemo.common.BaseFragment;
import org.liberty.android.fantastischmemo.dao.AchievementPointDao;
import org.liberty.android.fantastischmemo.dao.DailyPointsDao;
import org.liberty.android.fantastischmemo.dao.DeckPointsDao;
import org.liberty.android.fantastischmemo.dao.TagDao;
import org.liberty.android.fantastischmemo.dao.TagPointsDao;
import org.liberty.android.fantastischmemo.entity.AchievementPoint;
import org.liberty.android.fantastischmemo.entity.Card;
import org.liberty.android.fantastischmemo.entity.DailyPoints;
import org.liberty.android.fantastischmemo.entity.DeckPoints;
import org.liberty.android.fantastischmemo.entity.Tag;
import org.liberty.android.fantastischmemo.entity.TagPoints;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MultipleChoiceCardFragment extends BaseFragment {

    public static final String EXTRA_FIELD1_CARD_FRAGMENT = "field1CardFragment";
    public static final String ARGUMENT_KEY_CARD_ID = "cardId";
    public static final String ARGUMENT_KEY_DB_PATH = "dbPath";

    private Card answerCard;
    private List<Choice> choices;

    private Runnable changeCardTask;

    private AnyMemoBaseDBOpenHelper baseHelper;
    private AnyMemoDBOpenHelper helper;
    private TagDao tagDao;
    private DeckPoints deckPoint;
    private DeckPointsDao deckPointDao;
    private AchievementPointDao achPointDao;
    private TagPoints tagPoint;
    private TagPointsDao tagPointDao;
    private DailyPoints dailyPoint;
    private DailyPointsDao dailyPointDao;
    private String deckName;
    private List<Tag> tagList;

    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.multiple_choice_card_layout, container, false);

        GridLayout gridLayout = (GridLayout) view.findViewById(R.id.multiple_choice_grid);

        for (int i = 0; i < choices.size(); i++) {
            Button button = new Button(this.getContext());

            if (choices.get(i).isAnswer()) {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        doCorrectAnswer();
                        changeCardTask.run();
                    }
                });
            } else {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        doIncorrectAnswer();
                        changeCardTask.run();
                    }
                });
            }
            button.setText(choices.get(i).getCard().getAnswer());

            GridLayout.Spec spec = GridLayout.spec(GridLayout.UNDEFINED, GridLayout.FILL, 1);
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(spec, spec);

            button.setLayoutParams(layoutParams);
            gridLayout.addView(button);
        }

        final CardFragment.Builder field1CardFragmentBuilder = (CardFragment.Builder) getArguments().getSerializable(EXTRA_FIELD1_CARD_FRAGMENT);

        ViewPager field1CardPager = (ViewPager) view.findViewById(R.id.field1);
        field1CardPager.setAdapter(new FragmentStatePagerAdapter(
                getChildFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                return field1CardFragmentBuilder.build();
            }

            @Override
            public int getCount() {
                return 1;
            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        int cardId = getArguments().getInt(ARGUMENT_KEY_CARD_ID);
        String dbPath = getArguments().getString(ARGUMENT_KEY_DB_PATH);
        helper = AnyMemoDBOpenHelperManager.getHelper(dbPath);
        answerCard = helper.getCardDao().getById(cardId);
        choices = getMultipleChoices(dbPath);

        tagDao = helper.getTagDao();

        baseHelper = AnyMemoBaseDBOpenHelperManager.getHelper();
        deckName = FilenameUtils.getName(dbPath);

        achPointDao = baseHelper.getAchievementPointDao();
        deckPointDao = baseHelper.getDeckPointsDao();
        tagPointDao = baseHelper.getTagPointsDao();
        dailyPointDao = baseHelper.getDailyPointsDao();


        try{
            tagList = tagDao.queryForAll();
        }catch (SQLException e){
            e.printStackTrace();
        }

            deckPoint = deckPointDao.createOrReturn(deckName);
            dailyPoint = dailyPointDao.createOrReturn();

        if (activity instanceof StudyActivity) {
            changeCardTask = ((StudyActivity) activity).getOnCardCahngedListenerRunnable(answerCard);
        } else if (activity instanceof QuizActivity) {
            final QuizActivity quizActivity = (QuizActivity) activity;
            if (changeCardTask == null) {
                changeCardTask = new Runnable() {
                    @Override
                    public void run() {
                        quizActivity.getChangeCardTask(quizActivity, answerCard).execute();
                    }
                };
            }
        }
    }

    private void doCorrectAnswer() {
        AchievementPoint achPoint = new AchievementPoint();
        achPoint.setDeckPoints(deckPoint);
        achPoint.setDailyPoints(dailyPoint);

        for(Tag tag: tagList){

            tagPoint = tagPointDao.createOrReturn(tag.getName());

            achPoint.setTagPoints(tagPoint);

            try {
                achPointDao.create(achPoint);
            }catch (SQLException e){
                e.printStackTrace();
            }

            tagPointDao.update(tagPoint);
            tagPointDao.refresh(tagPoint);
        }

        dailyPointDao.update(dailyPoint);
        dailyPointDao.refresh(dailyPoint);

        deckPointDao.update(deckPoint);
        deckPointDao.refresh(deckPoint);

        Toast.makeText(MultipleChoiceCardFragment.this.getContext(), "Correct!"+(int)deckPoint.getSum(), Toast.LENGTH_SHORT).show();
    }

    private void doIncorrectAnswer() {
        Toast.makeText(MultipleChoiceCardFragment.this.getContext(), "Nope...", Toast.LENGTH_SHORT).show();
    }

    private List<Choice> getMultipleChoices(String dbPath) {
        List<Card> allCards = AnyMemoDBOpenHelperManager.getHelper(dbPath).getCardDao().queryForAll();
        List<Choice> choices = new ArrayList<>();

        int loopAmount = allCards.size() < 4 ? allCards.size() : 4;
        boolean answerHasBeenAdded = false;
        for (int i = 0; i < loopAmount; i++) {
            double random = Math.random();
            Choice choice;

            if (!answerHasBeenAdded && (random <= 0.25 || i == 3)) {
                choice = new Choice(answerCard, true);
                answerHasBeenAdded = true;
            } else {
                Card card = getRandomCardNotInList(allCards, choices);
                choice = new Choice(card, false);
            }

            choices.add(choice);
        }

        return choices;
    }

    private Card getRandomCardNotInList(List<Card> allCards, List<Choice> choices) {
        Card randomCard = getRandomCardFromList(allCards);

        while (containsCardById(choices, randomCard) || randomCard.getId().intValue() == answerCard.getId().intValue()) {
            randomCard = getRandomCardFromList(allCards);
        }

        return randomCard;
    }

    private boolean containsCardById(List<Choice> choices, Card card) {
        for (Choice choice : choices) {
            if (choice.getCard().getId().intValue() == card.getId().intValue()) {
                return true;
            }
        }
        return false;
    }

    private Card getRandomCardFromList(List<Card> cards) {
        int randomIndex = (int) (Math.random() * cards.size());
        return cards.get(randomIndex);
    }

    class Choice {
        private Card card;
        private boolean isAnswer;

        Choice(Card card, boolean isAnswer) {
            this.card = card;
            this.isAnswer = isAnswer;
        }

        public Card getCard() {
            return card;
        }

        public boolean isAnswer() {
            return isAnswer;
        }
    }
}
