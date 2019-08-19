package com.mahdichtioui.localsearchbinding.Model.DataManager;

import com.mahdichtioui.localsearchbinding.Model.Entity.Mood;
import com.mahdichtioui.localsearchbinding.R;

import java.util.ArrayList;
import java.util.List;

public class MoodDataManager {

    public MoodDataManager() {
    }

    public List<Mood> initValues() {
        List<Mood> list = new ArrayList<>();

        list.add(new Mood("Very Happy", R.drawable.ic_mood));
        list.add(new Mood("Sad", R.drawable.ic_mood_bad));
        list.add(new Mood("Nothing", R.drawable.ic_sentiment_neutral));
        list.add(new Mood("Very Dissatisfied", R.drawable.ic_sentiment_very_dissatisfied));
        list.add(new Mood("Dissatisfied", R.drawable.ic_sentiment_dissatisfied));
        list.add(new Mood("Satisfied", R.drawable.ic_sentiment_satisfied));
        list.add(new Mood("Very Satisfied", R.drawable.ic_sentiment_very_satisfied));

        return list;
    }
}
