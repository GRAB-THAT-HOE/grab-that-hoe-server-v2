package com.moreversal.grabthathoe.connection.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "connection")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Connection {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
