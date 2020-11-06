package dk.tec.velfaerdsapp;

public class selectBoxes {
    private final int mImageIcon;
    private final String mTxtExplanation;

    public selectBoxes(int imageIcon, String txtExplanation){
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

