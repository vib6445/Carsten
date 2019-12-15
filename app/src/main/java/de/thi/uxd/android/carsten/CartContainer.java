package de.thi.uxd.android.carsten;

import java.io.Serializable;
import java.util.ArrayList;

import de.thi.uxd.android.carsten.CreateSlabs.Slab;

public class CartContainer implements Serializable {

    private static ArrayList<Slab> slabList;

    public CartContainer() {
        slabList = new ArrayList<Slab>();
    }

    public ArrayList getSlabList() {
        return slabList;
    }

    public static void addToCart (Slab slab) {
        slabList.add(slab);
    }

}
