package com.moreversal.grabthathoe.pin.domain.repository;

import com.moreversal.grabthathoe.pin.domain.entity.Pin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PinRepository extends JpaRepository<Pin, Long> {

}
