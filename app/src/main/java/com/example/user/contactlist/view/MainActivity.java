package com.example.user.contactlist.view;


import android.os.PersistableBundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.user.contactlist.R;

public class MainActivity extends AppCompatActivity implements TransferInterface {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private LinearContactFragment linearContactFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        linearContactFragment = LinearContactFragment.newInstance(this);

        if (findViewById(R.id.fragment_container) != null)
            if (savedInstanceState == null) {

                if (!linearContactFragment.isVisible())
                    fragmentTransaction.replace(R.id.fragment_container, linearContactFragment);
                if (fragmentManager.findFragmentByTag(linearContactFragment.getTag()) != null)
                    fragmentTransaction.disallowAddToBackStack();
                else
                    fragmentTransaction.addToBackStack(linearContactFragment.getTag());
                fragmentTransaction.commit();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

    }

    @Override
    public void onBackPressed() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        int fragments = fragmentManager.getBackStackEntryCount();
        if (fragments == 1) {
            finish();
        } else {
            if (fragmentManager.getBackStackEntryCount() > 1) {
                fragmentManager.popBackStack();
            } else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public void goToGrid() {
        GridContactFragment gridContactFragment = GridContactFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, gridContactFragment)
                .addToBackStack(gridContactFragment.getTag())
                .commit();
    }
}
