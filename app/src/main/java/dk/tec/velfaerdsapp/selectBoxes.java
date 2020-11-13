package dk.tec.velfaerdsapp;

public class selectBoxes {
    private final int mImageIcon;
    private final String txtQuestion;
    private final String txtAnswer;

    public selectBoxes(int mImageIcon, String txtQuestion, String txtAnswer) {
        this.mImageIcon = mImageIcon;
        this.txtQuestion = txtQuestion;
        this.txtAnswer = txtAnswer;
    }

    public int getmImageIcon() {
        return mImageIcon;
    }

    public String getTxtQuestion() {
        return txtQuestion;
    }

    public String getTxtAnswer() {
        return txtAnswer;
    }
}

