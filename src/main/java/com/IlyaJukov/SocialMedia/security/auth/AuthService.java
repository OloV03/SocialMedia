package com.IlyaJukov.SocialMedia.security.auth;

import com.IlyaJukov.SocialMedia.models.User;
import com.IlyaJukov.SocialMedia.models.request.SignInRequest;
import com.IlyaJukov.SocialMedia.models.request.SignUpRequest;
import com.IlyaJukov.SocialMedia.models.response.JwtAuthenticationResponse;
import com.IlyaJukov.SocialMedia.security.jwt.JwtProxy;
import com.IlyaJukov.SocialMedia.services.user.UserRepository;
import com.IlyaJukov.SocialMedia.services.user.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service @RequiredArgsConstructor
public class AuthService implements AuthProxy {

    private final UserService service;
    private final PasswordEncoder passwordEncoder;
    private final JwtProxy jwtProxy;
    private final AuthenticationManager authenticationManager;
    @Override
    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        User user = new User(
                request.getName(),
                request.getLogin(),
                passwordEncoder.encode(request.getPassword())
        );
        service.save(user);

        var jwt = jwtProxy.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword()));
        User user = service.getUserByLogin(request.getLogin());

        var jwt = jwtProxy.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public String handleEntityNotFoundException() {
        return "Invalid login or password";
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException() {
        return "Login is already exist";
    }
    
}
