package Adapter;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import java.util.List;

import data.MenuItemData;
import dk.tec.velfaerdsapp.R;
import github.hellocsl.cursorwheel.CursorWheelLayout;

public class wheelCycleAdapter extends CursorWheelLayout.CycleWheelAdapter {

private Context mContext;
private List<MenuItemData> menuItems;
private LayoutInflater inflater;
private int gravity;

    public wheelCycleAdapter(Context mContext, List<MenuItemData> menuItems) {
        this.mContext = mContext;
        this.menuItems = menuItems;
        gravity = Gravity.CENTER;
        inflater = LayoutInflater.from(mContext);
    }

    public wheelCycleAdapter(Context mContext, List<MenuItemData> menuItems, int gravity) {
        this.mContext = mContext;
        this.menuItems = menuItems;
        this.gravity = gravity;
        inflater = LayoutInflater.from(mContext);

    }

    public int getCount(){
            return menuItems.size();
        }

        public View getView(View parent, int position){
            MenuItemData itemData = getItem(position);
            View root = inflater.inflate(R.layout.wheellayout, null,false);
            TextView textView = (TextView)root.findViewById(R.id.wheelMenuItem);
            textView.setVisibility(View.VISIBLE);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
            textView.setText(itemData.mTitle);
            if(textView.getLayoutParams() instanceof FrameLayout.LayoutParams)
                ((FrameLayout.LayoutParams) textView.getLayoutParams()).gravity = gravity;
            if (position == 4)
                textView.setTextColor(ActivityCompat.getColor(mContext,R.color.red));
            return root;
        }

        public MenuItemData getItem(int position){
            return menuItems.get(position);
        }

    }

