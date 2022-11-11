package com.jimmy.power_plan_system.services;

import com.jimmy.power_plan_system.dtos.LoginRequest;
import com.jimmy.power_plan_system.dtos.SignupRequest;

public interface AuthService {
    Object signup(SignupRequest signUpRequest);

    Object signIn(LoginRequest loginRequest);
}
