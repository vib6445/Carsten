package de.thi.uxd.android.carsten.CreateSlabs;

import android.app.Activity;
import android.content.Context;

import de.thi.uxd.android.carsten.BuildConfig;


public class Slab {

    private Activity activity;

    private String slabType;

    private SlabBottle[] slabBottles;
    private int[] drawables;




    Slab(Context context, Activity activity, int[] resIDs, int[] fragmentDrawables, String[] _typesOfDrinks, String slabType) {

        this.activity = activity;
        this.slabType = slabType;

        // Creates the array of empty SlabBottles with the slab specific amount of slots
        slabBottles = new SlabBottle[resIDs.length];

        // Receives all ImageButtons from the Fragment within the slab
        for (int i = 0; i < resIDs.length; i++) {
            int id = i + 1;
            resIDs[i] = activity.getResources().getIdentifier(slabType + "Button" + id, "id", BuildConfig.APPLICATION_ID);
        }
        drawables = fragmentDrawables;



        fillSlabWithEmptyBottles(context, resIDs);
    }

    private void fillSlabWithEmptyBottles(Context context, int[] resIDs) {
        // Fills the array of empty SlabBottles
        for (int i = 0; i < slabBottles.length; i++) {
            slabBottles[i] = new SlabBottle(context, activity, resIDs[i]);
        }
    }

    // Function returns a boolean so the fragment knows if the amount of bottles in the slab and the list should INCREASE
    public boolean addToSlab(String typeOfBottle, int listNumber) {
        for (SlabBottle slabBottle : slabBottles) {

            // Only fills the slab if there's a free slot in the slab
            if (slabBottle.isEmpty()) {
                slabBottle.setBottleType(typeOfBottle, drawables[listNumber]);
                return true;
            }
        }
        return false;
    }

    // Function returns a boolean so the fragment knows if the amount of bottles in the slab and the list should DECREASE
    public boolean removeFromSlab(String typeOfBottle) {
        for (SlabBottle slabBottle : slabBottles) {

            // Searches for the first occurrence of a bottle in the slab
            if (slabBottle != null && slabBottle.getBottleType().equals(typeOfBottle)) {

                // Removes the first found bottle from the slab
                slabBottle.setEmpty();

                // Sorts the slab, so that free slots are always at the end of the slab
                sortSlab();

                return true;
            }
        }
        return false;
    }

    private void sortSlab() {
        for (int i = 0; i < slabBottles.length; i++) {
            if (slabBottles[i].isEmpty() && i+1 < slabBottles.length) {
                String tempBottleType = slabBottles[i].getBottleType();
                int tempDrawable = slabBottles[i].getDrawableID();
                slabBottles[i].setBottleType(slabBottles[i+1].getBottleType(), slabBottles[i+1].getDrawableID());
                slabBottles[i+1].setBottleType(tempBottleType, tempDrawable);
            }
            if (slabBottles[i].isEmpty()) {
                slabBottles[i].setEmpty();
            }
        }
    }

    public boolean isFull() {
        for (SlabBottle slabBottle : slabBottles) {
            if (slabBottle.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public String getType() {
        return slabType;
    }
    public SlabBottle[] getSlabBottles() {
        return slabBottles;
    }

}
