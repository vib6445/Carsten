package de.thi.uxd.android.carsten.CreateSlabs;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import de.thi.uxd.android.carsten.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LemonadeFragment extends Fragment {

    final ConstraintLayout lemonadeLayout = new ConstraintLayout(getContext());
    final ImageView imageView = new ImageView(getContext());


    public LemonadeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lemonade, container, false);
    }

}
