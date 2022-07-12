package com.moreversal.grabthathoe.pin.service;

import com.moreversal.grabthathoe.pin.domain.dto.CreatePinDto;
import com.moreversal.grabthathoe.pin.domain.dto.DeletePinDto;
import com.moreversal.grabthathoe.pin.domain.entity.Pin;
import com.moreversal.grabthathoe.user.domain.entity.User;

import java.util.List;

public interface PinService {

    List<Pin> getPinsByUserId(Long userId);
    Pin createPin(CreatePinDto dto, User user);
    void deletePin(DeletePinDto dto, User user);
}
