package com.moreversal.grabthathoe.user.domain.ro;

import com.moreversal.grabthathoe.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UserLoginRo {

    private User user;
    private String accessToken;
    private String refreshToken;
}
