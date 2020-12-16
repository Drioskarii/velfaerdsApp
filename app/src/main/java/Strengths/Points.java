package Strengths;

import java.util.Comparator;

public class Points {
    private final String title;
    private final String question;
    private final int points;
    private final int icon;

    public Points(String Title, String Question, int Points, int Icon) {
        title = Title;
        question = Question;
        points = Points;
        icon = Icon;
    }

    public String getTitle() { return title; }

    public String getQuestion() { return question; }

    public int getPoints() {
        return points;
    }

    public int getIcon() {
        return icon;
    }
}
