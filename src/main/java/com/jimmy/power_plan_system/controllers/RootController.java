package com.jimmy.power_plan_system.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Map;

@RestController
@RequestMapping("/ping")
public class RootController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "ping")
    public Map<String, Object> index() {
        return Map.of("serverTime", ZonedDateTime.now(ZoneOffset.UTC));
    }

}