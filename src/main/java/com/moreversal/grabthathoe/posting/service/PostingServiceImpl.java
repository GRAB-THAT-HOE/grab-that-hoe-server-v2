package com.moreversal.grabthathoe.posting.service;

import com.moreversal.grabthathoe.common.exception.RecordNotFoundException;
import com.moreversal.grabthathoe.posting.domain.entity.Posting;
import com.moreversal.grabthathoe.posting.domain.repository.PostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostingServiceImpl implements PostingService {

    private final PostingRepository postingRepository;

    @Override
    public Posting getPosting(Long id) {
        Optional<Posting> posting = postingRepository.findById(id);
        if (!posting.isPresent()) {
            throw new RecordNotFoundException();
        }
        return posting.get();
    }
}
