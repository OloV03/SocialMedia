package com.IlyaJukov.SocialMedia.models.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor @AllArgsConstructor
public class TargetRequest {
    private Long currentUserId;
    private Long targetUserId;
}
