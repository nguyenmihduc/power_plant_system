package com.jimmy.power_plan_system.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupRequest {
    @NotEmpty
    @Size(min = 3, max = 20, message = "username must be between 3 and 20 characters")
    @Schema(example = "admin")
    private String username;

    @NotEmpty
    @Email(message = "email must be valid")
    @Schema(example = "admin@1.com")
    private String email;

    @Schema(example = "[\"ROLE_ADMIN\"]")
    private Set<String> role;

    @NotEmpty
    @Size(min = 6, max = 40, message = "password must be between 6 and 40 characters")
    @Schema(example = "123456")
    private String password;
}
