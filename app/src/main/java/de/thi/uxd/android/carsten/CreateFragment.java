package de.thi.uxd.android.carsten;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import de.thi.uxd.android.carsten.CreateSlabs.LemonadeFragment;
import de.thi.uxd.android.carsten.CreateSlabs.LongBeerFragment;
import de.thi.uxd.android.carsten.CreateSlabs.OurSelectionFragment;
import de.thi.uxd.android.carsten.CreateSlabs.ShortBeerFragment;

public class CreateFragment extends Fragment {
    private final static int[] radioIDs = {R.id.radioButton1, R.id.radioButton2, R.id.radioButton3, R.id.radioButton4};
    private RadioButton[] radioButtons = new RadioButton[4];
    private ViewPager viewPagerObject;

    private Fragment OurSelectionFragment   = new OurSelectionFragment();
    private Fragment LemonadeFragment       = new LemonadeFragment();
    private Fragment ShortBeerFragment      = new ShortBeerFragment();
    private Fragment LongBeerFragment       = new LongBeerFragment();



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate((R.layout.fragment_create), container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Finds the ViewPager object from the fragment_create.xml file and assigns a PagerAdapter
        // The instructions must be in the onViewCreated-Method, because the ViewPager-Object can first be accessed when created
        viewPagerObject = (ViewPager) getActivity().findViewById(R.id.viewPager);

        // Keeps all fragments in cache for improved usability
        viewPagerObject.setOffscreenPageLimit(3);


        PagerAdapter pagerAdapter = new FixedTabsPagerAdapter(getChildFragmentManager());
        viewPagerObject.setAdapter(pagerAdapter);

        // Our selection is the first active item when going to the Slab-Creation
        viewPagerObject.setCurrentItem(0);

        // Use the ToggleButtons to change the shown fragment of the ViewPagers and set the drop shadow below the card view to highlight the activated state of the related ToggleButton
        setToggleListener();
        viewPagerObject.addOnPageChangeListener(listener);

    }

    private void setToggleListener() {

        for (int i = 0; i < radioIDs.length; i++) {
            radioButtons[i] = (RadioButton) getActivity().findViewById(radioIDs[i]);
        }

        // Initialized RadioButton when opened the first time
        toggleOff(0);
        setCardViewState();

        // Sets RadioButton and CardView State when another RadioButton gets activated
        for (int i = 0; i < radioIDs.length; i++) {
            final int n = i;

            radioButtons[i].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        toggleOff(n);
                        setCardViewState();
                        viewPagerObject.setCurrentItem(n);
                    }
                }
            });
        }
    }

    private void toggleOff (int butNotMe) {
        for (int i = 0; i < radioIDs.length; i++) {
            if (i == butNotMe) {
                radioButtons[i].setChecked(true);
            } else {
                radioButtons[i].setChecked(false);
            }
        }
    }
    private void setCardViewState() {

        final int noDropShadow = 0;
        final int createDropShadow = 16;

        for (int i = 0; i < radioButtons.length; i++) {
            CardView selectedCard = (CardView) getActivity().findViewById(radioIDs[i]).getParent().getParent();
            if(radioButtons[i].isChecked()) {
                selectedCard.setElevation(createDropShadow);
            } else {
                selectedCard.setElevation(noDropShadow);
            }
        }
    }


    // Returns the correct Fragment
    private class FixedTabsPagerAdapter extends FragmentPagerAdapter {
        public FixedTabsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return OurSelectionFragment;
                case 1:

                    return LemonadeFragment;
                case 2:
                    return ShortBeerFragment;
                case 3:
                    return LongBeerFragment;
                default:
                    return null;
            }
        }
    }

    private ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            toggleOff(position);
            setCardViewState();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


}
