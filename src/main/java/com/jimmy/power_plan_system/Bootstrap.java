package com.jimmy.power_plan_system;

import com.jimmy.power_plan_system.model.Role;
import com.jimmy.power_plan_system.model.enums.RoleEnum;
import com.jimmy.power_plan_system.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Bootstrap implements CommandLineRunner {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        Optional<Role> role = roleRepository.findByName(RoleEnum.ROLE_USER);

        if (role.isEmpty()) {
            System.out.println("-----SEEDING ROLE-----");

            Role roleUser = Role.builder().name(RoleEnum.ROLE_USER).build();
            Role roleModerator = Role.builder().name(RoleEnum.ROLE_MODERATOR).build();
            Role roleAdmin = Role.builder().name(RoleEnum.ROLE_ADMIN).build();

            roleRepository.save(roleUser);
            roleRepository.save(roleModerator);
            roleRepository.save(roleAdmin);
        }

        System.out.println("-----Application Started-----");
    }
}
