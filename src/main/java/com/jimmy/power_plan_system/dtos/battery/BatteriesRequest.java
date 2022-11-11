package com.jimmy.power_plan_system.dtos.battery;

import lombok.*;

import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BatteriesRequest {
    @Valid
    List<BatteryRequestDto> batteries;
}
