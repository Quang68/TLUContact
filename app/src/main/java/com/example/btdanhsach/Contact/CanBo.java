package com.example.btdanhsach.Contact;

public class CanBo {
    private String name, chucvu, sdt, email, donvicongtac, id, role;
    private String avatar;

    public CanBo() {
    }

    public CanBo(String id, String name, String chucvu, String sdt, String email, String donvicongtac, String avatar) {
        this.id = id;
        this.name = name;
        this.chucvu = chucvu;
        this.sdt = sdt;
        this.email = email;
        this.donvicongtac = donvicongtac;
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChucvu() {
        return chucvu;
    }

    public void setChucvu(String chucvu) {
        this.chucvu = chucvu;
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

    public String getDonvicongtac() {
        return donvicongtac;
    }

    public void setDonvicongtac(String donvicongtac) {
        this.donvicongtac = donvicongtac;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
