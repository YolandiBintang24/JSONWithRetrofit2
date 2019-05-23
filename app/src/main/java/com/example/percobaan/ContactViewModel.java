package com.example.percobaan;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ContactViewModel extends AndroidViewModel {
    private LiveData<List<Kontak>> allContacts = new MutableLiveData();
    private KontakRepository repository;
    Context context;
    public ContactViewModel(@NonNull Application application){
        super(application);
        context = application.getApplicationContext();
        repository = new KontakRepository();
        allContacts = repository.getAllContacts();
        getListContact();
    }
    public LiveData<List<Kontak>> getListContact(){
        return allContacts;
    }

    public void insertContact(Kontak kontak,Context context){
        repository.insert(kontak,context);
    }

    public void deleteContact(int id,Context context){
        repository.delete(id,context);
    }
}
