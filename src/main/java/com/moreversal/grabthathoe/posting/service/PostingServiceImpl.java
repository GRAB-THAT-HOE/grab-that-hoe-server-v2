package com.moreversal.grabthathoe.posting.service;

import com.moreversal.grabthathoe.common.exception.RecordNotFoundException;
import com.moreversal.grabthathoe.posting.domain.dto.CreatePostingDto;
import com.moreversal.grabthathoe.posting.domain.entity.Posting;
import com.moreversal.grabthathoe.posting.domain.enums.PostingStatus;
import com.moreversal.grabthathoe.posting.domain.repository.PostingRepository;
import com.moreversal.grabthathoe.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostingServiceImpl implements PostingService {

    private final PostingRepository postingRepository;

    @Override
    public Posting getPosting(Long id) {

        Posting posting = postingRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException());

        return posting;
    }

    public List<Posting> getAllPostings() {

        return postingRepository.findAll();
    }

    @Override
    public Posting createPosting(CreatePostingDto dto, User user) {

        Posting posting = Posting.builder()
                .title(dto.getTitle())
                .explanation(dto.getExplanation())
                .maxWorker(dto.getMaxWorker())
                .giveRoomAndMeal(dto.isGiveRoomAndMeal())
                .giveSnack(dto.isGiveSnack())
                .image(dto.getImage())
                .startDate(dto.getStartWorkingDate())
                .endDate(dto.getEndWorkingDate())
                .pay(dto.getPay())
                .status(PostingStatus.NORMAL)
                .farmer(user)
                .regId("test")
                .updId("test")
                .build();

        return posting;
    }
}
