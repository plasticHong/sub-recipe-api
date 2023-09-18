package com.subway.sevice;

import com.subway.dto.data.BreadData;
import com.subway.dto.data.ExtraOptionData;
import com.subway.dto.data.SandwichBaseData;
import com.subway.dto.response.BreadResponse;
import com.subway.dto.response.ExtraOptionResponse;
import com.subway.dto.response.SandwichBaseResponse;
import com.subway.entity.Bread;
import com.subway.entity.ExtraOption;
import com.subway.entity.SandwichBase;
import com.subway.repository.materials.BreadRepo;
import com.subway.repository.materials.ExtraOptionRepo;
import com.subway.repository.materials.SandwichBaseRepo;
import com.subway.utils.ObjectMappingUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialFindService {

    private final ObjectMappingUtils mappingUtils;
    private final SandwichBaseRepo sandwichBaseRepo;
    private final BreadRepo breadRepo;
    private final ExtraOptionRepo extraOptionRepo;


    public ExtraOptionResponse getAllExtraOptions() {

        List<ExtraOption> extraOptionList = extraOptionRepo.findAll();
        List<ExtraOptionData> extraOptionDataList = mappingUtils.entityListToDtoList(extraOptionList, ExtraOptionData.class);

        return new ExtraOptionResponse(extraOptionDataList);
    }

    public SandwichBaseResponse getAllSandwichBases() {

        List<SandwichBase> sandwichBaseList = sandwichBaseRepo.findAll();
        List<SandwichBaseData> dtoList = mappingUtils.entityListToDtoList(sandwichBaseList, SandwichBaseData.class);

        return new SandwichBaseResponse(dtoList);
    }

    public BreadResponse getAllBreads() {

        List<Bread> breadList = breadRepo.findAll();
        List<BreadData> breadDataList = mappingUtils.entityListToDtoList(breadList, BreadData.class);

        return new BreadResponse(breadDataList);
    }

}
