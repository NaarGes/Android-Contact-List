package com.example.user.contactlist.view;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.user.contactlist.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearContactFragment linearContactFragment = LinearContactFragment.newInstance();
        linearContactFragment.setListener(new TransferInterface() {
            @Override
            public void goToGrid() {

                GridContactFragment gridContactFragment = GridContactFragment.newInstance();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, gridContactFragment)
                        .addToBackStack(gridContactFragment.getTag())
                        .commit();
            }
        });

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, linearContactFragment)
                .commit();
    }
}
