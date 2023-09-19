package com.subway.dto.response;

import com.subway.dto.data.SauceData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class SauceResponse {
    private final List<SauceData> sauceDataList;
}