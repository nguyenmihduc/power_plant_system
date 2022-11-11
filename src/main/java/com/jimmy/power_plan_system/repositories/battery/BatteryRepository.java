package com.jimmy.power_plan_system.repositories.battery;

import com.jimmy.power_plan_system.model.battery.Battery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatteryRepository extends JpaRepository<Battery, Integer> {
    List<Battery> findBatteriesByPostCodeBetween(Integer from, Integer to);

}
