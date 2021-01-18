package Strengths;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Strengths implements Parcelable{
    private final String title;
    private final String question;
    private final String identity;
    private int answer;
    static int progress = 2;

    public Strengths(String Identity, String Title, String Question, int Answer) {
        title = Title;  
        question = Question;
        identity = Identity;
        answer = Answer;
    }

    protected Strengths(Parcel in) {
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

    public String getTitle() {
        return title;
    }

    public String getQuestion(){
        return question;
    }

    public String getIdentity() { return identity; }

    public int getAnswer() { return answer; }

    public int setAnswer(int Answer) {this.answer = Answer; return Answer;}

    public static ArrayList<Strengths> getModList(){
        ArrayList<Strengths> questionList = new ArrayList<>();
        //MOD
        /*questionList.add(new Strengths("q_mod1", "MOD","Du er ikke bange for at kaste dig ud i nye udfordringer. Du siger din mening og tør at gå dine egne veje.", progress));
        questionList.add(new Strengths("q_mod2", "VEDHOLDENHED","Du giver sjældent op. Når du er gået i gang med noget, så gør du det også færdigt. Også selvom det er svært!", progress));
        questionList.add(new Strengths("q_mod3","ENTUSIASME"," Når du bliver optaget af noget, går du 100 % ind i det. Du er begejstret og engageret – du har drive!.", progress));
        */questionList.add(new Strengths("q_mod4","ÆRLIGHED","Du taler altid sandt og du gør sjældent ting, der går imod dine værdier og principper.", progress));
        return questionList;
    }

    public static ArrayList<Strengths> getNysList(){
        ArrayList<Strengths> questionList = new ArrayList<>();
        //Nysgerrighed
        /*questionList.add(new Strengths("q_nys1", "KREATIVITET","Du tænker tit ‘Hmm, måske kan det gøres på en anden eller bedre måde?’ Du er god til at få idéer.", progress));
        questionList.add(new Strengths("q_nys2", "NYSGERRIGHED","Du stiller altid spørgsmål og er god til fordybe dig. Ligesom Spørge Jørgen, der altid spørger “hvorfor dit og hvorfor dat”.", progress));
        questionList.add(new Strengths("q_nys3", "VIDEBEGÆR","Du elsker at lære nye ting! Du suger viden til dig både i skolen og i fritiden og undersøger hvorfor ting virker, som de gør.", progress));
        */questionList.add(new Strengths("q_nys4", "VISDOM","Dine venner kommer ofte til dig for at få gode råd. Du er nemlig god til at se nye perspektiver hele tiden.", progress));

        return questionList;
    }

    public static ArrayList<Strengths> getBesList(){
        ArrayList<Strengths> questionList = new ArrayList<>();
        //Beskedenhed
        /*questionList.add(new Strengths("q_bes1", "BESKEDENHED","Du er ikke “Se mig! Se mig!”-typen. Heller ikke når du er for sej og alting kører for dig.", progress));
        questionList.add(new Strengths("q_bes2", "OMTANKE","Du tænker dig altid om, inden du gør eller siger noget. Du er meget grundig og træffer altid fornuftige valg.", progress));
        */questionList.add(new Strengths("q_bes3", "SELVKONTROL","Du har stor selvdisciplin. Du tager ikke beslutninger med dine følelser og impulser.", progress));

        return questionList;
    }

    public static ArrayList<Strengths> getTakList(){
        ArrayList<Strengths> questionList = new ArrayList<>();
        //Taknemmelighed
        /*questionList.add(new Strengths("q_tak1", "VÆRDSÆTTELSE","Du får altid øje på det smukke i livet. Om det så er et flot landskab eller en perfekt kamp i sport, så er du helt solgt.", progress));
        questionList.add(new Strengths("q_tak2", "TAKNEMMELIGHED","Du sætter pris på både det store og de små ting i livet og ‘Tak’ er et ord, du bruger rigtig tit. Folk omkring dig ved, at de betyder noget for dig.", progress));
        questionList.add(new Strengths("q_tak3", "OPTIMISME","Du ser det gode i livet – også når tingene ikke går din vej. Du ser positivt på fremtiden og tænker ‘Det skal nok gå’.", progress));
        questionList.add(new Strengths("q_tak4", "HUMOR","Du tager ikke tingene så tungt. Du er god til at se ting fra den skæve vinkel og skaber smil og glæde omkring dig.", progress));
        */questionList.add(new Strengths("q_tak5", "SPIRITUALITET","Du tænker, at du er en del af noget større i livet. Du er åben for, at livet kan hænge sammen på mange måder.", progress));

        return questionList;
    }

    public static ArrayList<Strengths> getSamList(){
        ArrayList<Strengths> questionList = new ArrayList<>();
        //Samarbejde
        /*questionList.add(new Strengths("q_sam1", "SAMARBEJDE","Andre kan altid regne med dig. Du er god til at få gruppearbejde til at fungere og nyder fællesskaber.", progress));
        questionList.add(new Strengths("q_sam2", "RETFÆRDIGHED","Det er vigtigt for dig, at alle bliver behandlet ordentligt - også selvom du ikke er enig med dem.", progress));
        */questionList.add(new Strengths("q_sam3", "LEDERSKAB","Du kan godt lide at gå forrest og tager gerne styringen i en gruppe. Andre lytter til dig og kan godt lide dine idéer.", progress));

        return questionList;
    }

    public static ArrayList<Strengths> getSocList(){
        ArrayList<Strengths> questionList = new ArrayList<>();
        //Social Intelligens
        /*questionList.add(new Strengths("q_soc1", "SOCIAL INTELLIGENS","Du er god til at sætte dig ind i andres tanker og idéer. Folk omkring dig føler sig godt tilpas i dit selskab.", progress));
        questionList.add(new Strengths("q_soc2", "OMSORG","Du står altid klar, når folk omkring dig har brug for hjælp. Du tænker på andre og hjælper, når der er brug for det.", progress));
        */questionList.add(new Strengths("q_soc3", "RELATIONER","Du er god til at lære andre at kende. Det betyder meget for dig at være tæt på folk, du holder af.", progress));

        return questionList;
    }

    @Override
    public int describeContents() {
        return 0;
    }
//
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(question);
        dest.writeString(identity);
        dest.writeInt(answer);
    }
}