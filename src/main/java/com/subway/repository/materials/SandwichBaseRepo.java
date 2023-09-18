package com.subway.repository.materials;

import com.subway.entity.SandwichBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SandwichBaseRepo extends JpaRepository<SandwichBase,Long> {
}
