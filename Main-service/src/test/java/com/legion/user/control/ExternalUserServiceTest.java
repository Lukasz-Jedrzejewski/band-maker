package com.legion.user.control;

import com.legion.externalMicroservices.crm.control.CrmClient;
import com.legion.externalMicroservices.crm.identityObjects.*;
import com.legion.user.model.PasswordResetRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

    @Test
    void should_save_or_update_band_data() {
        // given
        Object obj = mock(Object.class);
        UUID id = UUID.randomUUID();
        BandData data = mock(BandData.class);
        User user = new User(id, "", UserType.BAND.toString(), "", Instant.now(), Instant.now(), false);
        Mockito.when(crmClient.getById(Mockito.any())).thenReturn(ResponseEntity.ok(user));
        Mockito.when(crmClient.saveBandData(Mockito.any(), Mockito.any())).thenReturn(ResponseEntity.ok(data));

        // when
        ResponseEntity<?> result = userService.saveUserData(id, obj);

        // then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(data, result.getBody());
        verify(crmClient, times(1)).getById(any());
        verify(crmClient, times(1)).saveBandData(any(), any());
    }

    @Test
    void should_save_or_update_institution_data() {
        // given
        Object obj = mock(Object.class);
        UUID id = UUID.randomUUID();
        InstitutionData data = mock(InstitutionData.class);
        User user = new User(id, "", UserType.LOCAL.toString(), "", Instant.now(), Instant.now(), false);
        Mockito.when(crmClient.getById(Mockito.any())).thenReturn(ResponseEntity.ok(user));
        Mockito.when(crmClient.saveInstitutionData(Mockito.any(), Mockito.any())).thenReturn(ResponseEntity.ok(data));

        // when
        ResponseEntity<?> result = userService.saveUserData(id, obj);

        // then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(data, result.getBody());
        verify(crmClient, times(1)).getById(any());
        verify(crmClient, times(1)).saveInstitutionData(any(), any());
    }

    @Test
    void should_save_or_update_personal_data() {
        // given
        Object obj = mock(Object.class);
        UUID id = UUID.randomUUID();
        PersonalData data = mock(PersonalData.class);
        User user = new User(id, "", UserType.MUSICIAN.toString(), "", Instant.now(), Instant.now(), false);
        Mockito.when(crmClient.getById(Mockito.any())).thenReturn(ResponseEntity.ok(user));
        Mockito.when(crmClient.savePersonalData(Mockito.any(), Mockito.any())).thenReturn(ResponseEntity.ok(data));

        // when
        ResponseEntity<?> result = userService.saveUserData(id, obj);

        // then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(data, result.getBody());
        verify(crmClient, times(1)).getById(any());
        verify(crmClient, times(1)).savePersonalData(any(), any());
    }
}