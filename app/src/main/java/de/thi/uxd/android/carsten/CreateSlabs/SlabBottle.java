package de.thi.uxd.android.carsten.CreateSlabs;

import android.annotation.SuppressLint;

import de.thi.uxd.android.carsten.R;

@SuppressLint("AppCompatCustomView")
public class SlabBottle {
    private String bottleType;
    private final String empty = "empty";
    private int drawableID = R.drawable.drink_slot_empty;

    public SlabBottle() {
        setEmpty();
    }

    public SlabBottle(SlabBottle slabBottle) {
        this.bottleType = slabBottle.bottleType;
        this.drawableID = slabBottle.drawableID;
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
    }


    public void setEmpty() {
        bottleType = empty;
        drawableID = R.drawable.drink_slot_empty;
    }
    public boolean isEmpty() {
        return bottleType.equals(empty);
    }
}