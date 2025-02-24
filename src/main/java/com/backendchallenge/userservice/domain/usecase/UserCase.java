package com.backendchallenge.userservice.domain.usecase;

import com.backendchallenge.userservice.domain.api.IRoleServicePort;
import com.backendchallenge.userservice.domain.api.IUserServicePort;
import com.backendchallenge.userservice.domain.exception.userexceptions.*;
import com.backendchallenge.userservice.domain.model.Role;
import com.backendchallenge.userservice.domain.model.User;
import com.backendchallenge.userservice.domain.spi.IEncoderPersistencePort;
import com.backendchallenge.userservice.domain.spi.IRestaurantPersistentPort;
import com.backendchallenge.userservice.domain.spi.IUserPersistencePort;
import com.backendchallenge.userservice.domain.until.ConstRole;
import com.backendchallenge.userservice.domain.until.ConstValidation;

import java.time.LocalDate;

public class UserCase implements IUserServicePort {
    private final IRoleServicePort roleServicePort;
    private final IUserPersistencePort userPersistencePort;
    private final IEncoderPersistencePort encoderPersistencePort;
    private final IRestaurantPersistentPort restaurantPersistentPort;

    public UserCase(IRoleServicePort roleServicePort,
                    IUserPersistencePort userPersistencePort,
                    IEncoderPersistencePort encoderPersistencePort,
                    IRestaurantPersistentPort restaurantPersistentPort) {
        this.roleServicePort = roleServicePort;
        this.userPersistencePort = userPersistencePort;
        this.encoderPersistencePort = encoderPersistencePort;
        this.restaurantPersistentPort = restaurantPersistentPort;
    }

    void saveUserWithRole(User user, Role role) {
        userPersistencePort.saveUserWithRole(user, role);
    }

    @Override
    public void createEmployee(User user,Long restaurantId) {
        validateOfUserParams(user);
        Role role = roleServicePort.getRoleByName(ConstRole.EMPLOYEE);
        user.setPassword(encoderPersistencePort.encode(user.getPassword()));
        if(userPersistencePort.existsUserIdByEmail(user.getEmail())){
            throw new UserAlreadyExistsException();
        }
        saveUserWithRole(user, role);
        restaurantPersistentPort.createEmployee(userPersistencePort.findUserIdByEmail(user.getEmail()), restaurantId);
    }

    @Override
    public void createOwner(User user) {
        validateOfUserParams(user);
        Role role = roleServicePort.getRoleByName(ConstRole.OWNER);
        user.setPassword(encoderPersistencePort.encode(user.getPassword()));
        saveUserWithRole(user, role);
    }

    @Override
    public Boolean findOwnerById(Long ownerId) {
        Role role = roleServicePort.getRoleByName(ConstRole.OWNER);
        return userPersistencePort.existsUserWithRole(ownerId, role);
    }

    @Override
    public void createClient(User user) {
        validateOfUserParams(user);
        Role role = roleServicePort.getRoleByName(ConstRole.CLIENT);
        user.setPassword(encoderPersistencePort.encode(user.getPassword()));
        saveUserWithRole(user, role);
    }

    @Override
    public String getPhoneById(Long userId) {
        if (!userPersistencePort.existsUserId(userId)) {
            throw new UserNotFoundException();
        }
        return userPersistencePort.getPhone(userId);
    }

    private static void userOlderThatValidAge(LocalDate birthdate) {
        if (birthdate.isAfter(LocalDate.now().minusYears(ConstValidation.MIN_AGE))) {
            throw new UserOlderThatTheValidAgeException();
        }
    }

    private static void emailFormatValidation(String email) {
        if (!email.matches(ConstValidation.EMAIL_REGEX)) {
            throw new InvalidUserEmailFormatException();
        }
    }

    private static void documentFormatValidation(String document) {
        if (!document.matches(ConstValidation.DOCUMENT_REGEX)) {
            throw new InvalidUserDocumentFormatException();
        }
    }

    private static void phoneFormatValidation(String phone) {
        if (!phone.matches(ConstValidation.PHONE_REGEX)) {
            throw new InvalidUserPhoneFormatException();
        }
    }

    private static void validateOfUserParams(User user) {
        if (user == null || user.getName().isBlank()) {
            throw new EmptyUserNameException();
        }
        if (user.getLastName() == null || user.getLastName().isBlank()) {
            throw new EmptyUserLastNameException();
        }
        if (user.getDocument() == null || user.getDocument().isBlank()) {
            throw new EmptyUserDocumentException();
        }
        if (user.getPhone() == null || user.getPhone().isBlank()) {
            throw new EmptyUserPhoneException();
        }
        if (user.getBirthdate() == null) {
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
