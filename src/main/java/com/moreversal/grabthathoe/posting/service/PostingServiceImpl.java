package com.moreversal.grabthathoe.posting.service;

import com.moreversal.grabthathoe.common.exception.ForbiddenException;
import com.moreversal.grabthathoe.common.exception.RecordNotFoundException;
import com.moreversal.grabthathoe.posting.domain.dto.CreatePostingDto;
import com.moreversal.grabthathoe.posting.domain.dto.UpdatePostingDto;
import com.moreversal.grabthathoe.posting.domain.entity.Posting;
import com.moreversal.grabthathoe.posting.domain.enums.PostingStatus;
import com.moreversal.grabthathoe.posting.domain.repository.PostingRepository;
import com.moreversal.grabthathoe.user.domain.entity.User;
import com.moreversal.grabthathoe.user.domain.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostingServiceImpl implements PostingService {

    private final PostingRepository postingRepository;

    @Override
    public Posting getPosting(Long id) {

        Posting posting = postingRepository.findById(id)
                .orElseThrow(RecordNotFoundException::new);

        return posting;
    }

    public List<Posting> getAllPostings() {

        return postingRepository.findAll();
    }

    @Override
    public Posting createPosting(CreatePostingDto dto, User user) {

        if(!user.getUserRole().equals(UserRole.FARMER)) {
            throw new ForbiddenException("농장주가 아니면 구인글을 생성할 수 없습니다.");
        }

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

        return postingRepository.save(posting);
    }

    public Posting updatePosting(UpdatePostingDto dto, User user) {

        Posting posting = postingRepository.findById(dto.getId())
                .orElseThrow(RecordNotFoundException::new);

        if(!posting.getFarmer().getId().equals(user.getId())) {
            throw new ForbiddenException("자신의 게시글이 아니면 수정할 수 없습니다.");
        }

        posting.setTitle(dto.getTitle());
        posting.setExplanation(dto.getExplanation());

        return postingRepository.save(posting);
    }

    public Posting deletePosting(Long id, User user) {

        Posting posting = postingRepository.findById(id)
                .orElseThrow(RecordNotFoundException::new);

        if(!posting.getFarmer().getId().equals(id)) {
            throw new ForbiddenException("자신의 게시글이 아니면 삭제할 수 없습니다.");
        }

        // 이미 연결된 일손이 있는지 체크. 있으면 ForbiddenException throw

        postingRepository.delete(posting);

        return posting;
    }
}
