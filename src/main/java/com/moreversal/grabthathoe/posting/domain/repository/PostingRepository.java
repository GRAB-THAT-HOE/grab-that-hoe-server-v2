package com.moreversal.grabthathoe.posting.domain.repository;

import com.moreversal.grabthathoe.posting.domain.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostingRepository extends JpaRepository<Posting, Long> {

}
