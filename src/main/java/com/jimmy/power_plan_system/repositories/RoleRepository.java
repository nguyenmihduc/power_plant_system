package com.jimmy.power_plan_system.repositories;

import com.jimmy.power_plan_system.model.Role;
import com.jimmy.power_plan_system.model.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(RoleEnum name);
}