package de.thi.uxd.android.carsten;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CheckoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        Button thankYouButton = findViewById(R.id.checkoutButton);

        thankYouButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openThankYouActivity();
            }

        });
    }

    public void openThankYouActivity(){
        Intent intent = new Intent(this, ThankYouActivity.class);
        startActivity(intent);
    }
}
