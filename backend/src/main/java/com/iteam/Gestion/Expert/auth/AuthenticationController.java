package com.iteam.Gestion.Expert.auth;

import com.iteam.Gestion.Expert.dto.Admindto;
import com.iteam.Gestion.Expert.dto.Expertdto;
import com.iteam.Gestion.Expert.dto.ResponsableSocietedto;
import com.iteam.Gestion.Expert.entities.User;
import com.iteam.Gestion.Expert.reposetories.RegistrationCompleteEventListener;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final HttpServletRequest servletRequest;
    private final AuthenticationService service;

    private final RegistrationCompleteEventListener eventListener;

    @GetMapping("/verify")
    public ResponseEntity<?> confirmRegistration(@NotEmpty @RequestParam String token) {
        final String result = service.validateVerificationToken(token);
        return ResponseEntity.ok(result);
    }


    @PostMapping("/register")
    public ResponseEntity<Messgchekmail> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/registerExpert")
    public ResponseEntity<Messgchekmail> registerExpert(
            @RequestBody Expertdto request
    ) {
        return ResponseEntity.ok(service.register(request));
    }


    @PostMapping("/registerAdmin")
    public ResponseEntity<Messgchekmail> registerAdmin(
            @RequestBody Admindto request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/registerResponsableSociete")
    public ResponseEntity<Messgchekmail> registerResponsableSociete(
            @RequestBody ResponsableSocietedto request
    ) {
        return ResponseEntity.ok(service.register(request));
    }


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/password-reset-request")
    public String resetPasswordRequest(@RequestBody PasswordRequestUtil passwordRequestUtil
    )
            throws MessagingException, UnsupportedEncodingException {

        Optional<User> user = service.findByEmail(passwordRequestUtil.getEmail());
        String passwordResetUrl = "";
        if (user.isPresent()) {
            String passwordResetToken = UUID.randomUUID().toString();
            service.createPasswordResetTokenForUser(user.get(), passwordResetToken);
            passwordResetUrl = passwordResetEmailLink(user.get(), applicationUrl(servletRequest), passwordResetToken);
        }
        return passwordResetUrl;
    }

    public String applicationUrl(HttpServletRequest request) {
        return "http://"+request.getServerName()+":"
                +request.getServerPort()+request.getContextPath();
    }



    private String passwordResetEmailLink(User user, String applicationUrl,
                                          String passwordToken) throws MessagingException, UnsupportedEncodingException {
        String code = "votre code est : "+ passwordToken;
        eventListener.sendPasswordResetVerificationEmail(code,user);
        /// log.info("Click the link to reset your password :  {}", url);
        return code;
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }


}