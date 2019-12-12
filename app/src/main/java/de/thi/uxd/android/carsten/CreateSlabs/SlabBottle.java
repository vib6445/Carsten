package de.thi.uxd.android.carsten.CreateSlabs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.widget.ImageButton;

import de.thi.uxd.android.carsten.R;

@SuppressLint("AppCompatCustomView")
public class SlabBottle extends ImageButton {
    private String bottleType;
    private ImageButton imageButton;
    private final String empty = "empty";
    private int drawableID = R.drawable.drink_slot_empty;

    public SlabBottle(Context context, Activity activity, int imgBtResId) {
        super(context);
        imageButton = (ImageButton) activity.findViewById(imgBtResId);
        setEmpty();
    }

    public String getBottleType() {
        return bottleType;
    }
    public int getDrawableID() {
        return drawableID;
    }


    public void setBottleType(String strBottleType, int _drawableID) {
        this.bottleType = strBottleType;
        this.drawableID = _drawableID;
        this.imageButton.setImageResource(drawableID);
    }


    public void setEmpty() {
        bottleType = empty;
        imageButton.setImageResource(R.drawable.drink_slot_empty);
    }
    public boolean isEmpty() {
        return bottleType.equals(empty);
    }
}