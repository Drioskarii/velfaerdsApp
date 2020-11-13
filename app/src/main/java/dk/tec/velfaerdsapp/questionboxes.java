package dk.tec.velfaerdsapp;

import android.widget.SeekBar;

public class questionboxes {
    private final int mImageIcon;
    private final String mTxtExplanation;

    public questionboxes(int imageIcon, String txtExplanation){
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
