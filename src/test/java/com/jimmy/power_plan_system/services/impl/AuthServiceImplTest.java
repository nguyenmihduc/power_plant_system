package com.jimmy.power_plan_system.services.impl;

import com.jimmy.power_plan_system.dtos.MessageResponse;
import com.jimmy.power_plan_system.dtos.SignupRequest;
import com.jimmy.power_plan_system.model.Role;
import com.jimmy.power_plan_system.model.enums.RoleEnum;
import com.jimmy.power_plan_system.repositories.RoleRepository;
import com.jimmy.power_plan_system.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private AuthServiceImpl authService;


    @Test
    public void testUsernameWasExist() {
        //Given
        Mockito.when(userRepository.existsByUsername(any())).thenReturn(true);

        //Action
        ResponseEntity<?> result = (ResponseEntity<?>) authService.signup(new SignupRequest());
        MessageResponse resultBody = (MessageResponse) result.getBody();

        //Expected
        MessageResponse expected = MessageResponse.builder()
                .message("Error: Username is already taken!")
                .build();

        //Result
        Assertions.assertEquals(resultBody.getMessage(), expected.getMessage());

    }

    @Test
    public void testEmailWasExist() {
        //Given
        Mockito.when(userRepository.existsByUsername(any())).thenReturn(false);
        Mockito.when(userRepository.existsByEmail(any())).thenReturn(true);

        //Action
        ResponseEntity<?> result = (ResponseEntity<?>) authService.signup(new SignupRequest());
        MessageResponse resultBody = (MessageResponse) result.getBody();

        //Expected
        MessageResponse expected = MessageResponse.builder()
                .message("Error: Email is already in use!")
                .build();

        //Result
        Assertions.assertEquals(resultBody.getMessage(), expected.getMessage());
    }

    @Test
    public void testRoleUserNotFound() {
        //Given
        Mockito.when(userRepository.existsByUsername(any())).thenReturn(false);
        Mockito.when(userRepository.existsByEmail(any())).thenReturn(false);

        SignupRequest signupRequest = SignupRequest.builder()
                .username("minh")
                .email("minh@gmail.com")
                .role(null)
                .password("123456")
                .build();

        Mockito.when(encoder.encode(any())).thenReturn("abcdef");

        Mockito.when(roleRepository.findByName(any())).thenReturn( Optional.empty());

        //Action

        RuntimeException e = assertThrows(RuntimeException.class,
                () ->  authService.signup(signupRequest));

        //Result
        Assertions.assertEquals(e.getMessage(), "Error: Role is not found.");
    }

    @Test
    public void testRoleUserFound() {
        //Given
        Mockito.when(userRepository.existsByUsername(any())).thenReturn(false);
        Mockito.when(userRepository.existsByEmail(any())).thenReturn(false);

        SignupRequest signupRequest = SignupRequest.builder()
                .username("minh")
                .email("minh@gmail.com")
                .role(null)
                .password("123456")
                .build();

        Mockito.when(encoder.encode(any())).thenReturn("abcdef");

        Optional<Role> role = Optional.of(Role.builder()
                .name(RoleEnum.ROLE_USER)
                .build());

        Mockito.when(roleRepository.findByName(any())).thenReturn(role);

        Mockito.when(userRepository.save(any())).thenReturn(null);

        //Action
        ResponseEntity<?> result = (ResponseEntity<?>) authService.signup(signupRequest);
        MessageResponse resultBody = (MessageResponse) result.getBody();

        //Expected
        MessageResponse expected = MessageResponse.builder()
                .message("User registered successfully!")
                .build();

        //Result
        Assertions.assertEquals(resultBody.getMessage(), expected.getMessage());

    }

    @Test
    public void testRoleAdminNotFound() {
        //Given
        Mockito.when(userRepository.existsByUsername(any())).thenReturn(false);
        Mockito.when(userRepository.existsByEmail(any())).thenReturn(false);

        Set<String> set = new HashSet<>();
        set.add("admin");
        SignupRequest signupRequest = SignupRequest.builder()
                .username("minh")
                .email("minh@gmail.com")
                .role(set)
                .password("123456")
                .build();

        Mockito.when(encoder.encode(any())).thenReturn("abcdef");

        Mockito.when(roleRepository.findByName(any())).thenReturn(Optional.empty());

        //Action
        RuntimeException e = assertThrows(RuntimeException.class,
                () ->  authService.signup(signupRequest));

        //Result
        Assertions.assertEquals(e.getMessage(), "Error: Role is not found.");
    }

    @Test
    public void testRoleAdminFound() {
        //Given
        Mockito.when(userRepository.existsByUsername(any())).thenReturn(false);
        Mockito.when(userRepository.existsByEmail(any())).thenReturn(false);

        Set<String> set = new HashSet<>();
        set.add("admin");
        SignupRequest signupRequest = SignupRequest.builder()
                .username("minh")
                .email("minh@gmail.com")
                .role(set)
                .password("123456")
                .build();

        Mockito.when(encoder.encode(any())).thenReturn("abcdef");

        Optional<Role> role = Optional.of(Role.builder()
                        .name(RoleEnum.ROLE_ADMIN)
                .build());

        Mockito.when(roleRepository.findByName(any())).thenReturn(role);

        //Action
        ResponseEntity<?> result = (ResponseEntity<?>) authService.signup(signupRequest);
        MessageResponse resultBody = (MessageResponse) result.getBody();

        //Expected
        MessageResponse expected = MessageResponse.builder()
                .message("User registered successfully!")
                .build();

        //Result
        Assertions.assertEquals(resultBody.getMessage(), expected.getMessage());

    }
    @Test
    public void testRoleModNotFound() {
        //Given
        Mockito.when(userRepository.existsByUsername(any())).thenReturn(false);
        Mockito.when(userRepository.existsByEmail(any())).thenReturn(false);

        Set<String> set = new HashSet<>();
        set.add("admin");
        set.add("mod");
        SignupRequest signupRequest = SignupRequest.builder()
                .username("minh")
                .email("minh@gmail.com")
                .role(set)
                .password("123456")
                .build();

        Mockito.when(encoder.encode(any())).thenReturn("abcdef");

        Optional<Role> role = Optional.of(Role.builder()
                        .name(RoleEnum.ROLE_ADMIN)
                .build());

        Mockito.when(roleRepository.findByName(any())).thenReturn(role);

        Mockito.when(roleRepository.findByName(any())).thenReturn(Optional.empty());

        //Action
        RuntimeException e = assertThrows(RuntimeException.class,
                () ->  authService.signup(signupRequest));

        //Result
        Assertions.assertEquals(e.getMessage(), "Error: Role is not found.");
    }

    @Test
    public void testRoleModFound() {
        //Given
        Mockito.when(userRepository.existsByUsername(any())).thenReturn(false);
        Mockito.when(userRepository.existsByEmail(any())).thenReturn(false);

        Set<String> set = new HashSet<>();
        set.add("admin");
        set.add("mod");
        SignupRequest signupRequest = SignupRequest.builder()
                .username("minh")
                .email("minh@gmail.com")
                .role(set)
                .password("123456")
                .build();

        Mockito.when(encoder.encode(any())).thenReturn("abcdef");

        Optional<Role> role_admin = Optional.of(Role.builder()
                .name(RoleEnum.ROLE_ADMIN)
                .build());

        Mockito.when(roleRepository.findByName(any())).thenReturn(role_admin);

        Optional<Role> role_mod = Optional.of(Role.builder()
                .name(RoleEnum.ROLE_MODERATOR)
                .build());

        Mockito.when(roleRepository.findByName(any())).thenReturn(role_mod);

        //Action
        ResponseEntity<?> result = (ResponseEntity<?>) authService.signup(signupRequest);
        MessageResponse resultBody = (MessageResponse) result.getBody();

        //Expected
        MessageResponse expected = MessageResponse.builder()
                .message("User registered successfully!")
                .build();

        //Result
        Assertions.assertEquals(resultBody.getMessage(), expected.getMessage());
    }

    @Test
    public void testSwitchDefaultRoleNotFound() {
        //Given
        Mockito.when(userRepository.existsByUsername(any())).thenReturn(false);
        Mockito.when(userRepository.existsByEmail(any())).thenReturn(false);

        Set<String> set = new HashSet<>();
        set.add("customer");
        SignupRequest signupRequest = SignupRequest.builder()
                .username("minh")
                .email("minh@gmail.com")
                .role(set)
                .password("123456")
                .build();

        Mockito.when(encoder.encode(any())).thenReturn("abcdef");
        Mockito.when(roleRepository.findByName(any())).thenReturn(Optional.empty());

        //Action
        RuntimeException e = assertThrows(RuntimeException.class,
                () ->  authService.signup(signupRequest));

        //Result
        Assertions.assertEquals(e.getMessage(), "Error: Role is not found.");
    }

    @Test
    public void testSwitchDefaultRoleFound() {
        //Given
        Mockito.when(userRepository.existsByUsername(any())).thenReturn(false);
        Mockito.when(userRepository.existsByEmail(any())).thenReturn(false);

        Set<String> set = new HashSet<>();
        set.add("customer");
        SignupRequest signupRequest = SignupRequest.builder()
                .username("minh")
                .email("minh@gmail.com")
                .role(set)
                .password("123456")
                .build();

        Mockito.when(encoder.encode(any())).thenReturn("abcdef");

        Optional<Role> role_user = Optional.of(Role.builder()
                        .name(RoleEnum.ROLE_USER)
                .build());
        Mockito.when(roleRepository.findByName(any())).thenReturn(role_user);

        //Action
        ResponseEntity<?> result = (ResponseEntity<?>) authService.signup(signupRequest);
        MessageResponse resultBody = (MessageResponse) result.getBody();

        //Expected
        MessageResponse expected = MessageResponse.builder()
                .message("User registered successfully!")
                .build();

        //Result
        Assertions.assertEquals(resultBody.getMessage(), expected.getMessage());
    }
    @Test
    void signup() {
    }

    @Test
    void signIn() {
    }
}