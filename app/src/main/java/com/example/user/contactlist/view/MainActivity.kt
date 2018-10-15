package com.example.user.contactlist.view

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.user.contactlist.R

class MainActivity : AppCompatActivity(), TransferInterface {

    private var fragmentManager: FragmentManager? = null
    private var fragmentTransaction: FragmentTransaction? = null
    private var linearContactFragment: LinearContactFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager!!.beginTransaction()

        linearContactFragment = LinearContactFragment.newInstance(this)

        if (findViewById<View>(R.id.fragment_container) != null)
            if (savedInstanceState == null) {

                if (!linearContactFragment!!.isVisible)
                    fragmentTransaction!!.replace(R.id.fragment_container, linearContactFragment!!)
                if (fragmentManager!!.findFragmentByTag(linearContactFragment!!.tag) != null)
                    fragmentTransaction!!.disallowAddToBackStack()
                else
                    fragmentTransaction!!.addToBackStack(linearContactFragment!!.tag)
                fragmentTransaction!!.commit()
            }
    }

    override fun onBackPressed() {

        val fragmentManager = supportFragmentManager
        val fragments = fragmentManager.backStackEntryCount
        if (fragments == 1) {
            finish()
        } else {
            if (fragmentManager.backStackEntryCount > 1) {
                fragmentManager.popBackStack()
            } else {
                super.onBackPressed()
            }
        }
    }

    override fun goToGrid() {
        val gridContactFragment = GridContactFragment.newInstance()
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, gridContactFragment)
                .addToBackStack(gridContactFragment.tag)
                .commit()
    }
}
