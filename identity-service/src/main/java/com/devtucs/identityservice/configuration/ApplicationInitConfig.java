package com.devtucs.identityservice.configuration;

import com.devtucs.identityservice.entity.User;
import com.devtucs.identityservice.enums.Role;
import com.devtucs.identityservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Configuration
public class ApplicationInitConfig {

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                Set<com.devtucs.identityservice.entity.Role> roles = new HashSet<>();
                com.devtucs.identityservice.entity.Role role = new com.devtucs.identityservice.entity.Role();
                role.setName(Role.ADMIN.name());
                roles.add(role);

                PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

                User user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("12345678"))
//                        .roles(roles)
                        .build();

                userRepository.save(user);
                log.warn("user role admin has been auto generated with password: 12345678. Please reset password.");
            }
        };
    }
}
