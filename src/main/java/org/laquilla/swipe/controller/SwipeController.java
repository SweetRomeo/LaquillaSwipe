package org.laquilla.swipe.controller;

import lombok.RequiredArgsConstructor;
import org.laquilla.swipe.dto.VoteRequest;
import org.laquilla.swipe.entity.Outfit;
import org.laquilla.swipe.service.SwipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/swipe")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000") // React portu
public class SwipeController {

    private final SwipeService swipeService;

    @GetMapping("/outfits")
    public ResponseEntity<List<Outfit>> getAll() {
        return ResponseEntity.ok(swipeService.getAllOutfits());
    }

    @PostMapping("/vote")
    public ResponseEntity<String> vote(@RequestBody VoteRequest request) {
        try {
            swipeService.processVote(request.getOutfitId(), request.getSessionId(), request.isLiked());
            return ResponseEntity.ok("Oy kaydedildi.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
