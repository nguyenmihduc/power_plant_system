package com.jimmy.power_plan_system.services.impl;

import com.jimmy.power_plan_system.dtos.battery.BatteriesRequest;
import com.jimmy.power_plan_system.dtos.battery.BatteriesResponse;
import com.jimmy.power_plan_system.dtos.battery.BatteryResponseDto;
import com.jimmy.power_plan_system.model.battery.Battery;
import com.jimmy.power_plan_system.repositories.battery.BatteryRepository;
import com.jimmy.power_plan_system.services.BatteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BatteryServiceImpl implements BatteryService {
    @Autowired
    private BatteryRepository batteryRepository;

    @Override
    public BatteriesResponse createBatteries(BatteriesRequest batteriesRequest) {
        // Map DTO to model
        List<Battery> modelList = batteriesRequest.getBatteries().stream()
                .map(m -> Battery.builder()
                        .name(m.getName())
                        .postCode(m.getPostCode())
                        .wattCapacity(m.getWattCapacity())
                        .build())
                .collect(Collectors.toList());

        //Save all batteries
        List<Battery> resultModels = batteryRepository.saveAll(modelList);

        List<BatteryResponseDto> batteriesDto = resultModels.stream()
                .map(m -> BatteryResponseDto.builder()
                        .id(m.getId())
                        .name(m.getName())
                        .postCode(m.getPostCode())
                        .wattCapacity(m.getWattCapacity())
                        .build())
                .collect(Collectors.toList());

        return BatteriesResponse.builder().batteries(batteriesDto).build();
    }

    @Override
    public BatteriesResponse findBatteriesByPostcode(Integer from, Integer to) {
        from = from == null ? 0 : from;
        to = to == null ? Integer.MAX_VALUE : to;

        // Find batteries by Postcode range and sorted name by alphabetically
        List<Battery> resultModel = batteryRepository.findBatteriesByPostCodeBetween(from, to);

        List<BatteryResponseDto> response = resultModel.stream().map(
                        m -> BatteryResponseDto.builder()
                                .id(m.getId())
                                .name(m.getName())
                                .postCode(m.getPostCode())
                                .wattCapacity(m.getWattCapacity())
                                .build())
                .sorted(Comparator.comparing(BatteryResponseDto::getName))
                .collect(Collectors.toList());

        //Calculate average watt capacity
        Double averageWattCapacity = response.stream()
                .mapToDouble(BatteryResponseDto::getWattCapacity)
                .average()
                .orElse(0d);


        return BatteriesResponse.builder()
                .batteries(response)
                .totalBatteries(response.size())
                .averageWattCapacity(averageWattCapacity)
                .build();
    }
}
