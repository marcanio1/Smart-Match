package com.smartMatch.user;

/**
 * This is used to create the put for just email address and the password.
 */
public class Verify {
    String emailaddress;
    String userPassword;
    public Verify(String emailaddress, String userPassword) {
       this.emailaddress=emailaddress;
       this.userPassword=userPassword;
    }

    public String getEmail() {
        return emailaddress;
    }

    public String getPassword() {
        return userPassword;
    }

    public void setEmail(String email) {
        this.emailaddress = email;
    }

    public void setPassword(String password) {
        this.userPassword = password;
    }
}
