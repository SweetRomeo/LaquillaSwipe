package org.laquilla.swipe.dto;

import lombok.Data;

@Data
public class VoteRequest {
    private Long outfitId;
    private String sessionId;
    private boolean isLiked;
}
