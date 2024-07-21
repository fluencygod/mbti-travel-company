package com.travelcreation.mbti.domain.location.service;

import com.travelcreation.mbti.domain.location.repository;
import com.travelcreation.mbti.domain.location.model.request;
import com.travelcreation.mbti.domain.location.model.response;
import com.travelcreation.mbti.domain.location.entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public List<LocationResponseDto> getAllLocations(LocationRequestDto requestDto, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Location> locationPage;

        if (requestDto.getKeyword() != null && !requestDto.getKeyword().isEmpty()) {
            if (requestDto.getAreaCode() != null && requestDto.getSigunguCode() != null) {
                locationPage = locationRepository.findByTitleContainingIgnoreCaseAndAreaCodeAndSigunguCode(
                        requestDto.getKeyword(), requestDto.getAreaCode(), requestDto.getSigunguCode(), pageable);
            } else {
                locationPage = locationRepository.findByTitleContainingIgnoreCase(requestDto.getKeyword(), pageable);
            }
        } else {
            locationPage = locationRepository.findAll(pageable);
        }

        // 정렬 로직 (필요에 따라 추가)
        if (requestDto.getArrange() != null) {
            switch (requestDto.getArrange()) {
                case "A": // 추천순
                    // 추천순 정렬 로직
                    break;
                case "B": // 인기순
                    // 인기순 정렬 로직
                    break;
                case "C": // 거리순
                    // 거리순 정렬 로직
                    break;
            }
        }

        // Location 엔티티를 LocationResponseDto로 변환
        return locationPage.stream().map(location -> {
            LocationResponseDto responseDto = new LocationResponseDto();
            responseDto.setId(location.getId());
            responseDto.setFirstImage(location.getFirstImage());
            responseDto.setFirstImage2(location.getFirstImage2());
            responseDto.setMapX(location.getMapX());
            responseDto.setMapY(location.getMapY());
            responseDto.setAddr2(location.getAddr2());
            responseDto.setAreaCode(location.getAreaCode());
            responseDto.setModifiedTime(location.getModifiedTime());
            responseDto.setCpyrhtDivCd(location.getCpyrhtDivCd());
            responseDto.setBookTour(location.getBookTour());
            responseDto.setCat1(location.getCat1());
            responseDto.setSigunguCode(location.getSigunguCode());
            responseDto.setTel(location.getTel());
            responseDto.setTitle(location.getTitle());
            responseDto.setAddr1(location.getAddr1());
            responseDto.setCat2(location.getCat2());
            responseDto.setCat3(location.getCat3());
            responseDto.setContentId(location.getContentId());
            responseDto.setContentTypeId(location.getContentTypeId());
            responseDto.setCreatedTime(location.getCreatedTime());
            responseDto.setZipcode(location.getZipcode());
            return responseDto;
        }).collect(Collectors.toList());
    }
}