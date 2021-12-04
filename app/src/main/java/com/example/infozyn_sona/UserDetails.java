package com.example.infozyn_sona;

public class UserDetails {
    String name;
    String email;
    String mobile;
    String profileImg;

    public UserDetails(){

    }

    public UserDetails(String name, String email, String mobile,String profileImg) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.profileImg=profileImg;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
