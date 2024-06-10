package com.iteam.Gestion.Expert.auth;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.iteam.Gestion.Expert.configmail.MailService;
import com.iteam.Gestion.Expert.configsecurite.AppConstants;
import com.iteam.Gestion.Expert.configsecurite.JwtService;
import com.iteam.Gestion.Expert.dto.Admindto;
import com.iteam.Gestion.Expert.dto.Expertdto;
import com.iteam.Gestion.Expert.dto.ResponsableSocietedto;
import com.iteam.Gestion.Expert.entities.*;
import com.iteam.Gestion.Expert.reposetories.UserRepository;
import com.iteam.Gestion.Expert.reposetories.VerificationTokenRepository;
import com.iteam.Gestion.Expert.token.Token;
import com.iteam.Gestion.Expert.token.TokenRepository;
import com.iteam.Gestion.Expert.token.TokenType;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final VerificationTokenRepository vtokenRepository;
    private final PasswordResetTokenService passwordResetTokenService;
    private final MailService mailService;

    public Messgchekmail register(RegisterRequest request) {
        User user;
        User savedUser = null;

        if (request instanceof Admindto) {
            user = new Admin();
            user = Admindto.toEntity((Admindto) request);
            user.setPassword(passwordEncoder.encode(user.getPassword()));


            user.setRole(Role.ADMIN);
            savedUser = repository.save((Admin) user);
            var jwtToken = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(user);
            saveUserToken(savedUser, jwtToken);
            //verification email
            final String token = UUID.randomUUID().toString();
            createVerificationTokenForUser(user, token);
            mailService.sendVerificationToken(token, user);
            return Messgchekmail.builder()
                    .msg("Success! Please, check your email to complete your registration")
                    .build();

        }

        if (request instanceof Expertdto) {
            user = new Expert();
            user = Expertdto.toEntity((Expertdto) request);
            user.setPassword(passwordEncoder.encode(user.getPassword()));


            user.setRole(Role.EXPERT);
            savedUser = repository.save((Expert) user);
            var jwtToken = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(user);
            saveUserToken(savedUser, jwtToken);
            //verification email
            final String token = UUID.randomUUID().toString();
            createVerificationTokenForUser(user, token);
            mailService.sendVerificationToken(token, user);
            return Messgchekmail.builder()
                    .msg("Success! Please, check your email to complete your registration")
                    .build();

        }

        if (request instanceof ResponsableSocietedto) {
            user = new ResponsableSociete();
            user = ResponsableSocietedto.toEntity((ResponsableSocietedto) request);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
System.err.println(user.getFullname());

            user.setRole(Role.RESP_STE);
            savedUser = repository.save((ResponsableSociete) user);
            var jwtToken = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(user);
            saveUserToken(savedUser, jwtToken);
            //verification email
            final String token = UUID.randomUUID().toString();
            createVerificationTokenForUser(user, token);
            mailService.sendVerificationToken(token, user);
            return Messgchekmail.builder()
                    .msg("Success! Please, check your email to complete your registration")
                    .build();

        }

        return Messgchekmail.builder()
                .msg("Failed! Please, check your email to register")
                .build();

    }

    public String validateVerificationToken(String token) {
        Optional<VerificationToken> verificationToken = vtokenRepository.findByToken(token);
        if (verificationToken == null) {
            return AppConstants.TOKEN_INVALID;
        }

        final User user = verificationToken.get().getUser();
        final Calendar cal = Calendar.getInstance();
        if ((verificationToken.get().getExpirationTime().getTime() - cal.getTime().getTime()) <= 0) {
            return AppConstants.TOKEN_EXPIRED;
        }

        user.setActive(true);


        mailService.sendTextEmail(user.getEmail(),"vous etes le bienvenu","vous etes le bienvenu");
        vtokenRepository.deleteById(verificationToken.get().getId());
        repository.save(user);
        return AppConstants.TOKEN_VALID;
    }

    public void createVerificationTokenForUser(final User user, final String token) {
        final VerificationToken myToken = new VerificationToken(token, user);
        vtokenRepository.save(myToken);
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var claims = new HashMap<String, Object>();
        claims.put("fullname", user.getFullname());
        claims.put("role", user.getRole().name());
       claims.put("userId", user.getId());
        System.out.println(claims);

        var jwtToken = jwtService.generateToken(claims,user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }


    public void changePassword(User theUser, String newPassword) {
        theUser.setPassword(passwordEncoder.encode(newPassword));
        repository.save(theUser);
    }
    public String validatePasswordResetToken(String token) {
        return passwordResetTokenService.validatePasswordResetToken(token);
    }

    public User findUserByPasswordToken(String token) {
        return passwordResetTokenService.findUserByPasswordToken(token).get();
    }


    public void createPasswordResetTokenForUser(User user, String passwordResetToken) {
        passwordResetTokenService.createPasswordResetTokenForUser(user, passwordResetToken);
    }

    public boolean oldPasswordIsValid(User user, String oldPassword){
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }





    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.repository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
    @PostConstruct
    public void createDefaultAdmin() {
        User user =new Admin();
        String email = "admin@mail.com";
        if (!repository.existsByEmail(email)) {
            user.setEmail("admin@mail.com");
            user.setFullname("Sofiene");
            user.setPhone("198");
            user.setActive(true);
            user.setPassword(passwordEncoder.encode("admin"));
    /*        List<Role> roles = new ArrayList<>();
            Role userRole = roleRepository.findByName("AdminSystem")
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);*/
            user.setRole(Role.ADMIN);
            repository.save(user);
        }
    }

}