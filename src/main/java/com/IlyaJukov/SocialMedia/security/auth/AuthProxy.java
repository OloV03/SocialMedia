package com.IlyaJukov.SocialMedia.security.auth;

import com.IlyaJukov.SocialMedia.models.request.SignInRequest;
import com.IlyaJukov.SocialMedia.models.request.SignUpRequest;
import com.IlyaJukov.SocialMedia.models.response.JwtAuthenticationResponse;

public interface AuthProxy {
    JwtAuthenticationResponse signUp(SignUpRequest request);
    JwtAuthenticationResponse signIn(SignInRequest request);
}
