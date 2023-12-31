package com.hong.dto.Request;

import lombok.Getter;

@Getter
public class RecipeSearchCondition {
    private Long sandwichBaseId;
    private Long memberId;
    private Boolean isWithOutCucumber;

    private Double maxKcal;
    private Double minKcal;

    private Integer maxPrice;
    private Integer minPrice;

    private Double maxFat;
    private Double minFat;

    private Double maxProtein;
    private Double minProtein;
}
