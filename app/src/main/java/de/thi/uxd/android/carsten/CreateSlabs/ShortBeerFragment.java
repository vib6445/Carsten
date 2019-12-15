package de.thi.uxd.android.carsten.CreateSlabs;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import de.thi.uxd.android.carsten.BuildConfig;
import de.thi.uxd.android.carsten.CartContainer;
import de.thi.uxd.android.carsten.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShortBeerFragment extends Fragment {

    // possible types of drinks/ bottles with their correct drawables
    private final String[] typesOfDrinks = {"ag", "ae", "tg", "sh","gw"};
    private final int[] drawables = {
            R.drawable.ic_augustiner_hell,
            R.drawable.ic_augustiner_edelstoff,
            R.drawable.ic_tegernseer_hell,
            R.drawable.ic_spaten_hell,
            R.drawable.ic_gutmann_weizen
    };

    // Beer Slab with a fixed amount of bottles
    private Slab shortbeer;

    // ImageButton Array for visual feedback of filled slab slots
    private ImageButton[] imageButtons = new ImageButton[20];

    // TextView showing the free space of the slab (Beer = 20)
    private TextView amountInSlab;

    // Remove and add buttons of the list
    private ImageButton[] removeButtons = new ImageButton[typesOfDrinks.length];
    private ImageButton[] addButtons = new ImageButton[typesOfDrinks.length];

    // TextView showing the current amount of a bottle type in the slab
    private TextView[] amountOfTypeInSlab = new TextView[typesOfDrinks.length];

    public ShortBeerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_short_beer, container, false);

        // Load slab dynamically, because the slab view is later needed for the cart
        LinearLayout slabRootLayout = rootView.findViewById(R.id.slabShortBeerSpace);
        View slabView = inflater.inflate(R.layout.slab_shortbeer, null);
        slabRootLayout.addView(slabView);

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Fills the ImageButton Array with their corresponding Views from the XML-file
        // The IDs of the ImageButtons in the XML-file follows a specific naming guideline: type + "Button" + count.
        for (int i = 0; i < imageButtons.length; i++) {
            // The counter start with 1 in the XML-file
            int id = i + 1;
            imageButtons[i] = getActivity().findViewById(getActivity().getResources().getIdentifier("shortbeerButton" + id, "id", BuildConfig.APPLICATION_ID));
        }

        // Creates new Slab that resembles the graphical ImageButtons with the actual information as object
        shortbeer = new Slab(drawables, "shortbeer", imageButtons.length);

        // Sets the initial amount of free slots in the slab
        amountInSlab = (TextView) getActivity().findViewById(R.id.amountShortBeer);
        amountInSlab.setText(String.valueOf(imageButtons.length));

        // Sets the click listener for the add and remove buttons
        setClickListener();

        // Sets the click listener to the bottom button, with which you can add a filled Slab to the cart
        addToCartListener();
    }

    private void addToCartListener() {
        Button sbATCButton = (Button) getActivity().findViewById(R.id.addToCartSB);
        sbATCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shortbeer.isFull()) {
                    // Uses the Copy-Constructor to create a new instance of the slab, which is then added to an ArrayList holding various Slabs
                    CartContainer.addToCart(new Slab(shortbeer));

                    // As soon as a slab is added to the cart the icon indicates with the color and a badge that now a buy operation can be conducted
                    ImageButton cartButton = getActivity().findViewById(R.id.cartButton);
                    cartButton.setColorFilter(R.color.colorBlack);
                    TextView amountInCart = getActivity().findViewById(R.id.amountInCart);
                    amountInCart.setText(String.valueOf(Integer.parseInt(amountInCart.getText().toString()) + 1));
                }
                else {
                    Toast.makeText(getContext(), "Only full slabs can be added to your cart", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setClickListener() {

        for (int i = 0; i < removeButtons.length; i++) {
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
                    if (shortbeer.removeFromSlab(typesOfDrinks[fI])) {
                        // Decreases the amount counter of the types of bottles in the list below the slab
                        amountOfTypeInSlab[fI].setText(String.valueOf(Integer.parseInt(amountOfTypeInSlab[fI].getText().toString()) - 1));

                        // Increases the displayed amount of left free slots in the slab
                        amountInSlab.setText(String.valueOf(Integer.parseInt(amountInSlab.getText().toString()) + 1));

                        for (int i = 0; i < imageButtons.length; i++) {
                            imageButtons[i].setImageResource(shortbeer.getSlabBottles()[i].getDrawableID());
                        }
                    }
                }
            });

            addButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (shortbeer.addToSlab(typesOfDrinks[fI], fI)) {
                        // Increases the amount counter of the types of bottles in the list below the slab
                        amountOfTypeInSlab[fI].setText(String.valueOf(Integer.parseInt(amountOfTypeInSlab[fI].getText().toString()) + 1));

                        // Increases the displayed amount of left free slots in the slab
                        amountInSlab.setText(String.valueOf(Integer.parseInt(amountInSlab.getText().toString()) - 1));

                        for (int i = 0; i < imageButtons.length; i++) {
                            imageButtons[i].setImageResource(shortbeer.getSlabBottles()[i].getDrawableID());
                        }
                    }
                }
            });
        }
    }
}
