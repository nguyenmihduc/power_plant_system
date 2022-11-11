package com.jimmy.power_plan_system.dtos.battery;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BatteryResponseDto {
    private Integer id;
    private String name;
    private Integer postCode;
    private Integer wattCapacity;
}
