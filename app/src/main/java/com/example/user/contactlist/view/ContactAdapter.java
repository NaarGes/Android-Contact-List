package com.example.user.contactlist.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.user.contactlist.R;
import com.example.user.contactlist.databinding.ItemContactBinding;
import com.example.user.contactlist.data.model.Contact;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<Contact> contacts;
    private ItemContactBinding binding;
    private Context context;

    public ContactAdapter(Context context) {
        this.context = context;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.item_contact, viewGroup, false);
        return new ContactViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder contactViewHolder, int i) {

        contactViewHolder.onBind(contacts.get(i));
    }

    @Override
    public int getItemCount() {
        return contacts != null ? contacts.size() : 0;
    }


    class ContactViewHolder extends RecyclerView.ViewHolder {

        private final ItemContactBinding binding;
        ContactViewHolder(ItemContactBinding itemContactBinding) {
            super(itemContactBinding.getRoot());
            this.binding = itemContactBinding;
        }

        void onBind(Contact contact) {
            //binding.setVariable(BR.contact, contact);
            binding.setContact(contact);
            EventHandler handler = new EventHandler(context);
            binding.setHandler(handler);
            binding.executePendingBindings();
        }
    }
}
