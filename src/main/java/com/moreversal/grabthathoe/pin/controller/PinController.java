package com.moreversal.grabthathoe.pin.controller;

import com.moreversal.grabthathoe.common.annotation.AuthorizationCheck;
import com.moreversal.grabthathoe.common.response.DataResponse;
import com.moreversal.grabthathoe.pin.domain.entity.Pin;
import com.moreversal.grabthathoe.pin.service.PinService;
import com.moreversal.grabthathoe.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/pin")
@RequiredArgsConstructor
public class PinController {

    private final PinService pinService;

    @AuthorizationCheck
    @GetMapping("/")
    public DataResponse<List<Pin>> getPinsByUserId(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        List<Pin> pins = pinService.getPinsByUserId(user.getId());
        return new DataResponse<>(HttpStatus.OK, "사용자의 말뚝 조회 성공", pins);
    }
}
