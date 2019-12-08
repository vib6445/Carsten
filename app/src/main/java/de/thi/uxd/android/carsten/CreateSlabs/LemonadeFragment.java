package de.thi.uxd.android.carsten.CreateSlabs;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import de.thi.uxd.android.carsten.BuildConfig;
import de.thi.uxd.android.carsten.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LemonadeFragment extends Fragment {

    private final String[] typesOfDrinks = {"cc", "sp", "mm", "ft"};
    private final ImageButton[] removeButtons = new ImageButton[4];
    private final ImageButton[] addButtons = new ImageButton[4];
    private final int[] resIDs = {R.id.imageButton1, R.id.imageButton2, R.id.imageButton3, R.id.imageButton4, R.id.imageButton5, R.id.imageButton6, R.id.imageButton7, R.id.imageButton8, R.id.imageButton9, R.id.imageButton10, R.id.imageButton11, R.id.imageButton12};

    private Slab slab;

    public LemonadeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lemonade, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        slab = new Slab(getResources());
        addClickListener();
    }

    private void addClickListener() {

        // add Listener to the add and remove buttons
        for (int i = 0; i < removeButtons.length; i++) {
            int removeID = getResources().getIdentifier(typesOfDrinks[i] + "Remove", "id", BuildConfig.APPLICATION_ID);
            int addID = getResources().getIdentifier(typesOfDrinks[i] + "Add", "id", BuildConfig.APPLICATION_ID);

            // remove buttons
            removeButtons[i] = (ImageButton) getActivity().findViewById(removeID);
            addButtons[i] = (ImageButton) getActivity().findViewById(addID);
        }

        for (int i = 0; i < removeButtons.length; i++) {
            final int fI = i;

            removeButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    slab.removeFromSlab(typesOfDrinks[fI]);
                }
            });
        }
        for (int i = 0; i < addButtons.length; i++) {
            final int fI = i;

            addButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    slab.addToSlab(typesOfDrinks[fI]);
                }
            });
        }
    }








    @SuppressLint("AppCompatCustomView")
    public class SlabBottle extends ImageButton {
        private String _bottleType;
        private ImageButton _imageButton;
        public SlabBottle(Context context) {
            super(context);
        }
        public SlabBottle(Context context, String bottleType, int resID) {
            super(context);
            this._bottleType = bottleType;
            this._imageButton = (ImageButton) getActivity().findViewById(resIDs[resID]);
        }
        public String getType() {
            return _bottleType;
        }
    }

    public class Slab {
        SlabBottle[] slabBottles;
        public Slab(Resources resources) {
            slabBottles = new SlabBottle[12];
            // create Bottle Array of the Slab
            for (int i = 0; i < slabBottles.length; i++) {
                slabBottles[i] = new SlabBottle(getContext(), "empty", i);
            }
        }

        void addToSlab(String typeOfBottle) {
            for (SlabBottle slabBottle : slabBottles) {
                if (slabBottle._bottleType.equals("empty")) {
                    slabBottle._bottleType = typeOfBottle;
                    slabBottle._imageButton.setImageResource(R.drawable.ic_button_plus);
                    return;
                }
            }

        }

        void removeFromSlab(String typeOfBottle) {
            for (SlabBottle slabBottle : slabBottles) {
                if (slabBottle != null && slabBottle.getType().equals(typeOfBottle)) {
                    slabBottle._bottleType = "empty";
                    slabBottle._imageButton.setImageResource(R.drawable.drink_slot_empty);
                    sortSlab();
                    return;
                }
            }
        }
        private void sortSlab() {
            for (int i = 0; i < slabBottles.length; i++) {
                if (slabBottles[i]._bottleType.equals("empty") && i+1 < slabBottles.length) {
                    SlabBottle tempBottle = slabBottles[i];
                    slabBottles[i] = slabBottles[i+1];
                    slabBottles[i+1] = tempBottle;
                    Toast.makeText(getContext(), i+ ": SORT", Toast.LENGTH_SHORT).show();
                }
            }
        }

        public SlabBottle getSlabBottle(int position) {
            return slabBottles[position];
        }
    }

}
