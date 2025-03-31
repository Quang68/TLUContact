package com.example.btdanhsach.Contact;

public class staff {
    private String department, email, fullName, id, phoneNumber, photoURL, position, role;

    public staff() {
    }

    public staff(String department, String email, String fullName, String id, String phoneNumber, String photoURL, String position, String role) {
        this.department = department;
        this.email = email;
        this.fullName = fullName;
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.photoURL = photoURL;
        this.position = position;
        this.role = role;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
