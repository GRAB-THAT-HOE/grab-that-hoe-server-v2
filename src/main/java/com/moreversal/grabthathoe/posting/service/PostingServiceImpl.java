package com.moreversal.grabthathoe.posting.service;

import com.moreversal.grabthathoe.posting.domain.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostingServiceImpl implements PostingService {

    private final PostingRepository postingRepository;


}
