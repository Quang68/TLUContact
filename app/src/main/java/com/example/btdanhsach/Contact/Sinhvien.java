package com.example.btdanhsach.Contact;

public class Sinhvien {
    private String msv, name, sdt, email, address, lop, role;
    private int avatar;

    public Sinhvien(String msv, String name, String email, String role) {
        this.msv = msv;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public Sinhvien(String msv, String name, String sdt, String email, String address, String lop, int avatar) {
        this.msv = msv;
        this.name = name;
        this.sdt = sdt;
        this.email = email;
        this.address = address;
        this.lop = lop;
        this.avatar = avatar;
    }

    public String getMsv() {
        return msv;
    }

    public void setMsv(String msv) {
        this.msv = msv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
}
