package com.iteam.Gestion.Expert.configmail;


import com.iteam.Gestion.Expert.entities.User;

public interface MailService {

    void sendVerificationToken(String token, User user);

    void sendTextEmail(String string, String string2, String string3);
}
