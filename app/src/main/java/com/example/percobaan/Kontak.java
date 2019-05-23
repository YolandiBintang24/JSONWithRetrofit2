package com.example.percobaan;

import com.google.gson.annotations.SerializedName;

public class Kontak {
    @SerializedName("id")
    private Integer idContact;

    @SerializedName("nama")
    private String name;

    private String email;

    @SerializedName("nohp")
    private String phone;

    @SerializedName("alamat")
    private String addres;

    public Kontak(String name, String email, String phone, String addres) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.addres = addres;
    }


    public void setIdContact(Integer idContact) {
        this.idContact = idContact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public Integer getIdContact(){
        return idContact;
    }
}
