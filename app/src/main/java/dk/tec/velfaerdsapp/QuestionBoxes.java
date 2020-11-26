package dk.tec.velfaerdsapp;


public class QuestionBoxes {
    private final int mImageIcon;
    private final String mTxtExplanation;

    public QuestionBoxes(int imageIcon, String txtExplanation){
        mImageIcon = imageIcon;
        mTxtExplanation = txtExplanation;
    }

    public int getImageIcon(){
        return mImageIcon;
    }

    public String getTxtExplanation(){
        return mTxtExplanation;
    }

}
