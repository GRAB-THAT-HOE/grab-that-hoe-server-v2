package com.moreversal.grabthathoe.pin.domain.entity;

import com.moreversal.grabthathoe.posting.domain.entity.Posting;
import com.moreversal.grabthathoe.user.domain.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "pin")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pin {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posting_id")
    private Posting posting;
}
