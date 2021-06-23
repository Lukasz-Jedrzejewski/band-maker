package com.legion.user.control;

import com.legion.externalMicroservices.crm.control.CrmClient;
import com.legion.externalMicroservices.crm.identityObjects.RegisterRequest;
import com.legion.externalMicroservices.crm.identityObjects.User;
import com.legion.externalMicroservices.crm.identityObjects.UserType;
import com.legion.user.model.PasswordResetRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ExternalUserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final CrmClient crmClient;

    public ResponseEntity<User> register(RegisterRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        return crmClient.register(request);
    }

    public boolean existsByEmail(String email) {
        ResponseEntity<Boolean> result = crmClient.existsByEmail(email);
        return Boolean.parseBoolean(String.valueOf(result.getBody()));
    }

    public ResponseEntity<?> changePassword(PasswordResetRequest request, UUID id) {
        if (request.getNewPassword().equals(request.getConfirmPassword())) {
            return crmClient.setPassword(request.getNewPassword(), id);
        } else {
            return new ResponseEntity<String>("The passwords don`t match", HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<?> saveUserData(UUID id, Object object) {
        User user = crmClient.getById(id).getBody();
        assert user != null;
        UserType type = UserType.getByName(user.getUserType());
        ResponseEntity<?> result = null;
        switch (type) {
            case BAND:
                result = crmClient.saveBandData(user.getId(), object);
                break;
            case LOCAL:
                result = crmClient.saveInstitutionData(user.getId(), object);
                break;
            case MUSICIAN:
                 result = crmClient.savePersonalData(user.getId(), object);
                break;
            default:
                break;
        }
        return result;
    }
}
