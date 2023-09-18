package com.subway.repository.materials;

import com.subway.entity.ExtraOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtraOptionRepo extends JpaRepository<ExtraOption, Long> {
}
