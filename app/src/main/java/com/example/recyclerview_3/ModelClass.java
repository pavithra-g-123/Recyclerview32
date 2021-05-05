package com.example.recyclerview_3;

public class ModelClass {
    private int sno;
    private String BG;
    private String name;
    private long phone_no;
    private String date;
    private String address;
    private String ID;
    public ModelClass() {
    }
    public ModelClass(int sno,String BG,String name,long phone_no,String date,String address,String ID) {
        this.sno = sno;
        this.BG=BG;
        this.name = name;
        this.phone_no = phone_no;
        this.date=date;
        this.address = address;
        this.ID=ID;
    }

    public int getSno() {
        return sno;
    }

    public String getBG() {
        return BG;
    }

    public String getName() {
        return name;
    }

    public long getPhone_no() {
        return phone_no;
    }


public String getDate(){
        return date;
}
    public String getAddress() {
        return address;
    }
    public String getID(){
        return ID;
    }
}


