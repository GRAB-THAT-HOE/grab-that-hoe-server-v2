package com.moreversal.grabthathoe.user.domain.ro;

import com.moreversal.grabthathoe.user.domain.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserLoginRo {

    private User user;
    private String accessToken;
    private String refreshToken;

    @Builder
    public UserLoginRo(User user, String accessToken, String refreshToken) {
        this.user = user;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
