package com.moreversal.grabthathoe.user.service;

import com.moreversal.grabthathoe.user.domain.dto.UserJoinDto;
import com.moreversal.grabthathoe.user.domain.entity.User;

public interface UserService {
    User join(UserJoinDto userJoinDto);
}
