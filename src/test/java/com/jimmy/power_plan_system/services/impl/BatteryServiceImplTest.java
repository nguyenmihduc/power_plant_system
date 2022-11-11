package com.jimmy.power_plan_system.services.impl;

import com.jimmy.power_plan_system.dtos.battery.BatteriesRequest;
import com.jimmy.power_plan_system.dtos.battery.BatteriesResponse;
import com.jimmy.power_plan_system.dtos.battery.BatteryRequestDto;
import com.jimmy.power_plan_system.model.battery.Battery;
import com.jimmy.power_plan_system.repositories.battery.BatteryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class BatteryServiceImplTest {
    @Mock
    private BatteryRepository batteryRepository;

    @InjectMocks
    private BatteryServiceImpl batteryService;

    @Test
    void testCreateBatteries() {
        //Given

        List<BatteryRequestDto> batteryRequestDtoList = new ArrayList<>();
        batteryRequestDtoList.add(BatteryRequestDto.builder()
                .name("nameA")
                .postCode(10000)
                .wattCapacity(5000)
                .build());

        BatteriesRequest batteriesRequest = BatteriesRequest.builder()
                .batteries(batteryRequestDtoList)
                .build();


        List<Battery> batteriesModel = new ArrayList<>();
        batteriesModel.add(Battery.builder()
                        .id(1)
                        .name("nameA")
                        .postCode(10000)
                        .wattCapacity(5000)
                .build());

        Mockito.when(batteryRepository.saveAll(any())).thenReturn(batteriesModel);

        //Action
        BatteriesResponse res = batteryService.createBatteries(batteriesRequest);

        //Expected
        Integer size = 1;

        //Result
        Assertions.assertEquals(res.getBatteries().size(), size);

    }

    @Test
    void testFindBatteriesByPostcode() {
        //Given
        Integer from = 8000;
        Integer to = 15000;

        List<Battery> batteriesModel = new ArrayList<>();
        batteriesModel.add(Battery.builder()
                .id(1)
                .name("nameA")
                .postCode(10000)
                .wattCapacity(5000)
                .build());
        Mockito.when(batteryRepository.findBatteriesByPostCodeBetween(any(), any())).thenReturn(batteriesModel);

        //Action
        BatteriesResponse res = batteryService.findBatteriesByPostcode(from, to);

        //Expected
        Integer total = 1;

        //Result
        Assertions.assertEquals(res.getTotalBatteries(), total);

    }
}