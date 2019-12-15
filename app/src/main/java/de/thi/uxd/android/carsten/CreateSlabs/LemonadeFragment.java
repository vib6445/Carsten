package de.thi.uxd.android.carsten.CreateSlabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import de.thi.uxd.android.carsten.BuildConfig;
import de.thi.uxd.android.carsten.R;


public class LemonadeFragment extends Fragment {

    // possible types of drinks/ bottles with their correct drawables
    private final String[] typesOfDrinks = {"cc", "sp", "mm", "ft"};
    private final int[] drawables = {
            R.drawable.ic_coke,
            R.drawable.ic_sprite,
            R.drawable.ic_mmix,
            R.drawable.ic_fanta,
    };

    // Lemonade Slab with a fixed amount of bottles
    private Slab lemonade;
    private int[] resIDs = new int[12];

    private TextView amountInSlab;

    // remove and add buttons of the list
    private ImageButton[] removeButtons = new ImageButton[typesOfDrinks.length];
    private ImageButton[] addButtons = new ImageButton[typesOfDrinks.length];
    private TextView[] amountOfTypeInSlab = new TextView[typesOfDrinks.length];

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
    public void onResume() {
        super.onResume();
        lemonade = new Slab(getContext(), getActivity(), resIDs, drawables, typesOfDrinks, "lemonade");
        amountInSlab = (TextView) getActivity().findViewById(R.id.amountLemonade);
        amountInSlab.setText(String.valueOf(resIDs.length));
        setClickListener();
    }



    private void setClickListener() {

        for (int i = 0; i < 4; i++) {
            int removeID = getActivity().getResources().getIdentifier(typesOfDrinks[i] + "Remove", "id", BuildConfig.APPLICATION_ID);
            int addID = getActivity().getResources().getIdentifier(typesOfDrinks[i] + "Add", "id", BuildConfig.APPLICATION_ID);
            int amountID = getActivity().getResources().getIdentifier(typesOfDrinks[i] + "Amount", "id", BuildConfig.APPLICATION_ID);

            // remove buttons
            removeButtons[i] = (ImageButton) getActivity().findViewById(removeID);
            addButtons[i] = (ImageButton) getActivity().findViewById(addID);
            amountOfTypeInSlab[i] = (TextView) getActivity().findViewById(amountID);
        }

        for (int i = 0; i < removeButtons.length; i++) {
            final int fI = i;

            removeButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (lemonade.removeFromSlab(typesOfDrinks[fI])) {
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
                    if (lemonade.addToSlab(typesOfDrinks[fI], fI)) {
                        // Increases the amount counter of the types of bottles in the list below the slab
                        amountOfTypeInSlab[fI].setText(String.valueOf(Integer.parseInt(amountOfTypeInSlab[fI].getText().toString()) + 1));

                        // Increases the displayed amount of left free slots in the slab
                        amountInSlab.setText(String.valueOf(Integer.parseInt(amountInSlab.getText().toString()) - 1));
                    }
                }
            });
        }

    }
}
