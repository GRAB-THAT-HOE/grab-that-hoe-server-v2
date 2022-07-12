package com.moreversal.grabthathoe.connection.domain.repository;

import com.moreversal.grabthathoe.connection.domain.entity.Connection;
import com.moreversal.grabthathoe.posting.domain.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConnectionRepository extends JpaRepository<Connection, Long> {

    List<Connection> findAllByPosting(Posting posting);
}
