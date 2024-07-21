package com.travelcreation.mbti.domain.location.repository;

import com.travelcreation.mbti.domain.location.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Long> {
    List<LocationEntity> findByTitleContainingIgnoreCaseAndAreaCodeAndSigunguCode(String title, String areaCode, String sigunguCode);
    List<LocationEntity> findByTitleContainingIgnoreCase(String title);
    Page<LocationEntity> findByTitleContainingIgnoreCaseAndAreaCodeAndSigunguCode(String title, String areaCode, String sigunguCode, Pageable pageable);
    Page<LocationEntity> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}