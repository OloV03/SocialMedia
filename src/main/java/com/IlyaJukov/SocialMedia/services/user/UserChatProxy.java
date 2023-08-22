package com.IlyaJukov.SocialMedia.services.user;

import com.IlyaJukov.SocialMedia.models.User;

public interface UserChatProxy {
    void startChat(User member1, User member2);
}
