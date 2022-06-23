package com.moreversal.grabthathoe.posting.controller;

import com.moreversal.grabthathoe.common.annotation.AuthorizationCheck;
import com.moreversal.grabthathoe.common.response.DataResponse;
import com.moreversal.grabthathoe.common.response.Response;
import com.moreversal.grabthathoe.posting.domain.dto.CreatePostingDto;
import com.moreversal.grabthathoe.posting.domain.dto.UpdatePostingDto;
import com.moreversal.grabthathoe.posting.domain.entity.Posting;
import com.moreversal.grabthathoe.posting.service.PostingService;
import com.moreversal.grabthathoe.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/posting")
@RequiredArgsConstructor
public class PostingController {

    private final PostingService postingService;

    @GetMapping("/{id}")
    public DataResponse<Posting> getPostingById(@PathVariable("id") Long id) {
        Posting posting = postingService.getPosting(id);
        return new DataResponse<>(HttpStatus.OK, "포스팅 조회 성공", posting);
    }

    @AuthorizationCheck
    @PostMapping("/")
    public DataResponse<Posting> createPosting(HttpServletRequest request, @RequestBody CreatePostingDto dto) {
        User user = (User) request.getAttribute("user");
        Posting createdPosting = postingService.createPosting(dto, user);
        return new DataResponse<>(HttpStatus.OK, "포스팅 생성 성공", createdPosting);
    }

    @AuthorizationCheck
    @PatchMapping("/")
    public DataResponse<Posting> updatePosting(HttpServletRequest request, @RequestBody UpdatePostingDto dto) {
        User user = (User) request.getAttribute("user");
        Posting updatedPosting = postingService.updatePosting(dto, user);
        return new DataResponse<>(HttpStatus.OK, "포스팅 수정 성공", updatedPosting);
    }

    @AuthorizationCheck
    @DeleteMapping("/{id}")
    public Response deletePosting(HttpServletRequest request, @PathVariable("id") Long id) {
        User user = (User) request.getAttribute("user");
        Posting deletedPosting = postingService.deletePosting(id, user);
        return new Response(HttpStatus.OK, "포스팅 삭제 성공");
    }
}
