package com.example.btdanhsach.Contact;

public class Donvi {
    private String id, address, name, sdt;
    private String avatar;

    public Donvi() {
    }

    public Donvi(String address, String name, String sdt, String avatar) {
            this.address = address;
            this.name = name;
            this.sdt = sdt;
            this.avatar = avatar;
    }

    public Donvi(String id, String address, String name, String avatar, String sdt) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.avatar = avatar;
        this.sdt = sdt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
