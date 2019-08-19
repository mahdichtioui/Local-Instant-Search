package com.mahdichtioui.localsearchbinding.Model.Entity;

public class Mood {
    private String sentiment;
    private int emoji;

    public Mood(String sentiment, int emoji) {
        this.sentiment = sentiment;
        this.emoji = emoji;
    }

    public String getSentiment() {
        return sentiment;
    }

    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }

    public int getEmoji() {
        return emoji;
    }

    public void setEmoji(int emoji) {
        this.emoji = emoji;
    }
}
