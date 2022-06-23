package com.moreversal.grabthathoe.user.service;

import com.moreversal.grabthathoe.common.enums.JwtType;
import com.moreversal.grabthathoe.common.exception.DuplicateRecordException;
import com.moreversal.grabthathoe.common.exception.RecordNotFoundException;
import com.moreversal.grabthathoe.common.lib.Jwt;
import com.moreversal.grabthathoe.user.domain.dto.UserJoinDto;
import com.moreversal.grabthathoe.user.domain.dto.UserLoginDto;
import com.moreversal.grabthathoe.user.domain.entity.User;
import com.moreversal.grabthathoe.user.domain.enums.UserStatus;
import com.moreversal.grabthathoe.user.domain.repository.UserRepository;
import com.moreversal.grabthathoe.user.domain.ro.TokenRefreshRo;
import com.moreversal.grabthathoe.user.domain.ro.UserLoginRo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Jwt jwt;

    @Override
    public User join(UserJoinDto userJoinDto) {
        if(userRepository.existsUserByPhone(userJoinDto.getPhone())) {
            throw new DuplicateRecordException("해당 id가 이미 존재합니다.");
        }

        User user = User.builder()
                .phone(userJoinDto.getPhone())
                .name(userJoinDto.getName())
                .birthDate(userJoinDto.getBirthDate())
                .status(UserStatus.NORMAL)
                .userRole(userJoinDto.getUserRole())
                .regId("test")
                .updId("test")
                .build();

        return userRepository.save(user);
    }

    @Override
    public UserLoginRo login(UserLoginDto userLoginDto) {

        User user = userRepository.findUserByPhone(userLoginDto.getPhone())
                .orElseThrow(RecordNotFoundException::new);

        String accessToken = jwt.createToken(user, JwtType.ACCESS);
        String refreshToken = jwt.createToken(user, JwtType.REFRESH);

        UserLoginRo loginRo = UserLoginRo.builder()
                .user(user)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        return loginRo;
    }

    @Override
    public TokenRefreshRo refresh(String refreshToken) {

        String newAccessToken = jwt.refresh(refreshToken);
        return new TokenRefreshRo(newAccessToken);
    }

    @Override
    public User getUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(RecordNotFoundException::new);
        return user;
    }
}

