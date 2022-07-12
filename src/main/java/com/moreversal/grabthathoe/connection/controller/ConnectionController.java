package com.moreversal.grabthathoe.connection.controller;

import com.moreversal.grabthathoe.common.response.DataResponse;
import com.moreversal.grabthathoe.connection.domain.dto.CreateConnectionDto;
import com.moreversal.grabthathoe.connection.domain.entity.Connection;
import com.moreversal.grabthathoe.connection.service.ConnectionService;
import com.moreversal.grabthathoe.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/connection")
@RequiredArgsConstructor
public class ConnectionController {

    private final ConnectionService connectionService;

    @GetMapping("/{id}")
    public DataResponse<List<Connection>> getConnectionsByPostingId(@PathVariable("id") Long postingId) {
        List<Connection> connections = connectionService.getConnectionsByPostingId(postingId);
        return new DataResponse<>(HttpStatus.OK, "게시글의 연결 조회 성공", connections);
    }

    @PostMapping("/")
    public DataResponse<Connection> createConnection(HttpServletRequest request, @RequestBody CreateConnectionDto dto) {
        User user = (User) request.getAttribute("user");
        Connection createdConnection = connectionService.createConnection(dto, user);
        return new DataResponse<>(HttpStatus.CREATED, "연결 생성 성공", createdConnection);
    }


}
