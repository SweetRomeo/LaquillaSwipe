package org.laquilla.swipe.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
// Aynı session_id'nin aynı outfit_id'ye birden fazla oy vermesini veritabanı seviyesinde kesin olarak engellemek ve sorguları hızlandırmak için Unique Index ekliyoruz.
@Table(name = "votes", indexes = {
        @Index(name = "idx_outfit_session", columnList = "outfit_id, session_id", unique = true)
})
@Data
@NoArgsConstructor
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "outfit_id", nullable = false)
    private Long outfitId;

    // Kullanıcının tarayıcısına atayacağımız anonim kimlik
    @Column(name = "session_id", nullable = false, length = 255)
    private String sessionId;

    @Column(name = "is_liked", nullable = false)
    private boolean isLiked; // true = sağa kaydırdı, false = sola kaydırdı

    // İleride "Bugün kaç oy kullanıldı?" gibi istatistikler çekmek istersen diye zaman damgası
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}