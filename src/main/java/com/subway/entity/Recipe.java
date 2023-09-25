package com.subway.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Entity
@Table(name = "recipe", schema = "sub-recipe")
@NoArgsConstructor
@ToString
public class Recipe{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "title")
    private String title;

    @Column(name = "sandwich_base_id")
    private Long sandwichBaseId;

    @Column(name = "description")
    private String description;

    @Column(name = "is_baked")
    private boolean isBaked;

    /*
     * sandwichBase 기본 wheat(위트)
     * */
    @Column(name = "bread_id")
    private Long breadId;

    @Column(name = "cheese_id")
    private Long cheeseId;

    /*
     * 제외 야채
     * */
    @Column(name = "veggie_1")
    private Long veggie1;

    @Column(name = "veggie_2")
    private Long veggie2;

    @Column(name = "veggie_3")
    private Long veggie3;

    @Column(name = "veggie_4")
    private Long veggie4;

    @Column(name = "veggie_5")
    private Long veggie5;

    @Column(name = "veggie_6")
    private Long veggie6;

    @Column(name = "veggie_7")
    private Long veggie7;

    @Column(name = "veggie_8")
    private Long veggie8;

    /*
     * source
     * maximum 3
     * */
    @Column(name = "sauce_1")
    private Long sauceId1;

    @Column(name = "sauce_2")
    private Long sauceId2;

    @Column(name = "sauce_3")
    private Long sauceId3;


    @Column(name = "total_price")
    private Integer total_price;

    @Column(name = "total_kcal")
    private Double totalKcal;

    @Column(name = "total_protein")
    private Double totalProtein;

    @Column(name = "total_fat")
    private Double totalFat;

    @Column(name = "star_point")
    private Integer starPoint;

    @Column(name = "respect_point")
    private Integer respectPoint;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @PrePersist
    public void onPrePersist() {
        this.createTime = LocalDateTime.now();
    }

    @Builder
    public Recipe(Long memberId, String title, Long sandwichBaseId, String description,
                  Long breadId, Long cheeseId,
                  Integer total_price, Double totalKcal, Double totalProtein, Double totalFat,
                  List<Long> veggieIds, List<Long> sauceIds) {

        this.memberId = memberId;
        this.title = title;
        this.sandwichBaseId = sandwichBaseId;
        this.description = description;
        this.breadId = breadId;
        this.cheeseId = cheeseId;
        this.total_price = total_price;
        this.totalKcal = totalKcal;
        this.totalProtein = totalProtein;
        this.totalFat = totalFat;

        setExceptionVeggies(veggieIds);
        setSauces(sauceIds);
    }

    private void setExceptionVeggies(List<Long> veggieIds) {

        int length = veggieIds.size();

        this.veggie1 = veggieIds.get(0);
        if (length < 2) {
            return;
        }
        this.veggie2 = veggieIds.get(1);
        if (length < 3) {
            return;
        }
        this.veggie3 = veggieIds.get(2);
        if (length < 4) {
            return;
        }
        this.veggie4 = veggieIds.get(3);
        if (length < 5) {
            return;
        }
        this.veggie5 = veggieIds.get(4);
        if (length < 6) {
            return;
        }
        this.veggie6 = veggieIds.get(5);
        if (length < 7) {
            return;
        }
        this.veggie7 = veggieIds.get(6);
        if (length < 8) {
            return;
        }
        this.veggie8 = veggieIds.get(7);

    }

    private void setSauces(List<Long> sauceIds) {

        int length = sauceIds.size();

        this.sauceId1 = sauceIds.get(0);
        if (length < 2) {
            return;
        }
        this.sauceId2 = sauceIds.get(1);
        if (length < 3) {
            return;
        }
        this.sauceId3 = sauceIds.get(2);

    }

}
