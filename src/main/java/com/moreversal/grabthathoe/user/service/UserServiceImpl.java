package com.moreversal.grabthathoe.user.service;

import com.moreversal.grabthathoe.user.domain.dto.UserJoinDto;
import com.moreversal.grabthathoe.user.domain.entity.User;
import com.moreversal.grabthathoe.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User join(UserJoinDto userJoinDto) {
        if(userRepository.existsUserByPhone(userJoinDto.getPhone())) {
            throw new EntityExistsException();
        }

        User user = User.builder()
                .phone(userJoinDto.getPhone())
                .name(userJoinDto.getName())
                .birthDate(userJoinDto.getBirthDate())
                .status("1")
                .role(userJoinDto.getRole())
                .build();

        return userRepository.save(user);
    }
}
