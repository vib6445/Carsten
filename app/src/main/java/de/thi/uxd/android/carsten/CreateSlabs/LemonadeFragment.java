package de.thi.uxd.android.carsten.CreateSlabs;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import de.thi.uxd.android.carsten.BuildConfig;
import de.thi.uxd.android.carsten.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LemonadeFragment extends Fragment {

    final String[] typesOfDrinks = {"cc", "sp", "mm", "ft"};
    final ImageButton[] removeButtons = new ImageButton[4];
    final ImageButton[] addButtons = new ImageButton[4];

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

        slab = new Slab(getActivity());
        addClickListener();

    }

    public void addClickListener() {

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
        public SlabBottle(Context context, String bottleType, ImageButton imageButton) {
            super(context);
            this._bottleType = bottleType;
            this._imageButton = imageButton;
        }
        public String getType() {
            return _bottleType;
        }
    }

    public class Slab {
        SlabBottle[] slabBottles;
        public Slab(FragmentActivity activity) {
            slabBottles = new SlabBottle[9];
            // create Bottle Array of the Slab
            for (int i = 0; i < slabBottles.length; i++) {
                int resID = activity.getResources().getIdentifier("imageButton" + i, "id", BuildConfig.APPLICATION_ID);
                slabBottles[i] = new SlabBottle(getContext(), "empty", (ImageButton) getActivity().findViewById(resID));
                Toast.makeText(getContext(), slabBottles[i]._bottleType + i, Toast.LENGTH_LONG).show();
            }
        }

        public void addToSlab(String typeOfBottle) {
            for (int i = 0; i < slabBottles.length; i++) {
                if (slabBottles[i]._bottleType.equals("empty")) {
                    slabBottles[i]._bottleType = typeOfBottle;
                    slabBottles[i]._imageButton.setImageResource(R.drawable.ic_button_plus);
                    Toast.makeText(getContext(), "Test", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getContext(), slabBottles[i]._bottleType, Toast.LENGTH_SHORT).show();
            }

        }

        public void removeFromSlab(String typeOfBottle) {
            for (int i = 0; i < slabBottles.length; i++) {
                if (slabBottles[i] != null && slabBottles[i].getType().equals(typeOfBottle)); {
                    slabBottles[i]._bottleType = "empty";
                    sortSlab();
                    break;
                }
            }
        }
        private void sortSlab() {
            for (int i = 0; i < slabBottles.length; i++) {
                if (slabBottles[i]._bottleType.equals("empty") && i+1 < slabBottles.length && slabBottles[i+1] != null) {
                    slabBottles[i] = slabBottles[i+1];
                    slabBottles[i+1]._bottleType = "empty";

                }
            }
        }

        public SlabBottle getSlabBottle(int position) {
            return slabBottles[position];
        }
    }

}
