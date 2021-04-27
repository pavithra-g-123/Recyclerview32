package com.example.recyclerview_3;

public class ModelClass {
    private int sno;
    private String BloodGroup;
    private String name;
    private int phone_no;
    private String address;

    public ModelClass(int sno,String BloodGroup,String name,int phone_no,String address) {
        this.sno = sno;
        this.BloodGroup=BloodGroup;
        this.name = name;
        this.phone_no = phone_no;
        this.address = address;
    }

    public int getSno() {
        return sno;
    }

    public String getBloodGroup() {
        return BloodGroup;
    }

    public String getName() {
        return name;
    }

    public int getPhone_no() {
        return phone_no;
    }

    public String getAddress() {
        return address;
    }
}


