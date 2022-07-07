package com.moreversal.grabthathoe.pin.domain.repository;

import com.moreversal.grabthathoe.pin.domain.entity.Pin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PinRepository extends JpaRepository<Pin, Long> {

    List<Pin> findAllByUserId(Long userId);
}
