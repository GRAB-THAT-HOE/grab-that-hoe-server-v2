package com.moreversal.grabthathoe.pin.service;

import com.moreversal.grabthathoe.common.exception.ForbiddenException;
import com.moreversal.grabthathoe.common.exception.RecordNotFoundException;
import com.moreversal.grabthathoe.pin.domain.dto.CreatePinDto;
import com.moreversal.grabthathoe.pin.domain.dto.DeletePinDto;
import com.moreversal.grabthathoe.pin.domain.entity.Pin;
import com.moreversal.grabthathoe.pin.domain.repository.PinRepository;
import com.moreversal.grabthathoe.posting.domain.entity.Posting;
import com.moreversal.grabthathoe.posting.domain.repository.PostingRepository;
import com.moreversal.grabthathoe.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PinServiceImpl implements PinService {

    private final PinRepository pinRepository;
    private final PostingRepository postingRepository;

    @Override
    public List<Pin> getPinsByUserId(Long userId) {
        return pinRepository.findAllByUserId(userId);
    }

    @Override
    public Pin createPin(CreatePinDto dto, User user) {

        Posting posting = postingRepository.findById(dto.getPostingId())
                .orElseThrow(RecordNotFoundException::new);

        Pin pin = Pin.builder()
                .user(user)
                .posting(posting)
                .build();

        return pinRepository.save(pin);
    }

    @Override
    public void deletePin(DeletePinDto dto, User user) {

        Pin pin = pinRepository.findById(dto.getPostingId())
                .orElseThrow(RecordNotFoundException::new);

        if(!pin.getUser().getId().equals(user.getId())) {
            throw new ForbiddenException("자신의 핀이 아니면 삭제할 수 없습니다.");
        }

        pinRepository.delete(pin);
    }
}
