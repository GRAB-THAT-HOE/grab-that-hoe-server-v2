package com.moreversal.grabthathoe.user.service;

import com.moreversal.grabthathoe.user.domain.dto.UserJoinDto;
import com.moreversal.grabthathoe.user.domain.dto.UserLoginDto;
import com.moreversal.grabthathoe.user.domain.entity.User;
import com.moreversal.grabthathoe.user.domain.ro.TokenRefreshRo;
import com.moreversal.grabthathoe.user.domain.ro.UserLoginRo;

public interface UserService {
    User join(UserJoinDto userJoinDto);
    UserLoginRo login(UserLoginDto userLoginDto);
    TokenRefreshRo refresh(String refreshToken);
    User getUser(Long id);
}
