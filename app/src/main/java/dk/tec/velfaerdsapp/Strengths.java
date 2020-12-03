package dk.tec.velfaerdsapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Strengths implements Parcelable {
    private final int icon;
    private final String title;
    private final String question;
    private final String identity;
    private int answer;
    static int progress = 3;

    public Strengths(String Identity, int Icon, String Title, String Question, int Answer) {
        icon = Icon;
        title = Title;  
        question = Question;
        identity = Identity;
        answer = Answer;
    }

    protected Strengths(Parcel in) {
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

    public int setAnswer(int Answer) {this.answer = Answer; return Answer;}

    public static ArrayList<Strengths> getQuestionList(){
        ArrayList<Strengths> questionList = new ArrayList<>();
        //MOD
        questionList.add(new Strengths("1", R.drawable.iconmod, "MOD","Du er ikke bange for at kaste dig ud i nye udfordringer. Du siger din mening og tør at gå dine egne veje.", progress));
        questionList.add(new Strengths("2", R.drawable.iconmod, "VEDHOLDENHED","Du giver sjældent op. Når du er gået i gang med noget, så gør du det også færdigt. Også selvom det er svært!", progress));
        questionList.add(new Strengths("3", R.drawable.iconmod, "ENTUSIASME"," Når du bliver optaget af noget, går du 100 % ind i det. Du er begejstret og engageret – du har drive!.", progress));
        questionList.add(new Strengths("4", R.drawable.iconmod, "ÆRLIGHED","Du taler altid sandt og du gør sjældent ting, der går imod dine værdier og principper.", progress));
        //Nysgerrighed
        questionList.add(new Strengths("5", R.drawable.iconnysgerrig, "KREATIVITET","Du tænker tit ‘Hmm, måske kan det gøres på en anden eller bedre måde?’ Du er god til at få idéer.", progress));
        questionList.add(new Strengths("6", R.drawable.iconnysgerrig, "NYSGERRIGHED","Du stiller altid spørgsmål og er god til fordybe dig. Ligesom Spørge Jørgen, der altid spørger “hvorfor dit og hvorfor dat”.", progress));
        questionList.add(new Strengths("7", R.drawable.iconnysgerrig, "VIDEBEGÆR","Du elsker at lære nye ting! Du suger viden til dig både i skolen og i fritiden og undersøger hvorfor ting virker, som de gør.", progress));
        questionList.add(new Strengths("8", R.drawable.iconnysgerrig, "VISDOM","Dine venner kommer ofte til dig for at få gode råd. Du er nemlig god til at se nye perspektiver hele tiden.", progress));
        //Beskedenhed
        questionList.add(new Strengths("9", R.drawable.iconbeskedenhed, "BESKEDENHED","Du er ikke “Se mig! Se mig!”-typen. Heller ikke når du er for sej og alting kører for dig.", progress));
        questionList.add(new Strengths("10", R.drawable.iconbeskedenhed, "OMTANKE","Du tænker dig altid om, inden du gør eller siger noget. Du er meget grundig og træffer altid fornuftige valg.", progress));
        questionList.add(new Strengths("11", R.drawable.iconbeskedenhed, "SELVKONTROL","Du har stor selvdisciplin. Du tager ikke beslutninger med dine følelser og impulser.", progress));
        //Taknemmelighed
        questionList.add(new Strengths("12", R.drawable.icontaknemmelighed, "VÆRDSÆTTELSE","Du får altid øje på det smukke i livet. Om det så er et flot landskab eller en perfekt kamp i sport, så er du helt solgt.", progress));
        questionList.add(new Strengths("13", R.drawable.icontaknemmelighed, "TAKNEMMELIGHED","Du sætter pris på både det store og de små ting i livet og ‘Tak’ er et ord, du bruger rigtig tit. Folk omkring dig ved, at de betyder noget for dig.", progress));
        questionList.add(new Strengths("14", R.drawable.icontaknemmelighed, "OPTIMISME","Du ser det gode i livet – også når tingene ikke går din vej. Du ser positivt på fremtiden og tænker ‘Det skal nok gå’.", progress));
        questionList.add(new Strengths("15", R.drawable.icontaknemmelighed, "HUMOR","Du tager ikke tingene så tungt. Du er god til at se ting fra den skæve vinkel og skaber smil og glæde omkring dig.", progress));
        questionList.add(new Strengths("16", R.drawable.icontaknemmelighed, "SPIRITUALITET","Du tænker, at du er en del af noget større i livet. Du er åben for, at livet kan hænge sammen på mange måder.", progress));
        //Samarbejde
        questionList.add(new Strengths("17", R.drawable.iconsamarbejde, "SAMARBEJDE","Andre kan altid regne med dig. Du er god til at få gruppearbejde til at fungere og nyder fællesskaber.", progress));
        questionList.add(new Strengths("18", R.drawable.iconsamarbejde, "RETFÆRDIGHED","Det er vigtigt for dig, at alle bliver behandlet ordentligt - også selvom du ikke er enig med dem.", progress));
        questionList.add(new Strengths("19", R.drawable.iconsamarbejde, "LEDERSKAB","Du kan godt lide at gå forrest og tager gerne styringen i en gruppe. Andre lytter til dig og kan godt lide dine idéer.", progress));
        //Social Intelligens
        questionList.add(new Strengths("20", R.drawable.iconsocialintelligens, "SOCIAL INTELLIGENS","Du er god til at sætte dig ind i andres tanker og idéer. Folk omkring dig føler sig godt tilpas i dit selskab.", progress));
        questionList.add(new Strengths("21", R.drawable.iconsocialintelligens, "OMSORG","Du står altid klar, når folk omkring dig har brug for hjælp. Du tænker på andre og hjælper, når der er brug for det.", progress));
        questionList.add(new Strengths("22", R.drawable.iconsocialintelligens, "RELATIONER","Du er god til at lære andre at kende. Det betyder meget for dig at være tæt på folk, du holder af.", progress));
        return questionList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(icon);
        dest.writeString(title);
        dest.writeString(question);
        dest.writeString(identity);
        dest.writeInt(answer);
    }
}