package com.example.percobaan;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class KontakRepository {
    Retrofit connection = ContactConnection.getInstance();
    private KontakApi kontakApi = connection.create(KontakApi.class);
    private MutableLiveData<List<Kontak>> allContacts = new MutableLiveData<>();


    public LiveData<List<Kontak>> getAllContacts(){
        Call<List<Kontak>> call = kontakApi.getAllContact();
        call.enqueue(new Callback<List<Kontak>>() {
            @Override
            public void onResponse(Call<List<Kontak>> call, Response<List<Kontak>> response) {
                allContacts.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Kontak>> call, Throwable t) {
                Log.e("Err",t.getMessage());
            }
        });
        return allContacts;
    }

    public void insert(Kontak kontak, final Context context){

    Call<Kontak> call = kontakApi.saveContact(kontak);
    call.enqueue(new Callback<Kontak>() {
        @Override
        public void onResponse(Call<Kontak> call, Response<Kontak> response) {
            if(!response.isSuccessful()){
                Toast.makeText(context,response.code(),Toast.LENGTH_SHORT).show();
            }
            //Toast.makeText(context,"Saved Contact is Success",Toast.LENGTH_SHORT).show();
            getAllContacts();

        }

        @Override
        public void onFailure(Call<Kontak> call, Throwable t) {
        Toast.makeText(context,t.getMessage(),Toast.LENGTH_SHORT).show();

        }
    });


    }

    public void delete(int id, final Context context){
       final ProgressDialog pd = new ProgressDialog(context);
        pd.setMessage("Deleting...");
        pd.show();
        Call<Void>call = kontakApi.deleteContact(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(context,response.code(),Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(context,"Contact Deleted",Toast.LENGTH_SHORT).show();
                getAllContacts();
                pd.dismiss();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(context,t.getMessage(),Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });
    }

}
