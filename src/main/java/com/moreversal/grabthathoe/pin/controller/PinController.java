package com.moreversal.grabthathoe.pin.controller;

import com.moreversal.grabthathoe.common.annotation.AuthorizationCheck;
import com.moreversal.grabthathoe.common.response.DataResponse;
import com.moreversal.grabthathoe.common.response.Response;
import com.moreversal.grabthathoe.pin.domain.dto.CreatePinDto;
import com.moreversal.grabthathoe.pin.domain.dto.DeletePinDto;
import com.moreversal.grabthathoe.pin.domain.entity.Pin;
import com.moreversal.grabthathoe.pin.service.PinService;
import com.moreversal.grabthathoe.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @AuthorizationCheck
    @PostMapping("/")
    public DataResponse<Pin> createPin(HttpServletRequest request, @RequestBody CreatePinDto dto) {
        User user = (User) request.getAttribute("user");
        Pin createdPin = pinService.createPin(dto, user);
        return new DataResponse<>(HttpStatus.CREATED, "말뚝 생성 성공", null);
    }

    @AuthorizationCheck
    @DeleteMapping("/")
    public Response deletePin(HttpServletRequest request, @RequestBody DeletePinDto dto) {
        User user = (User) request.getAttribute("user");
        pinService.deletePin(dto, user);
        return new Response(HttpStatus.OK, "말뚝 삭제 성공");
    }
}
