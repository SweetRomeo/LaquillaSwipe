package org.laquilla.swipe.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "outfits")
@Data
public class Outfit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;
    private String description;

    @Column(name = "like_count")
    private int likeCount = 0;

    @Column(name = "dislike_count")
    private int dislikeCount = 0;
}
