package com.iteam.Gestion.Expert.auth;


import lombok.Data;

@Data
public class PasswordRequestUtilchangepassword {
    private String email;
    private String oldPassword;
    private String newPassword;
}
