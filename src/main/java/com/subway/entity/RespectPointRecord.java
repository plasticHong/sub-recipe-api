package com.subway.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "respect_point_records", schema = "sub-recipe")
@NoArgsConstructor
public class RespectPointRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "recipe_id")
    private Long recipeId;

    public RespectPointRecord(Long memberId, Long recipeId) {
        this.memberId = memberId;
        this.recipeId = recipeId;
    }
}
