package com.moreversal.grabthathoe.pin.service;

import com.moreversal.grabthathoe.pin.domain.entity.Pin;
import com.moreversal.grabthathoe.pin.domain.repository.PinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PinServiceImpl implements PinService {

    private final PinRepository pinRepository;

    @Override
    public List<Pin> getPinsByUserId(Long userId) {
        return pinRepository.findAllByUserId(userId);
    }
}
