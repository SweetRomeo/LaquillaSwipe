package org.laquilla.swipe.repository;

import org.laquilla.swipe.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    boolean existsByOutfitIdAndSessionId(Long outfitId, String sessionId);

}
