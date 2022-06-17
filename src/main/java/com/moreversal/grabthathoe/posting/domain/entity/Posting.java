package com.moreversal.grabthathoe.posting.domain.entity;

import com.moreversal.grabthathoe.posting.domain.enums.PostingStatus;
import com.moreversal.grabthathoe.user.domain.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "posting")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Posting {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 글의 제목
    @Column(nullable = false)
    private String title;

    // 글의 자세한 설명
    @Column(nullable = false)
    private String explanation;

    // 구할 최대 일손 인원
    @Column(nullable = false)
    private int maxWorker;

    // 숙식 제공 여부
    @Column(nullable = false)
    private boolean giveRoomAndMeal;

    // 새참 제공 여부
    @Column(nullable = false)
    private boolean giveSnack;

    // 농장/소개 사진
    @Column(nullable = false)
    private String image;

    // 밭일 시작 일시
    @Column(nullable = false)
    private LocalDateTime startWorkingDate;

    // 밭일 마감 일시
    @Column(nullable = false)
    private LocalDateTime endWorkingDate;

    // 급여
    @Column(nullable = false)
    private Long pay;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PostingStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farmer_id")
    private User farmer;

    @Column(nullable = false, length = 45)
    private String regId;

    @Column(nullable = false)
    private LocalDateTime regDt;

    @Column(nullable = false, length = 45)
    private String updId;

    @Column(nullable = false)
    private LocalDateTime updDt;

    @Builder
    public Posting(String title, String explanation, int maxWorker, boolean giveRoomAndMeal, boolean giveSnack, String image, LocalDateTime startDate, LocalDateTime endDate, Long pay, PostingStatus status, User farmer, String regId, String updId) {


        this.title = title;
        this.explanation = explanation;
        this.maxWorker = maxWorker;
        this.giveRoomAndMeal = giveRoomAndMeal;
        this.giveSnack = giveSnack;
        this.image = image;
        this.startWorkingDate = startDate;
        this.endWorkingDate = endDate;
        this.pay = pay;
        this.status = status;
        this.farmer = farmer;
        this.regId = regId;
        this.updId = updId;
    }
}
