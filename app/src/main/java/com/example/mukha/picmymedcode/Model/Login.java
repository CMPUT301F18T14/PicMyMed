package com.example.mukha.picmymedcode.Model;

public class Login {
    // Get it from the database and check the password and UserID from there
    // For now use a local file

    // should check for username
    public boolean checkUsername(String username) {
        if (username.equals("username")) {
            return true;
        }
        else {
            return false;
        }
    }

}
