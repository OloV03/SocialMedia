package com.IlyaJukov.SocialMedia.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class JwtAuthenticationResponse {
    private String token;
}
