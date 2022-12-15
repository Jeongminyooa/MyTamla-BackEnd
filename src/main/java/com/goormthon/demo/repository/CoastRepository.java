package com.goormthon.demo.repository;

import com.goormthon.demo.domain.Coast;
import com.goormthon.demo.domain.CoastType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoastRepository extends JpaRepository<Coast, Long> {
    List<Coast> findByCoastType(CoastType type);
}
