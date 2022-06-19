package com.moreversal.grabthathoe.user.controller;

import com.moreversal.grabthathoe.common.response.DataResponse;
import com.moreversal.grabthathoe.user.domain.dto.UserJoinDto;
import com.moreversal.grabthathoe.user.domain.dto.UserLoginDto;
import com.moreversal.grabthathoe.user.domain.entity.User;
import com.moreversal.grabthathoe.user.domain.ro.UserLoginRo;
import com.moreversal.grabthathoe.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public DataResponse<User> join(@RequestBody UserJoinDto userJoinDto) {
        User createdUser = userService.join(userJoinDto);
        return new DataResponse<>(HttpStatus.OK, "회원가입 성공", createdUser);
    }

    @PostMapping("/login")
    public DataResponse<UserLoginRo> login(@RequestBody UserLoginDto loginDto) {
        UserLoginRo loginRo = userService.login(loginDto);
        return new DataResponse<>(HttpStatus.OK, "로그인 성공", loginRo);
    }
}
