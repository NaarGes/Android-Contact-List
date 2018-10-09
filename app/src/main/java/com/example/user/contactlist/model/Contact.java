package com.example.user.contactlist.model;


import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class Contact extends BaseObservable {

    private String name;
    private String phoneNumber;
    private String photoUri;

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Bindable
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Bindable
    public String getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }
}
