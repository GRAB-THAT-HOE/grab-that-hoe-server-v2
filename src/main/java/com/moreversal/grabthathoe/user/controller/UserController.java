package com.moreversal.grabthathoe.user.controller;

import com.moreversal.grabthathoe.common.response.DataResponse;
import com.moreversal.grabthathoe.user.domain.dto.UserJoinDto;
import com.moreversal.grabthathoe.user.domain.dto.UserLoginDto;
import com.moreversal.grabthathoe.user.domain.entity.User;
import com.moreversal.grabthathoe.user.domain.ro.TokenRefreshRo;
import com.moreversal.grabthathoe.user.domain.ro.UserLoginRo;
import com.moreversal.grabthathoe.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public DataResponse<User> join(@RequestBody @Valid UserJoinDto userJoinDto) {
        User createdUser = userService.join(userJoinDto);
        return new DataResponse<>(HttpStatus.CREATED, "회원가입 성공", createdUser);
    }

    @PostMapping("/login")
    public DataResponse<UserLoginRo> login(@RequestBody @Valid UserLoginDto loginDto) {
        UserLoginRo loginRo = userService.login(loginDto);
        return new DataResponse<>(HttpStatus.OK, "로그인 성공", loginRo);
    }

    @PostMapping("/refresh")
    public DataResponse<TokenRefreshRo> refresh(@RequestBody String refreshToken) {
        TokenRefreshRo tokenRefreshRo = userService.refresh(refreshToken);
        return new DataResponse<>(HttpStatus.OK, "토큰 재발급 성공", tokenRefreshRo);
    }

    @GetMapping("/{id}")
    public DataResponse<User> getUser(@PathVariable("id") Long id) {
        User user = userService.getUser(id);
        return new DataResponse<>(HttpStatus.OK, "유저 조회 성공", user);
    }
}
