package de.thi.uxd.android.carsten.CreateSlabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
    private int[] resIDs = new int[12];

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

        Slab slab = new Slab(getContext(), getActivity(), resIDs, drawables, typesOfDrinks);
        slab.setClickListener();
    }

}
