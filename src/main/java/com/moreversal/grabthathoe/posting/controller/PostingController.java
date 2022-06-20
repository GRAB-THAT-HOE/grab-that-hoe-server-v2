package com.moreversal.grabthathoe.posting.controller;

import com.moreversal.grabthathoe.common.response.DataResponse;
import com.moreversal.grabthathoe.posting.domain.entity.Posting;
import com.moreversal.grabthathoe.posting.service.PostingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
