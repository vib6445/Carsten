package de.thi.uxd.android.carsten;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate((R.layout.fragment_create), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager viewPagerObject = (ViewPager) getActivity().findViewById(R.id.viewPager);
        PagerAdapter pagerAdapter = new FixedTabsPagerAdapter(getFragmentManager());
        viewPagerObject.setAdapter(pagerAdapter);
        //fixIconSize();
    }

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
                    return new OurSelectionFragment();
                case 1:
                    return new LemonadeFragment();
                case 2:
                    return new ShortBeerFragment();
                case 3:
                    return new LongBeerFragment();
                default:
                    return null;
            }
        }
    }

    /* to override the standard icon size of the bottom navigation view of the Slab-Selection
    private void fixIconSize(){
        BottomNavigationView bottomNavigationView = (BottomNavigationView) getActivity().findViewById(R.id.create_navigation);
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);



        for (int i = 0; i < menuView.getChildCount(); i++) {
            final View iconView = menuView.getChildAt(i).findViewById(com.google.android.material.R.id.icon);
            final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
            final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            // set your height here
            layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 320, displayMetrics);
            // set your width here
            layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 320, displayMetrics);
            iconView.setLayoutParams(layoutParams);
        }
    }*/

}
