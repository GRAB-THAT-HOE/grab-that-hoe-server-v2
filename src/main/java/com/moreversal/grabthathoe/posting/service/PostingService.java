package com.moreversal.grabthathoe.posting.service;

import com.moreversal.grabthathoe.posting.domain.dto.CreatePostingDto;
import com.moreversal.grabthathoe.posting.domain.dto.UpdatePostingDto;
import com.moreversal.grabthathoe.posting.domain.entity.Posting;
import com.moreversal.grabthathoe.user.domain.entity.User;

public interface PostingService {

    Posting getPosting(Long id);
    Posting createPosting(CreatePostingDto dto, User user);
    Posting updatePosting(UpdatePostingDto dto, User user);
    Posting deletePosting(Long id, User user);
}
