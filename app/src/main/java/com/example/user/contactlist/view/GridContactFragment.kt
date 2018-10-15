package com.example.user.contactlist.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.user.contactlist.R
import com.example.user.contactlist.viewmodel.ContactViewModel


class GridContactFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_grid_contact, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    private fun init(view: View) {

        val recyclerView = view.findViewById<RecyclerView>(R.id.grid_contact_recycler_view)
        recyclerView.layoutManager = GridLayoutManager(recyclerView.context, 2)
        val adapter = ContactAdapter()

        val contactViewModel = ViewModelProviders.of(activity!!).get<ContactViewModel>(ContactViewModel::class.java!!)

        contactViewModel.contacts!!.observe(this, Observer{ contacts ->
            recyclerView.adapter = adapter
            adapter.setContacts(contacts!!)
        })

        contactViewModel.liveDataString.observe(this, Observer{ s -> Toast.makeText(context, s, Toast.LENGTH_SHORT).show() })

    }

    companion object {

        fun newInstance(): GridContactFragment {
            val fragment = GridContactFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
