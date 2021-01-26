package Strengths;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

//////////////////////////////////////////////////////////
// Points bliver brugt som et Interface for at sende
// Data videre med Intent.
// Points implementere Parcelable for at kunne sende data
// dataen som en arrayliste med objekter.
//////////////////////////////////////////////////////////

public class Points implements Parcelable {
    //Title indeholder titlen (Mod, Nysgerrighed, Beskedenhed osv.)
    private final String title;
    //Description indeholder beskrivelsen af en af de 6 kategorierne
    private final String description;
    //Points indeholder det samlede antal af point personen har opnået.
    private final int points;
    //Icon indeholder det ikon der matcher en af de 6 hovedkategorier
    private final int icon;

    //Default Constructor for Points
    public Points(String Title, String Description, int Points, int Icon) {
        title = Title;
        description = Description;
        points = Points;
        icon = Icon;
    }

    //Parcelable Constructor for Points
    protected Points(Parcel in) {
        title = in.readString();
        description = in.readString();
        points = in.readInt();
        icon = in.readInt();
    }

    //Parcelable Creator for Points, denne metode er implementeret som default
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

    //denne metode returner Title
    public String getTitle() { return title; }

    //denne metode returner Description
    public String getDescription() { return description; }

    //denne metode returner Points
    public int getPoints() {
        return points;
    }

    //denne metode returner Icon
    public int getIcon() {
        return icon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(title);
        dest.writeString(description);
        dest.writeInt(points);
        dest.writeInt(icon);
    }

}
