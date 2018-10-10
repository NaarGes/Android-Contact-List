package com.example.user.contactlist.view;

import android.content.Context;
import android.databinding.BaseObservable;
import android.util.Log;
import android.widget.Toast;

import com.example.user.contactlist.data.model.Contact;

public class EventHandler extends BaseObservable {

    private Context context;

    public EventHandler(Context context) {
        this.context = context;
    }

    public void OnContactClick(Contact contact) {
        Log.e("CLICKED", "OnContactClick: "+contact.getName() );
        Toast.makeText(context, contact.getName(), Toast.LENGTH_SHORT).show();
    }
}
