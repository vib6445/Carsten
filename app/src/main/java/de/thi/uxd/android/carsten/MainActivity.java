package de.thi.uxd.android.carsten;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    CartContainer cartContainer = new CartContainer();
    ImageButton cartButton;

    public MainActivity() {


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cartButton = findViewById(R.id.cartButton);
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCartActivity();
            }
        });

        BottomNavigationView topNav = findViewById(R.id.top_navigation);
        topNav.setOnNavigationItemSelectedListener(navListener);

        Intent intent = this.getIntent();
        String dest = intent.getStringExtra("dest");
        if (dest != null && dest.equals("createSlab")) {
            topNav.setSelectedItemId(R.id.nav_create);
        } else {
            topNav.setSelectedItemId(R.id.nav_home);
        }


    }


    public void openCartActivity(){

        Intent intent = new Intent(this, CartActivity.class);
        intent.putExtra("CartContainer", cartContainer);
        startActivity(intent);

    }


    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
        new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selectedFragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        selectedFragment = new HomeFragment();
                        break;
                    case R.id.nav_account:
                        selectedFragment = new ProfileFragment();
                        break;
                    case R.id.nav_create:
                        selectedFragment = new CreateFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commitNow();
                return true;
            }
        };

}
