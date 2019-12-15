package de.thi.uxd.android.carsten;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

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
        /* for (int i = 0; i < slabList.size(); i++) {
            String out = slabList.get(i).getSlabBottles()[i].getBottleType();
            Toast.makeText(getApplicationContext(), out, Toast.LENGTH_SHORT).show();
        } */

        LinearLayout cartRootLayout = findViewById(R.id.cartRootLayout);

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
                    if (id < 12) {
                        childButton.setImageResource(slabList.get(i).getSlabBottles()[id].getDrawableID());
                    } else {
                        childButton.setImageResource(R.drawable.drink_slot_empty);
                    }
                    id++;
                }
            }


            slabSpace.addView(slabView);
            cartRootLayout.addView(cardView);
        }

        Button checkoutButton = findViewById(R.id.checkoutButton);
        checkoutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openCheckoutActivity();
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
