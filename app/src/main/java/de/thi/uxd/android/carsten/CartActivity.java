package de.thi.uxd.android.carsten;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

import de.thi.uxd.android.carsten.CreateSlabs.Slab;

public class CartActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        Intent intent = this.getIntent();
        CartContainer cartContainer = (CartContainer) intent.getSerializableExtra("CartContainer");

        ArrayList<Slab> slabList = cartContainer.getSlabList();

        LinearLayout cartRootLayout = findViewById(R.id.cartRootLayout);



        Button checkoutButton = findViewById(R.id.checkoutButton);
        ConstraintLayout emptyState = findViewById(R.id.emptyState);
        if (slabList.isEmpty()) {
            checkoutButton.setVisibility(View.GONE);
            emptyState.setVisibility(View.VISIBLE);
            cartRootLayout.setVisibility(View.GONE);
        } else {
            checkoutButton.setVisibility(View.VISIBLE);
            emptyState.setVisibility(View.GONE);
            cartRootLayout.setVisibility(View.VISIBLE);
        }

        if (!slabList.isEmpty()) {
            for (int i = 0; i < slabList.size(); i++) {
                View cardView = getLayoutInflater().inflate(R.layout.cart_cardview, null);

                LinearLayout slabSpace = cardView.findViewById(R.id.slabSpace);
                int layout;
                if ("lemonade".equals(slabList.get(i).getType())) {
                    layout = R.layout.slab_lemonade;
                } else {
                    layout = R.layout.slab_shortbeer;
                }
                View slabView = getLayoutInflater().inflate(layout, null);


                ArrayList<View> allViewsWithinMySlabView = getAllChildren(slabView);
                int id = 0;
                for (View child : allViewsWithinMySlabView) {
                    if (child instanceof ImageButton) {
                        ImageButton childButton = (ImageButton) child;
                        childButton.setImageResource(slabList.get(i).getSlabBottles()[id].getDrawableID());
                        id++;
                    }
                }


                slabSpace.addView(slabView);
                cartRootLayout.addView(cardView);
            }

            checkoutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openCheckoutActivity();
                }

            });
        } else {
            Button createSlab = findViewById(R.id.createSlab);
            createSlab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CartActivity.this, MainActivity.class);
                    intent.putExtra("dest", "createSlab");
                    startActivity(intent);
                }
            });
        }

        Button backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    private ArrayList<View> getAllChildren(View v) {

        if (!(v instanceof ViewGroup)) {
            ArrayList<View> viewArrayList = new ArrayList<View>();
            viewArrayList.add(v);
            return viewArrayList;
        }

        ArrayList<View> result = new ArrayList<View>();

        ViewGroup vg = (ViewGroup) v;
        for (int i = 0; i < vg.getChildCount(); i++) {

            View child = vg.getChildAt(i);

            ArrayList<View> viewArrayList = new ArrayList<View>();
            viewArrayList.add(v);
            viewArrayList.addAll(getAllChildren(child));

            result.addAll(viewArrayList);
        }
        return result;
    }

    public void openCheckoutActivity(){
        Intent intent = new Intent(this, CheckoutActivity.class);
        startActivity(intent);
    }

}
