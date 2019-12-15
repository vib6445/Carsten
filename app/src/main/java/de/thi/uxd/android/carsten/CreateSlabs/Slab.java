package de.thi.uxd.android.carsten.CreateSlabs;

public class Slab {

    private String slabType;

    private SlabBottle[] slabBottles;
    private int[] drawables;




    Slab(int[] fragmentDrawables, String slabType, int size) {
        this.slabType = slabType;

        // Creates the array of empty SlabBottles with the slab specific amount of slots
        slabBottles = new SlabBottle[size];


        drawables = fragmentDrawables;

        // Fills the array of empty SlabBottles
        for (int i = 0; i < slabBottles.length; i++) {
            slabBottles[i] = new SlabBottle();
        }
    }

    Slab(Slab that) {
        this.slabType = that.slabType;
        this.slabBottles = new SlabBottle[that.getSlabBottles().length];
        for (int i = 0; i < slabBottles.length; i++) {
            slabBottles[i] = new SlabBottle(that.getSlabBottles()[i]);
        }
        this.drawables = that.drawables;
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
