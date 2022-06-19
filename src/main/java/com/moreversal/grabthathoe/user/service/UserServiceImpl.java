package com.moreversal.grabthathoe.user.service;

import com.moreversal.grabthathoe.common.enums.JwtType;
import com.moreversal.grabthathoe.common.exception.PhoneExistsException;
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

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Jwt jwt;

    @Override
    public User join(UserJoinDto userJoinDto) {
        if(userRepository.existsUserByPhone(userJoinDto.getPhone())) {
            throw new PhoneExistsException("해당 id가 이미 존재합니다.");
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

        Optional<User> user = userRepository.getUserByPhone(userLoginDto.getPhone());

        if(!user.isPresent()) {
            throw new RuntimeException("해당 로그인 정보에 대한 회원이 없습니다.");
        }

        String accessToken = jwt.createToken(user.get(), JwtType.ACCESS);
        String refreshToken = jwt.createToken(user.get(), JwtType.REFRESH);

        UserLoginRo loginRo = UserLoginRo.builder()
                .user(user.get())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        return loginRo;
    }

    public TokenRefreshRo refresh(String accessToken) {

        String newAccessToken = jwt.refresh(accessToken);
        return new TokenRefreshRo(newAccessToken);
    }
}

