package com.moreversal.grabthathoe.pin.service;

import com.moreversal.grabthathoe.pin.domain.entity.Pin;

import java.util.List;

public interface PinService {

    List<Pin> getPinsByUserId(Long userId);
}
