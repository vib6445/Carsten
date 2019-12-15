package de.thi.uxd.android.carsten;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeFragment extends Fragment {

    CardView buttonDelivery;
    CardView buttonProfile;
    CardView buttonCreate;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate((R.layout.fragment_home), container, false);

        final BottomNavigationView topNav = getActivity().findViewById(R.id.top_navigation);



        buttonDelivery = v.findViewById(R.id.btn_deliveryStatus);
        buttonDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        buttonCreate = v.findViewById(R.id.btn_newOrder);
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topNav.setSelectedItemId(R.id.nav_create);
            }
        });

        buttonProfile = v.findViewById(R.id.btn_profile);
        buttonProfile.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                topNav.setSelectedItemId(R.id.nav_account);
            }
        });
        return v;


    }
}
