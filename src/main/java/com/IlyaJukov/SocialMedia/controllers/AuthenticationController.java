package com.IlyaJukov.SocialMedia.controllers;

import com.IlyaJukov.SocialMedia.models.request.SignInRequest;
import com.IlyaJukov.SocialMedia.models.request.SignUpRequest;
import com.IlyaJukov.SocialMedia.models.response.JwtAuthenticationResponse;
import com.IlyaJukov.SocialMedia.security.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthService authenticationService;

    @PostMapping("/signUp")
    public ResponseEntity<JwtAuthenticationResponse> signUp(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signUp(request));
    }

    @PostMapping("/signIn")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(authenticationService.signIn(request));
    }

}
