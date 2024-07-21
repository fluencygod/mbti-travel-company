package com.travelcreation.mbti.domain.location.controller;

import com.travelcreation.mbti.domain.location.model.request.LocationRequestDto;
import com.travelcreation.mbti.domain.location.model.response.LocationResponseDto;
import com.travelcreation.mbti.domain.location.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/api/locations")
    public List<LocationResponseDto> getLocations(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String areaCode,
            @RequestParam(required = false) String sigunguCode,
            @RequestParam(required = false) String arrange,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String mbti
    ) {
        LocationRequestDto requestDto = new LocationRequestDto();
        requestDto.setKeyword(keyword);
        requestDto.setAreaCode(areaCode);
        requestDto.setSigunguCode(sigunguCode);
        requestDto.setArrange(arrange);
        requestDto.setUserId(userId);
        requestDto.setMbti(mbti);

        return locationService.getAllLocations(requestDto, page, size);
    }
}