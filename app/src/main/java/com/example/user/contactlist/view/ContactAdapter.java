package com.example.user.contactlist.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.user.contactlist.R;
import com.example.user.contactlist.data.model.Contact;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<Contact> contacts;

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View result = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_contact,
                viewGroup, false);
        return new ContactViewHolder(result);
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

        private TextView name, phoneNo;
        private ImageView photo;

        ContactViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.contact_name);
            phoneNo = itemView.findViewById(R.id.contact_number);
            photo = itemView.findViewById(R.id.contact_photo);
        }

        void onBind(Contact contact) {

            name.setText(contact.getName());
            phoneNo.setText(contact.getPhoneNumber());
            if (contact.getPhotoUri() != null)
                Glide.with(itemView)
                        .load(contact.getPhotoUri())
                        .apply(RequestOptions.circleCropTransform())
                        .into(photo);
            else
                Glide.with(itemView)
                .load(R.drawable.ic_business_card_of_a_woman)
                .apply(RequestOptions.circleCropTransform())
                .into(photo);
        }
    }
}
