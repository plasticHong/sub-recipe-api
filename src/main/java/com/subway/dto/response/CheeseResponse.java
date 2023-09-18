package com.subway.dto.response;

import com.subway.dto.data.CheeseData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class CheeseResponse {
    private final List<CheeseData> cheeseDataList;
}