package Strengths;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

public class Points implements Parcelable {
    private final String title;
    private final String question;
    private final int points;
    private final int icon;
    private final int maxPoints;

    public Points(String Title, String Question, int Points, int Icon, int MaxPoints) {
        title = Title;
        question = Question;
        points = Points;
        icon = Icon;
        maxPoints = MaxPoints;
    }

    protected Points(Parcel in) {
        title = in.readString();
        question = in.readString();
        points = in.readInt();
        icon = in.readInt();
        maxPoints = in.readInt();
    }

    public static final Creator<Points> CREATOR = new Creator<Points>() {
        @Override
        public Points createFromParcel(Parcel in) {
            return new Points(in);
        }

        @Override
        public Points[] newArray(int size) {
            return new Points[size];
        }
    };

    public String getTitle() { return title; }

    public String getQuestion() { return question; }

    public int getPoints() {
        return points;
    }

    public int getIcon() {
        return icon;
    }

    public int getMaxPoints() { return maxPoints; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(title);
        dest.writeString(question);
        dest.writeInt(points);
        dest.writeInt(icon);
        dest.writeInt(maxPoints);
    }

}
