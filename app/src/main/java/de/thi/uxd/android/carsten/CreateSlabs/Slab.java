package de.thi.uxd.android.carsten.CreateSlabs;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import de.thi.uxd.android.carsten.BuildConfig;
import de.thi.uxd.android.carsten.R;


public class Slab {

    private Activity activity;

    private SlabBottle[] slabBottles;
    private int[] drawables;
    private String[] typesOfDrinks;

    private TextView amountInSlab;



    public Slab(Context context, Activity _activity, int[] resIDs, int[] fragmentDrawables, String[] _typesOfDrinks) {

        this.activity = _activity;
        this.typesOfDrinks = _typesOfDrinks;

        amountInSlab = (TextView) activity.findViewById(R.id.numberOfBottles);

        // Creates the array of empty SlabBottles with the slab specific amount of slots
        slabBottles = new SlabBottle[resIDs.length];

        // Receives all ImageButtons from the Fragment within the slab
        for (int i = 0; i < resIDs.length; i++) {
            int id = i + 1;
            resIDs[i] = activity.getResources().getIdentifier("imageButton" + id, "id", BuildConfig.APPLICATION_ID);
        }
        drawables = fragmentDrawables;

        fillSlabWithEmptyBottles(context, activity, resIDs);
        setClickListener();
    }

    private void fillSlabWithEmptyBottles(Context context, Activity activity, int[] resIDs) {
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
    public boolean removeFromSlab(Activity activity, String typeOfBottle) {
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

    public void setClickListener() {

        // remove and add buttons of the list
        final ImageButton[] removeButtons = new ImageButton[typesOfDrinks.length];
        final ImageButton[] addButtons = new ImageButton[typesOfDrinks.length];
        final TextView[] amountOfTypeInSlab = new TextView[typesOfDrinks.length];

        for (int i = 0; i < 4; i++) {
            int removeID = activity.getResources().getIdentifier(typesOfDrinks[i] + "Remove", "id", BuildConfig.APPLICATION_ID);
            int addID = activity.getResources().getIdentifier(typesOfDrinks[i] + "Add", "id", BuildConfig.APPLICATION_ID);
            int amountID = activity.getResources().getIdentifier(typesOfDrinks[i] + "Amount", "id", BuildConfig.APPLICATION_ID);

            // remove buttons
            removeButtons[i] = (ImageButton) activity.findViewById(removeID);
            addButtons[i] = (ImageButton) activity.findViewById(addID);
            amountOfTypeInSlab[i] = (TextView) activity.findViewById(amountID);
        }

        for (int i = 0; i < removeButtons.length; i++) {
            final int fI = i;

            removeButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(removeFromSlab(activity, typesOfDrinks[fI])) {
                        // Decreases the amount counter of the types of bottles in the list below the slab
                        amountOfTypeInSlab[fI].setText(String.valueOf(Integer.parseInt(amountOfTypeInSlab[fI].getText().toString()) - 1));

                        // Increases the displayed amount of left free slots in the slab
                        amountInSlab.setText(String.valueOf(Integer.parseInt(amountInSlab.getText().toString()) + 1));
                    }
                }
            });

            addButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (addToSlab(typesOfDrinks[fI], fI)) {
                        // Increases the amount counter of the types of bottles in the list below the slab
                        amountOfTypeInSlab[fI].setText(String.valueOf(Integer.parseInt(amountOfTypeInSlab[fI].getText().toString()) + 1));

                        // Decreases the displayed amount of left free slots in the slab
                        amountInSlab.setText(String.valueOf(Integer.parseInt(amountInSlab.getText().toString()) - 1));
                    }
                }
            });
        }

    }
}