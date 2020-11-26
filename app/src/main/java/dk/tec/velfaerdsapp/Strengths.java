package dk.tec.velfaerdsapp;

import java.util.ArrayList;

public class Strengths {
    private final int icon;
    private final String title;
    private final String question;

    public Strengths(int Icon, String Title, String Question) {
        icon = Icon;
        title = Title;
        question = Question;
    }

    public int getIcon() {
        return icon;
    }

    public String getTitle() {
        return title;
    }

    public String getQuestion(){
        return question;
    }

    public static ArrayList<Strengths> getQuestionList(){
        ArrayList<Strengths> questionList = new ArrayList<>();
        //MOD
        questionList.add(new Strengths(R.drawable.intellectual, "MOD","Du er ikke bange for at kaste dig ud i nye udfordringer. Du siger din mening og tør at gå dine egne veje."));
        questionList.add(new Strengths(R.drawable.people_person, "VEDHOLDENHED","Du giver sjældent op. Når du er gået i gang med noget, så gør du det også færdigt. Også selvom det er svært!"));
        questionList.add(new Strengths(R.drawable.sharp_brained, "ENTUSIASME"," Når du bliver optaget af noget, går du 100 % ind i det. Du er begejstret og engageret – du har drive!."));
        questionList.add(new Strengths(R.drawable.intellectual, "ÆRLIGHED","Du taler altid sandt og du gør sjældent ting, der går imod dine værdier og principper."));
        //Nysgerrighed
        questionList.add(new Strengths(R.drawable.people_person, "KREATIVITET","Du tænker tit ‘Hmm, måske kan det gøres på en anden eller bedre måde?’ Du er god til at få idéer."));
        questionList.add(new Strengths(R.drawable.sharp_brained, "NYSGERRIGHED","Du stiller altid spørgsmål og er god til fordybe dig. Ligesom Spørge Jørgen, der altid spørger “hvorfor dit og hvorfor dat”."));
        questionList.add(new Strengths(R.drawable.people_person, "VIDEBEGÆR","Du elsker at lære nye ting! Du suger viden til dig både i skolen og i fritiden og undersøger hvorfor ting virker, som de gør."));
        questionList.add(new Strengths(R.drawable.sharp_brained, "VISDOM","Dine venner kommer ofte til dig for at få gode råd. Du er nemlig god til at se nye perspektiver hele tiden."));
        //Beskedenhed
        questionList.add(new Strengths(R.drawable.sharp_brained, "BESKEDENHED","Du er ikke “Se mig! Se mig!”-typen. Heller ikke når du er for sej og alting kører for dig."));
        questionList.add(new Strengths(R.drawable.sharp_brained, "OMTANKE","Du tænker dig altid om, inden du gør eller siger noget. Du er meget grundig og træffer altid fornuftige valg."));
        questionList.add(new Strengths(R.drawable.sharp_brained, "SELVKONTROL","Du har stor selvdisciplin. Du tager ikke beslutninger med dine følelser og impulser."));
        //Taknemmelighed
        questionList.add(new Strengths(R.drawable.sharp_brained, "VÆRDSÆTTELSE","Du får altid øje på det smukke i livet. Om det så er et flot landskab eller en perfekt kamp i sport, så er du helt solgt."));
        questionList.add(new Strengths(R.drawable.sharp_brained, "TAKNEMMELIGHED","Du sætter pris på både det store og de små ting i livet og ‘Tak’ er et ord, du bruger rigtig tit. Folk omkring dig ved, at de betyder noget for dig."));
        questionList.add(new Strengths(R.drawable.sharp_brained, "OPTIMISME","Du ser det gode i livet – også når tingene ikke går din vej. Du ser positivt på fremtiden og tænker ‘Det skal nok gå’."));
        questionList.add(new Strengths(R.drawable.sharp_brained, "HUMOR","Du tager ikke tingene så tungt. Du er god til at se ting fra den skæve vinkel og skaber smil og glæde omkring dig."));
        questionList.add(new Strengths(R.drawable.sharp_brained, "SPIRITUALITET","Du tænker, at du er en del af noget større i livet. Du er åben for, at livet kan hænge sammen på mange måder."));
        //Samarbejde
        questionList.add(new Strengths(R.drawable.sharp_brained, "SAMARBEJDE","Andre kan altid regne med dig. Du er god til at få gruppearbejde til at fungere og nyder fællesskaber."));
        questionList.add(new Strengths(R.drawable.sharp_brained, "RETFÆRDIGHED","Det er vigtigt for dig, at alle bliver behandlet ordentligt - også selvom du ikke er enig med dem."));
        questionList.add(new Strengths(R.drawable.sharp_brained, "LEDERSKAB","Du kan godt lide at gå forrest og tager gerne styringen i en gruppe. Andre lytter til dig og kan godt lide dine idéer."));
        //Social Intelligens
        questionList.add(new Strengths(R.drawable.sharp_brained, "SOCIAL INTELLIGENS","Du er god til at sætte dig ind i andres tanker og idéer. Folk omkring dig føler sig godt tilpas i dit selskab."));
        questionList.add(new Strengths(R.drawable.sharp_brained, "OMSORG","Du står altid klar, når folk omkring dig har brug for hjælp. Du tænker på andre og hjælper, når der er brug for det."));
        questionList.add(new Strengths(R.drawable.sharp_brained, "RELATIONER","Du er god til at lære andre at kende. Det betyder meget for dig at være tæt på folk, du holder af."));

        return questionList;
    }
}

