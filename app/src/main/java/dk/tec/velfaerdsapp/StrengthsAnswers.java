package dk.tec.velfaerdsapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class StrengthsAnswers implements Parcelable{
    private final int icon;
    private final String title;
    private final String question;
    private final String identity;
    private int answer;
    private boolean good;

    public StrengthsAnswers(String Identity, int Icon, String Title, String Question, int Answer, boolean Good) {
        icon = Icon;
        title = Title;
        question = Question;
        identity = Identity;
        answer = Answer;
        good = Good;
    }

    protected StrengthsAnswers(Parcel in) {
        icon = in.readInt();
        title = in.readString();
        question = in.readString();
        identity = in.readString();
        answer = in.readInt();
    }

    public static final Creator<Strengths> CREATOR = new Creator<Strengths>() {
        @Override
        public Strengths createFromParcel(Parcel in) {
            return new Strengths(in);
        }

        @Override
        public Strengths[] newArray(int size) {
            return new Strengths[size];
        }
    };

    public int getIcon() {
        return icon;
    }

    public String getTitle() {
        return title;
    }

    public String getQuestion(){
        return question;
    }

    public String getIdentity() { return identity; }

    public int getAnswer() { return answer; }

    public boolean isGood() { return good; }

    @Override
    public int describeContents() {
        return 0;
    }
    //
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(icon);
        dest.writeString(title);
        dest.writeString(question);
        dest.writeString(identity);
        dest.writeInt(answer);
    }
}