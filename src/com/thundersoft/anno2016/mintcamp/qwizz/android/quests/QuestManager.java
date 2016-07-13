package com.thundersoft.anno2016.mintcamp.qwizz.android.quests;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.thundersoft.anno2016.mintcamp.qwizz.quests.GeneralQuest;
import com.thundersoft.anno2016.mintcamp.qwizz.quests.InvalidArgumentException;
import com.thundersoft.anno2016.mintcamp.qwizz.quests.MCQuest;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author fgast34
 * @version ??? - 12.07.2016.
 */
public class QuestManager extends Activity {

    private List<GeneralQuest> quests;
    private int score;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        quests = Arrays.asList(new GeneralQuest[]{
                new MCQuest(new String[]{"10:00 Uhr", "10:30 Uhr", "11:00 Uhr"}, 2, "Um wie viel Uhr startet das SummerCamp?"),
//            new EstQuest(150,"Wieviele Minuten arbeiten wir hier nun schon?",10),
                new MCQuest(new String[]{"Deutschland", "Frankreich", "Portugal"}, 3, "Wer ist Europameister 2016?"),
                new MCQuest(new String[]{"Wirtschaftsinformatik", "Volkswirtschaftslehre", "Betriebswirtschaftslehre"}, 2, "Welches Fach kann man an der FHDW nicht studieren?")
        });

        try {
            startQuiz(getIntent().getBooleanExtra("isTimeRace", false));
        } catch (InvalidArgumentException e) {
        }
    }

    public GeneralQuest getQuest(int index) {
        return quests.get(index);
    }

    public int getNumberOfQuests() {
        return quests.size();
    }

    public int getScore() {
        return score;
    }

    public GeneralQuest getNext() {
        GeneralQuest q;
        boolean hasNew = false;
        for (GeneralQuest qu : quests) {
            if (!qu.hasUserAnswered()) hasNew = true;
        }
        if (!hasNew) {
            return null;
        }

        do {
            q = getQuest(new Random().nextInt(getNumberOfQuests()));
        } while (q.hasUserAnswered());
        return q;
    }

    private void startQuiz(boolean isTimeRace) throws InvalidArgumentException {
        GeneralQuest quest;
        while ((quest = getNext()) != null) {
            Intent i;
            if (quest instanceof MCQuest)
                i = new Intent(this, MCQActivity.class);
            else throw new InvalidArgumentException("Only quests of the type 'MCQuest' are implemented", quest);
            i.putExtra("givenQuest", quest);
            this.startActivity(i);
        }
    }

}