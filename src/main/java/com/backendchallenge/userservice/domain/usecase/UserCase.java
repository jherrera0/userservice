package com.backendchallenge.userservice.domain.usecase;

import com.backendchallenge.userservice.domain.api.IRoleServicePort;
import com.backendchallenge.userservice.domain.api.IUserServicePort;
import com.backendchallenge.userservice.domain.exception.*;
import com.backendchallenge.userservice.domain.model.Role;
import com.backendchallenge.userservice.domain.model.User;
import com.backendchallenge.userservice.domain.spi.IEncoderPersistencePort;
import com.backendchallenge.userservice.domain.spi.IUserPersistencePort;
import com.backendchallenge.userservice.domain.until.ConstRole;
import com.backendchallenge.userservice.domain.until.ConstValidation;

import java.time.LocalDate;

public class UserCase implements IUserServicePort {
    private final IRoleServicePort roleServicePort;
    private final IUserPersistencePort userPersistencePort;
    private final IEncoderPersistencePort encoderPersistencePort;

    public UserCase(IRoleServicePort roleServicePort, IUserPersistencePort userPersistencePort, IEncoderPersistencePort encoderPersistencePort) {
        this.roleServicePort = roleServicePort;
        this.userPersistencePort = userPersistencePort;
        this.encoderPersistencePort = encoderPersistencePort;
    }

    void saveUserWithRole(User user, Role role) {
        userPersistencePort.saveUserWithRole(user, role);
    }
    @Override
    public void createOwner(User user) {
        validateOfUserParams(user);
        Role role = roleServicePort.getRoleByName(ConstRole.OWNER);
        user.setPassword(encoderPersistencePort.encode(user.getPassword()));
        saveUserWithRole(user, role);
    }

    private static void userOlderThatValidAge(LocalDate birthdate) {
        if(birthdate.isAfter(LocalDate.now().minusYears(ConstValidation.MIN_AGE))){
            throw new UserOlderThatTheValidAgeException();
        }
    }
    private static void emailFormatValidation(String email) {
        if(email.matches(ConstValidation.EMAIL_REGEX)){
            throw new InvalidUserEmailException();
        }
    }

    private static void documentFormatValidation(String document) {
        if(document.matches(ConstValidation.DOCUMENT_REGEX)){
            throw new InvalidUserDocumentFormatException();
        }
    }

    private static void phoneFormatValidation(String phone) {
        if(phone.matches(ConstValidation.PHONE_REGEX)){
            throw new InvalidUserPhoneFormatException();
        }
    }

    private static void validateOfUserParams(User user) {
        if(user == null|| user.getName().isBlank()){
            throw new EmptyUserNameException();
        }
        if (user.getLastName() == null || user.getLastName().isBlank()) {
            throw new EmptyUserLastNameException();
        }
        if (user.getDocument()== null || user.getDocument().isBlank()) {
            throw new EmptyUserDocumentException();
        }
        if(user.getPhone()==null || user.getPhone().isBlank()){
            throw new EmptyUSerPhoneException();
        }
        if(user.getBirthdate() == null){
            throw new EmptyUserBirthdateException();
        }
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new EmptyUserEmailException();
        }
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            throw new EmptyUserPasswordException();
        }
        emailFormatValidation(user.getEmail());
        phoneFormatValidation(user.getPhone());
        documentFormatValidation(user.getDocument());
        userOlderThatValidAge(user.getBirthdate());
    }
}
