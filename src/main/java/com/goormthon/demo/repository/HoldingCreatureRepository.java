package com.goormthon.demo.repository;

import com.goormthon.demo.domain.HoldingCreature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HoldingCreatureRepository extends JpaRepository<HoldingCreature, Long> {
}
