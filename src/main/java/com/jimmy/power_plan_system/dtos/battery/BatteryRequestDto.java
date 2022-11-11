package com.jimmy.power_plan_system.dtos.battery;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BatteryRequestDto {
    @Schema(example = "nameA")
    @NotEmpty
    @Size(min = 3, message = "name length must bigger than 3 characters")
    private String name;

    @NotNull
    @Range(min = 0, message = "postCode must bigger than 0")
    @Schema(example = "10000")
    private Integer postCode;

    @NotNull
    @Range(min = 0, message = "wattCapacity must bigger than 0")
    @Schema(example = "5000")
    private Integer wattCapacity;
}
