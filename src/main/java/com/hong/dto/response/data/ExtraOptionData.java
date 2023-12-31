package com.hong.dto.response.data;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ExtraOptionData {

    private Long id;
    private String name;
    private Integer price;
    private String image;
    private Double kcal;
    private Double protein;
    private Double fat;

}
