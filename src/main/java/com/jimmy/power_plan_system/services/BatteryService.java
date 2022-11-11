package com.jimmy.power_plan_system.services;

import com.jimmy.power_plan_system.dtos.battery.BatteriesRequest;
import com.jimmy.power_plan_system.dtos.battery.BatteriesResponse;

public interface BatteryService {
    BatteriesResponse createBatteries(BatteriesRequest batteriesRequest);
    BatteriesResponse findBatteriesByPostcode(Integer from, Integer to);
}
