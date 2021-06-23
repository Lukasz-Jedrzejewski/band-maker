package com.legion.user.control;

import com.legion.externalMicroservices.crm.control.CrmClient;
import com.legion.externalMicroservices.crm.identityObjects.User;
import com.legion.user.model.PasswordResetRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
class ExternalUserServiceTest {

    private CrmClient crmClient;
    private BCryptPasswordEncoder passwordEncoder;
    private ExternalUserService userService;

    @BeforeEach
    void setUp() {
        crmClient = mock(CrmClient.class);
        passwordEncoder = mock(BCryptPasswordEncoder.class);
        userService = new ExternalUserService(passwordEncoder, crmClient);
    }

    @Test
    void should_check_if_given_email_already_exist_and_return_false() {
        // given
        String email = "test@gmail.com";
        Mockito.when(crmClient.existsByEmail(email)).thenReturn(ResponseEntity.ok(false));

        // when
        boolean result = userService.existsByEmail(email);

        // then
        assertFalse(result);
    }

    @Test
    void should_check_if_given_email_already_exist_and_return_true() {
        // given
        String email = "test@gmail.com";
        Mockito.when(crmClient.existsByEmail(email)).thenReturn(ResponseEntity.ok(true));

        // when
        boolean result = userService.existsByEmail(email);

        // then
        assertTrue(result);
    }

    @Test
    void should_return_exception_message() {
        // given
        PasswordResetRequest request = new PasswordResetRequest("aaaaAAAA12@", "aaaAAA12@");

        // when
        ResponseEntity<?> result = userService.changePassword(request, UUID.randomUUID());

        // then
        assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
        assertEquals("The passwords don`t match", result.getBody());
    }

    @Test
    void should_set_password_for_given_user_and_return_him() {
        // given
        PasswordResetRequest request = new PasswordResetRequest("aaaaAAAA12@", "aaaaAAAA12@");
        User user = mock(User.class);
        Mockito.when(crmClient.setPassword(Mockito.any(), Mockito.any())).thenReturn(ResponseEntity.ok(user));

        // when
        ResponseEntity<?> result = userService.changePassword(request, UUID.randomUUID());

        // then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(user, result.getBody());
    }
}