package org.laquilla.swipe.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.laquilla.swipe.entity.Outfit;
import org.laquilla.swipe.entity.Vote;
import org.laquilla.swipe.repository.OutfitRepository;
import org.laquilla.swipe.repository.VoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor // Lombok ile constructor injection
public class SwipeService {

    private final OutfitRepository outfitRepository;
    private final VoteRepository voteRepository;

    @Transactional // Yazma işlemi olduğu için atomik olmalı
    public void processVote(Long outfitId, String sessionId, boolean isLiked) {
        // 1. Kontrol: Daha önce oylamış mı?
        if (voteRepository.existsByOutfitIdAndSessionId(outfitId, sessionId)) {
            throw new RuntimeException("Zaten oylandı!");
        }

        // 2. Oy Kaydı
        Vote vote = new Vote();
        vote.setOutfitId(outfitId);
        vote.setSessionId(sessionId);
        vote.setLiked(isLiked);
        voteRepository.save(vote);

        // 3. İstatistik Güncelleme
        Outfit outfit = outfitRepository.findById(outfitId)
                .orElseThrow(() -> new EntityNotFoundException("Kombin bulunamadı"));

        if (isLiked) {
            outfit.setLikeCount(outfit.getLikeCount() + 1);
        } else {
            outfit.setDislikeCount(outfit.getDislikeCount() + 1);
        }
        outfitRepository.save(outfit);
    }

    public List<Outfit> getAllOutfits() {
        return outfitRepository.findAll();
    }
}