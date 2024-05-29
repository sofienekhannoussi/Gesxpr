package com.iteam.Gestion.Expert.reposetories;

import java.io.UnsupportedEncodingException;

import com.iteam.Gestion.Expert.auth.AuthenticationService;
import com.iteam.Gestion.Expert.auth.RegistrationCompleteEvent;
import com.iteam.Gestion.Expert.entities.User;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import tn.teams.servicescomp.auth.AuthenticationService;
//import tn.teams.servicescomp.entites.User;
//import tn.teams.servicescomp.event.RegistrationCompleteEvent;




@Slf4j
@Component
@RequiredArgsConstructor
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    private final AuthenticationService userService;
    private final JavaMailSender mailSender;
    private User theUser;
    /* @Override
     public void onApplicationEvent(RegistrationCompleteEvent event) {
         // 1. Get the newly registered user
          theUser = event.getUser();
         //2. Create a verification token for the user
         String verificationToken = UUID.randomUUID().toString();
         //3. Save the verification token for the user
       //  userService.saveUserVerificationToken(theUser, verificationToken);
         //4 Build the verification url to be sent to the user
         String url = event.getApplicationUrl()+"/register/verifyEmail?token="+verificationToken;
         //5. Send the email.
         try {
            // sendVerificationEmail(url);
         } catch (MessagingException | UnsupportedEncodingException e) {
            // throw new RuntimeException(e);
         }
         log.info("Click the link to verify your registration :  {}", url);
     }*/
    public void sendVerificationEmail(String url) throws MessagingException, UnsupportedEncodingException {
        //theUser = event.getUser();
        String subject = "Email Verification";
        String senderName = "User Registration Portal Service";
        String mailContent = "<p> Hi, "+ theUser.getFullname()+ ", </p>"+
                "<p>Thank you for registering with us,"+"" +
                "Please, follow the link below to complete your registration.</p>"+
                "<a href=\"" +url+ "\">Verify your email to activate your account</a>"+
                "<p> Thank you <br> Users Registration Portal Service";
        MimeMessage message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom("nailiridha20@gmail.com", senderName);
        messageHelper.setTo(theUser.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        mailSender.send(message);
    }

    public void sendPasswordResetVerificationEmail(String code,User user) throws MessagingException, UnsupportedEncodingException {
        String subject = "Password Reset Request Verification";
        String senderName = "User Registration Portal Service";
        String mailContent = "<p> Hi, "+ user.getFullname()+ ", </p>"+
                "<p><b>You recently requested to reset your password,</b>"+"" +
                "Please, follow the link below to complete the action.</p>"+
                "<p> "+ code + "</p>"+
                "<p> Users Registration Portal Service";
        MimeMessage message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom("nailiridha20@gmail.com", senderName);
        messageHelper.setTo(user.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        mailSender.send(message);
    }
    //email welcome to site
    public void sendwelcomeEmail(User user) throws MessagingException, UnsupportedEncodingException {
        String subject = "Password Reset Request Verification";
        String senderName = "User Registration Portal Service";
        String mailContent = "<p> Hi, "+ user.getFullname()+ ", </p>"+
                "<p><b>Nous sommes heureux de vous accueillir, !,</b>"+""
                ;
        MimeMessage message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom("nailiridha20@gmail.com", senderName);
        messageHelper.setTo(user.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        mailSender.send(message);
    }





    /* public void sendPasswordResetVerificationEmail(String url,User user) throws MessagingException, UnsupportedEncodingException {
    String subject = "Password Reset Request Verification";
    String senderName = "User Registration Portal Service";
    String mailContent = "<p> Hi, "+ user.getFullname()+ ", </p>"+
            "<p><b>You recently requested to reset your password,</b>"+"" +
            "Please, follow the link below to complete the action.</p>"+
            "<a href=\"" +url+ "\">Reset password</a>"+
            "<p> Users Registration Portal Service";
    MimeMessage message = mailSender.createMimeMessage();
    var messageHelper = new MimeMessageHelper(message);
    messageHelper.setFrom("nailiridha20@gmail.com", senderName);
    messageHelper.setTo(user.getEmail());
    messageHelper.setSubject(subject);
    messageHelper.setText(mailContent, true);
    mailSender.send(message);
}*/

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // TODO Auto-generated method stub

    }
}
