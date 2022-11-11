package com.jimmy.power_plan_system.dtos.battery;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BatteriesResponse {
    private List<BatteryResponseDto> batteries;
    private Integer totalBatteries;
    private Double averageWattCapacity;
}
