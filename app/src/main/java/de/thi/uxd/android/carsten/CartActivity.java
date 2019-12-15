package de.thi.uxd.android.carsten;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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





            int[] resIDs = new int[slabList.get(i).getSlabBottles().length];
            ImageButton[] imageButtons = new ImageButton[slabList.get(i).getSlabBottles().length];

            for (int id = 0; id < resIDs.length; id++) {
                resIDs[id] = getResources().getIdentifier(slabList.get(i).getType() + "Button" + (id + 1), "id", BuildConfig.APPLICATION_ID);
                imageButtons[id] = slabView.findViewById(resIDs[id]);
                imageButtons[id].setImageResource(slabList.get(i).getSlabBottles()[id].getDrawableID());
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



    public void openCheckoutActivity(){
        Intent intent = new Intent(this, CheckoutActivity.class);
        startActivity(intent);
    }

}
