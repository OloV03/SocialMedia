package com.IlyaJukov.SocialMedia.security.jwt;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtProxy {
    String extractUserName(String token);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);
}
