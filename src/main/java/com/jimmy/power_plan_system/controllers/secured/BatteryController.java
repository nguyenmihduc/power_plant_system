package com.jimmy.power_plan_system.controllers.secured;

import com.jimmy.power_plan_system.dtos.battery.BatteriesRequest;
import com.jimmy.power_plan_system.dtos.battery.BatteriesResponse;
import com.jimmy.power_plan_system.services.BatteryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/batteries")
@SecurityRequirement(name = "bearerAuth")
public class BatteryController {
    @Autowired
    private BatteryService batteryService;

    @PostMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<BatteriesResponse> createBatteries(@Valid @RequestBody BatteriesRequest request) {
        return ResponseEntity.ok(batteryService.createBatteries(request));
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<BatteriesResponse> findBatteryByPostcode(
            @RequestParam(name = "postCodeFrom", required = false) Integer from,
            @RequestParam(name = "postCodeTo", required = false) Integer to) {
        return ResponseEntity.ok(batteryService.findBatteriesByPostcode(from, to));
    }
}
