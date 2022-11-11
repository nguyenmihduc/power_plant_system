package com.jimmy.power_plan_system.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class LoginRequest {
    @NotEmpty
    @Size(min = 3, max = 20, message = "username must be between 3 and 20 characters")
    @Schema(example = "admin")
    private String username;

    @NotEmpty
    @Size(min = 6, max = 40, message = "password must be between 6 and 40 characters")
    @Schema(example = "123456")
    private String password;
}
