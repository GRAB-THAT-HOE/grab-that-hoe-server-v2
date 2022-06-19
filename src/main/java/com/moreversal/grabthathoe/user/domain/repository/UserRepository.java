package com.moreversal.grabthathoe.user.domain.repository;

import com.moreversal.grabthathoe.user.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsUserByPhone(String phone);
    Optional<User> getUserByPhone(String phone);
}
