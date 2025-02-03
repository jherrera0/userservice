package com.backendchallenge.userservice.domain.usecase;

import com.backendchallenge.userservice.domain.api.IRoleServicePort;
import com.backendchallenge.userservice.domain.exception.userexceptions.*;
import com.backendchallenge.userservice.domain.model.Role;
import com.backendchallenge.userservice.domain.model.User;
import com.backendchallenge.userservice.domain.spi.IEncoderPersistencePort;
import com.backendchallenge.userservice.domain.spi.IRestaurantPersistentPort;
import com.backendchallenge.userservice.domain.spi.IUserPersistencePort;
import com.backendchallenge.userservice.domain.until.ConstRole;
import com.backendchallenge.userservice.domain.until.ConstTest;
import com.backendchallenge.userservice.domain.until.ConstValidation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserCaseTest {

    @Mock
    private IRoleServicePort roleServicePort;

    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private IEncoderPersistencePort encoderPersistencePort;

    @Mock
    private IRestaurantPersistentPort restaurantPersistentPort;

    @InjectMocks
    private UserCase userCase;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void createOwner_withValidUser_shouldCreateOwner() {
        User user = new User();
        user.setName(ConstTest.NAME_VALID);
        user.setLastName(ConstTest.LAST_NAME_VALID);
        user.setDocument(ConstTest.DOCUMENT_VALID);
        user.setPhone(ConstTest.PHONE_VALID);
        user.setBirthdate(ConstTest.BIRTHDATE_VALID);
        user.setEmail(ConstTest.EMAIL_VALID);
        user.setPassword(ConstTest.PASSWORD_VALID);

        Role role = new Role(ConstTest.ID_TEST,ConstTest.ROLE_NAME_TEST);

        when(roleServicePort.getRoleByName(ConstRole.OWNER)).thenReturn(role);
        when(encoderPersistencePort.encode(user.getPassword())).thenReturn(ConstTest.ENCODED_PASSWORD_TEST);

        userCase.createOwner(user);

        assertEquals(ConstTest.ENCODED_PASSWORD_TEST, user.getPassword());
        verify(userPersistencePort, times(1)).saveUserWithRole(user, role);
    }

    @Test
    void createOwner_withNullUser_shouldThrowException() {
        assertThrows(EmptyUserNameException.class, () -> userCase.createOwner(null));
    }

    @Test
    void createOwner_withEmptyUserName_shouldThrowException() {
        User user = new User();
        user.setName(ConstTest.EMPTY_STRING);
        user.setLastName(ConstTest.LAST_NAME_VALID);
        user.setDocument(ConstTest.DOCUMENT_VALID);
        user.setPhone(ConstTest.PHONE_VALID);
        user.setBirthdate(ConstTest.BIRTHDATE_VALID);
        user.setEmail(ConstTest.EMAIL_VALID);
        user.setPassword(ConstTest.PASSWORD_VALID);

        assertThrows(EmptyUserNameException.class, () -> userCase.createOwner(user));
    }

    @Test
    void createOwner_withInvalidEmailFormat_shouldThrowException() {
        User user = new User();
        user.setName(ConstTest.NAME_VALID);
        user.setLastName(ConstTest.LAST_NAME_VALID);
        user.setDocument(ConstTest.DOCUMENT_VALID);
        user.setPhone(ConstTest.PHONE_VALID);
        user.setBirthdate(ConstTest.BIRTHDATE_VALID);
        user.setEmail(ConstTest.INVALID_EMAIL_TEST);
        user.setPassword(ConstTest.PASSWORD_VALID);

        assertThrows(InvalidUserEmailFormatException.class, () -> userCase.createOwner(user));
    }

    @Test
    void createOwner_withInvalidPhoneFormat_shouldThrowException() {
        User user = new User();
        user.setName(ConstTest.NAME_VALID);
        user.setLastName(ConstTest.LAST_NAME_VALID);
        user.setDocument(ConstTest.DOCUMENT_VALID);
        user.setPhone(ConstTest.INVALID_PHONE_TEST);
        user.setBirthdate(ConstTest.BIRTHDATE_VALID);
        user.setEmail(ConstTest.EMAIL_VALID);
        user.setPassword(ConstTest.PASSWORD_VALID);

        assertThrows(InvalidUserPhoneFormatException.class, () -> userCase.createOwner(user));
    }

    @Test
    void createOwner_withInvalidDocumentFormat_shouldThrowException() {
        User user = new User();
        user.setName(ConstTest.NAME_VALID);
        user.setLastName(ConstTest.LAST_NAME_VALID);
        user.setDocument(ConstTest.INVALID_DOCUMENT_TEST);
        user.setPhone(ConstTest.PHONE_VALID);
        user.setBirthdate(ConstTest.BIRTHDATE_VALID);
        user.setEmail(ConstTest.EMAIL_VALID);
        user.setPassword(ConstTest.PASSWORD_VALID);

        assertThrows(InvalidUserDocumentFormatException.class, () -> userCase.createOwner(user));
    }

    @Test
    void createOwner_withEmptyLastName_shouldThrowException() {
        User user = new User();
        user.setName(ConstTest.NAME_VALID);
        user.setLastName(ConstTest.EMPTY_STRING);
        user.setDocument(ConstTest.DOCUMENT_VALID);
        user.setPhone(ConstTest.PHONE_VALID);
        user.setBirthdate(ConstTest.BIRTHDATE_VALID);
        user.setEmail(ConstTest.EMAIL_VALID);
        user.setPassword(ConstTest.PASSWORD_VALID);

        assertThrows(EmptyUserLastNameException.class, () -> userCase.createOwner(user));
    }

    @Test
    void createOwner_withBlankDocument_shouldThrowException() {
        User user = new User();
        user.setName(ConstTest.NAME_VALID);
        user.setLastName(ConstTest.LAST_NAME_VALID);
        user.setDocument(ConstTest.EMPTY_STRING);
        user.setPhone(ConstTest.PHONE_VALID);
        user.setBirthdate(ConstTest.BIRTHDATE_VALID);
        user.setEmail(ConstTest.EMAIL_VALID);
        user.setPassword(ConstTest.PASSWORD_VALID);

        assertThrows(EmptyUserDocumentException.class, () -> userCase.createOwner(user));
    }

    @Test
    void createOwner_withBlankPhone_shouldThrowException() {
        User user = new User();
        user.setName(ConstTest.NAME_VALID);
        user.setLastName(ConstTest.LAST_NAME_VALID);
        user.setDocument(ConstTest.DOCUMENT_VALID);
        user.setPhone(ConstTest.EMPTY_STRING);
        user.setBirthdate(ConstTest.BIRTHDATE_VALID);
        user.setEmail(ConstTest.EMAIL_VALID);
        user.setPassword(ConstTest.PASSWORD_VALID);

        assertThrows(EmptyUserPhoneException.class, () -> userCase.createOwner(user));
    }

    @Test
    void createOwner_withNullBirthdate_shouldThrowException() {
        User user = new User();
        user.setName(ConstTest.NAME_VALID);
        user.setLastName(ConstTest.LAST_NAME_VALID);
        user.setDocument(ConstTest.DOCUMENT_VALID);
        user.setPhone(ConstTest.PHONE_VALID);
        user.setBirthdate(null);
        user.setEmail(ConstTest.EMAIL_VALID);
        user.setPassword(ConstTest.PASSWORD_VALID);

        assertThrows(EmptyUserBirthdateException.class, () -> userCase.createOwner(user));
    }

    @Test
    void createOwner_withBlankEmail_shouldThrowException() {
        User user = new User();
        user.setName(ConstTest.NAME_VALID);
        user.setLastName(ConstTest.LAST_NAME_VALID);
        user.setDocument(ConstTest.DOCUMENT_VALID);
        user.setPhone(ConstTest.PHONE_VALID);
        user.setBirthdate(ConstTest.BIRTHDATE_VALID);
        user.setEmail(ConstTest.EMPTY_STRING);
        user.setPassword(ConstTest.PASSWORD_VALID);

        assertThrows(EmptyUserEmailException.class, () -> userCase.createOwner(user));
    }

    @Test
    void createOwner_withBlankPassword_shouldThrowException() {
        User user = new User();
        user.setName(ConstTest.NAME_VALID);
        user.setLastName(ConstTest.LAST_NAME_VALID);
        user.setDocument(ConstTest.DOCUMENT_VALID);
        user.setPhone(ConstTest.PHONE_VALID);
        user.setBirthdate(ConstTest.BIRTHDATE_VALID);
        user.setEmail(ConstTest.EMAIL_VALID);
        user.setPassword(ConstTest.EMPTY_STRING);

        assertThrows(EmptyUserPasswordException.class, () -> userCase.createOwner(user));
    }

    @Test
    void createOwner_withUserOlderThanValidAge_shouldThrowException() {
        User user = new User();
        user.setName(ConstTest.NAME_VALID);
        user.setLastName(ConstTest.LAST_NAME_VALID);
        user.setDocument(ConstTest.DOCUMENT_VALID);
        user.setPhone(ConstTest.PHONE_VALID);
        user.setBirthdate(LocalDate.now().minusYears(ConstValidation.MIN_AGE - 1));
        user.setEmail(ConstTest.EMAIL_VALID);
        user.setPassword(ConstTest.PASSWORD_VALID);

        assertThrows(UserOlderThatTheValidAgeException.class, () -> userCase.createOwner(user));
    }

    @Test
    void findOwnerById_withExistingOwner_shouldReturnTrue() {
        Long ownerId = ConstTest.ID_TEST;
        Role role = new Role(ConstTest.ID_TEST, ConstRole.OWNER);

        when(roleServicePort.getRoleByName(ConstRole.OWNER)).thenReturn(role);
        when(userPersistencePort.existsUserWithRole(ownerId, role)).thenReturn(true);

        Boolean result = userCase.findOwnerById(ownerId);

        assertTrue(result);
        verify(roleServicePort, times(1)).getRoleByName(ConstRole.OWNER);
        verify(userPersistencePort, times(1)).existsUserWithRole(ownerId, role);
    }

    @Test
    void findOwnerById_withNonExistingOwner_shouldReturnFalse() {
        Long ownerId = ConstTest.ID_TEST;
        Role role = new Role(ConstTest.ID_TEST, ConstRole.OWNER);

        when(roleServicePort.getRoleByName(ConstRole.OWNER)).thenReturn(role);
        when(userPersistencePort.existsUserWithRole(ownerId, role)).thenReturn(false);

        Boolean result = userCase.findOwnerById(ownerId);

        assertFalse(result);
        verify(roleServicePort, times(1)).getRoleByName(ConstRole.OWNER);
        verify(userPersistencePort, times(1)).existsUserWithRole(ownerId, role);
    }

    @Test
    void createEmployee_withValidUser_shouldCreateEmployee() {
        User user = new User();
        user.setName(ConstTest.NAME_VALID);
        user.setLastName(ConstTest.LAST_NAME_VALID);
        user.setDocument(ConstTest.DOCUMENT_VALID);
        user.setPhone(ConstTest.PHONE_VALID);
        user.setBirthdate(ConstTest.BIRTHDATE_VALID);
        user.setEmail(ConstTest.EMAIL_VALID);
        user.setPassword(ConstTest.PASSWORD_VALID);

        Role role = new Role(ConstTest.ID_TEST, ConstRole.EMPLOYEE);
        doNothing().when(restaurantPersistentPort).createEmployee(ConstTest.ID_TEST, ConstTest.ID_TEST);
        when(roleServicePort.getRoleByName(ConstRole.EMPLOYEE)).thenReturn(role);
        when(encoderPersistencePort.encode(user.getPassword())).thenReturn(ConstTest.ENCODED_PASSWORD_TEST);

        userCase.createEmployee(user, ConstTest.ID_TEST);

        assertEquals(ConstTest.ENCODED_PASSWORD_TEST, user.getPassword());
        verify(userPersistencePort, times(1)).saveUserWithRole(user, role);
    }

    @Test
    void createEmployee_withNullUser_shouldThrowException() {
        assertThrows(EmptyUserNameException.class, () -> userCase.createEmployee(null, ConstTest.ID_TEST));
    }

    @Test
    void createEmployee_withEmptyUserName_shouldThrowException() {
        User user = new User();
        user.setName(ConstTest.EMPTY_STRING);
        user.setLastName(ConstTest.LAST_NAME_VALID);
        user.setDocument(ConstTest.DOCUMENT_VALID);
        user.setPhone(ConstTest.PHONE_VALID);
        user.setBirthdate(ConstTest.BIRTHDATE_VALID);
        user.setEmail(ConstTest.EMAIL_VALID);
        user.setPassword(ConstTest.PASSWORD_VALID);

        assertThrows(EmptyUserNameException.class, () -> userCase.createEmployee(user, ConstTest.ID_TEST));
    }

    @Test
    void createEmployee_withInvalidEmailFormat_shouldThrowException() {
        User user = new User();
        user.setName(ConstTest.NAME_VALID);
        user.setLastName(ConstTest.LAST_NAME_VALID);
        user.setDocument(ConstTest.DOCUMENT_VALID);
        user.setPhone(ConstTest.PHONE_VALID);
        user.setBirthdate(ConstTest.BIRTHDATE_VALID);
        user.setEmail(ConstTest.INVALID_EMAIL_TEST);
        user.setPassword(ConstTest.PASSWORD_VALID);

        assertThrows(InvalidUserEmailFormatException.class, () -> userCase.createEmployee(user, ConstTest.ID_TEST));
    }

    @Test
    void createEmployee_withInvalidPhoneFormat_shouldThrowException() {
        User user = new User();
        user.setName(ConstTest.NAME_VALID);
        user.setLastName(ConstTest.LAST_NAME_VALID);
        user.setDocument(ConstTest.DOCUMENT_VALID);
        user.setPhone(ConstTest.INVALID_PHONE_TEST);
        user.setBirthdate(ConstTest.BIRTHDATE_VALID);
        user.setEmail(ConstTest.EMAIL_VALID);
        user.setPassword(ConstTest.PASSWORD_VALID);

        assertThrows(InvalidUserPhoneFormatException.class, () -> userCase.createEmployee(user, ConstTest.ID_TEST));
    }

    @Test
    void createEmployee_withInvalidDocumentFormat_shouldThrowException() {
        User user = new User();
        user.setName(ConstTest.NAME_VALID);
        user.setLastName(ConstTest.LAST_NAME_VALID);
        user.setDocument(ConstTest.INVALID_DOCUMENT_TEST);
        user.setPhone(ConstTest.PHONE_VALID);
        user.setBirthdate(ConstTest.BIRTHDATE_VALID);
        user.setEmail(ConstTest.EMAIL_VALID);
        user.setPassword(ConstTest.PASSWORD_VALID);

        assertThrows(InvalidUserDocumentFormatException.class, () -> userCase.createEmployee(user, ConstTest.ID_TEST));
    }

    @Test
    void createEmployee_withEmptyLastName_shouldThrowException() {
        User user = new User();
        user.setName(ConstTest.NAME_VALID);
        user.setLastName(ConstTest.EMPTY_STRING);
        user.setDocument(ConstTest.DOCUMENT_VALID);
        user.setPhone(ConstTest.PHONE_VALID);
        user.setBirthdate(ConstTest.BIRTHDATE_VALID);
        user.setEmail(ConstTest.EMAIL_VALID);
        user.setPassword(ConstTest.PASSWORD_VALID);

        assertThrows(EmptyUserLastNameException.class, () -> userCase.createEmployee(user, ConstTest.ID_TEST));
    }

    @Test
    void createEmployee_withBlankDocument_shouldThrowException() {
        User user = new User();
        user.setName(ConstTest.NAME_VALID);
        user.setLastName(ConstTest.LAST_NAME_VALID);
        user.setDocument(ConstTest.EMPTY_STRING);
        user.setPhone(ConstTest.PHONE_VALID);
        user.setBirthdate(ConstTest.BIRTHDATE_VALID);
        user.setEmail(ConstTest.EMAIL_VALID);
        user.setPassword(ConstTest.PASSWORD_VALID);

        assertThrows(EmptyUserDocumentException.class, () -> userCase.createEmployee(user, ConstTest.ID_TEST));
    }

    @Test
    void createEmployee_withBlankPhone_shouldThrowException() {
        User user = new User();
        user.setName(ConstTest.NAME_VALID);
        user.setLastName(ConstTest.LAST_NAME_VALID);
        user.setDocument(ConstTest.DOCUMENT_VALID);
        user.setPhone(ConstTest.EMPTY_STRING);
        user.setBirthdate(ConstTest.BIRTHDATE_VALID);
        user.setEmail(ConstTest.EMAIL_VALID);
        user.setPassword(ConstTest.PASSWORD_VALID);

        assertThrows(EmptyUserPhoneException.class, () -> userCase.createEmployee(user, ConstTest.ID_TEST));
    }

    @Test
    void createEmployee_withNullBirthdate_shouldThrowException() {
        User user = new User();
        user.setName(ConstTest.NAME_VALID);
        user.setLastName(ConstTest.LAST_NAME_VALID);
        user.setDocument(ConstTest.DOCUMENT_VALID);
        user.setPhone(ConstTest.PHONE_VALID);
        user.setBirthdate(null);
        user.setEmail(ConstTest.EMAIL_VALID);
        user.setPassword(ConstTest.PASSWORD_VALID);

        assertThrows(EmptyUserBirthdateException.class, () -> userCase.createEmployee(user, ConstTest.ID_TEST));
    }

    @Test
    void createEmployee_withBlankEmail_shouldThrowException() {
        User user = new User();
        user.setName(ConstTest.NAME_VALID);
        user.setLastName(ConstTest.LAST_NAME_VALID);
        user.setDocument(ConstTest.DOCUMENT_VALID);
        user.setPhone(ConstTest.PHONE_VALID);
        user.setBirthdate(ConstTest.BIRTHDATE_VALID);
        user.setEmail(ConstTest.EMPTY_STRING);
        user.setPassword(ConstTest.PASSWORD_VALID);

        assertThrows(EmptyUserEmailException.class, () -> userCase.createEmployee(user, ConstTest.ID_TEST));
    }

    @Test
    void createEmployee_withBlankPassword_shouldThrowException() {
        User user = new User();
        user.setName(ConstTest.NAME_VALID);
        user.setLastName(ConstTest.LAST_NAME_VALID);
        user.setDocument(ConstTest.DOCUMENT_VALID);
        user.setPhone(ConstTest.PHONE_VALID);
        user.setBirthdate(ConstTest.BIRTHDATE_VALID);
        user.setEmail(ConstTest.EMAIL_VALID);
        user.setPassword(ConstTest.EMPTY_STRING);

        assertThrows(EmptyUserPasswordException.class, () -> userCase.createEmployee(user, ConstTest.ID_TEST));
    }

    @Test
    void createEmployee_withUserOlderThanValidAge_shouldThrowException() {
        User user = new User();
        user.setName(ConstTest.NAME_VALID);
        user.setLastName(ConstTest.LAST_NAME_VALID);
        user.setDocument(ConstTest.DOCUMENT_VALID);
        user.setPhone(ConstTest.PHONE_VALID);
        user.setBirthdate(LocalDate.now().minusYears(ConstValidation.MIN_AGE - 1));
        user.setEmail(ConstTest.EMAIL_VALID);
        user.setPassword(ConstTest.PASSWORD_VALID);

        assertThrows(UserOlderThatTheValidAgeException.class, () -> userCase.createEmployee(user, ConstTest.ID_TEST));
    }
    @Test
    void createEmployee_withExistingEmail_shouldThrowException() {
        User user = new User();
        user.setName(ConstTest.NAME_VALID);
        user.setLastName(ConstTest.LAST_NAME_VALID);
        user.setDocument(ConstTest.DOCUMENT_VALID);
        user.setPhone(ConstTest.PHONE_VALID);
        user.setBirthdate(ConstTest.BIRTHDATE_VALID);
        user.setEmail(ConstTest.EMAIL_VALID);
        user.setPassword(ConstTest.PASSWORD_VALID);

        when(userPersistencePort.existsUserIdByEmail(user.getEmail())).thenReturn(true);

        assertThrows(UserAlreadyExistsException.class, () -> userCase.createEmployee(user, ConstTest.ID_TEST));
    }
    @Test
    void createClient_withValidUser_shouldCreateClient() {
        User user = new User();
        user.setName(ConstTest.NAME_VALID);
        user.setLastName(ConstTest.LAST_NAME_VALID);
        user.setDocument(ConstTest.DOCUMENT_VALID);
        user.setPhone(ConstTest.PHONE_VALID);
        user.setBirthdate(ConstTest.BIRTHDATE_VALID);
        user.setEmail(ConstTest.EMAIL_VALID);
        user.setPassword(ConstTest.PASSWORD_VALID);

        Role role = new Role(ConstTest.ID_TEST, ConstRole.CLIENT);

        when(roleServicePort.getRoleByName(ConstRole.CLIENT)).thenReturn(role);
        when(encoderPersistencePort.encode(user.getPassword())).thenReturn(ConstTest.ENCODED_PASSWORD_TEST);

        userCase.createClient(user);

        assertEquals(ConstTest.ENCODED_PASSWORD_TEST, user.getPassword());
        verify(userPersistencePort, times(1)).saveUserWithRole(user, role);
    }

    @Test
    void createClient_withNullUser_shouldThrowException() {
        assertThrows(EmptyUserNameException.class, () -> userCase.createClient(null));
    }

    @Test
    void createClient_withEmptyUserName_shouldThrowException() {
        User user = new User();
        user.setName(ConstTest.EMPTY_STRING);
        user.setLastName(ConstTest.LAST_NAME_VALID);
        user.setDocument(ConstTest.DOCUMENT_VALID);
        user.setPhone(ConstTest.PHONE_VALID);
        user.setBirthdate(ConstTest.BIRTHDATE_VALID);
        user.setEmail(ConstTest.EMAIL_VALID);
        user.setPassword(ConstTest.PASSWORD_VALID);

        assertThrows(EmptyUserNameException.class, () -> userCase.createClient(user));
    }

    @Test
    void createClient_withInvalidEmailFormat_shouldThrowException() {
        User user = new User();
        user.setName(ConstTest.NAME_VALID);
        user.setLastName(ConstTest.LAST_NAME_VALID);
        user.setDocument(ConstTest.DOCUMENT_VALID);
        user.setPhone(ConstTest.PHONE_VALID);
        user.setBirthdate(ConstTest.BIRTHDATE_VALID);
        user.setEmail(ConstTest.INVALID_EMAIL_TEST);
        user.setPassword(ConstTest.PASSWORD_VALID);

        assertThrows(InvalidUserEmailFormatException.class, () -> userCase.createClient(user));
    }

    @Test
    void createClient_withInvalidPhoneFormat_shouldThrowException() {
        User user = new User();
        user.setName(ConstTest.NAME_VALID);
        user.setLastName(ConstTest.LAST_NAME_VALID);
        user.setDocument(ConstTest.DOCUMENT_VALID);
        user.setPhone(ConstTest.INVALID_PHONE_TEST);
        user.setBirthdate(ConstTest.BIRTHDATE_VALID);
        user.setEmail(ConstTest.EMAIL_VALID);
        user.setPassword(ConstTest.PASSWORD_VALID);

        assertThrows(InvalidUserPhoneFormatException.class, () -> userCase.createClient(user));
    }

    @Test
    void createClient_withInvalidDocumentFormat_shouldThrowException() {
        User user = new User();
        user.setName(ConstTest.NAME_VALID);
        user.setLastName(ConstTest.LAST_NAME_VALID);
        user.setDocument(ConstTest.INVALID_DOCUMENT_TEST);
        user.setPhone(ConstTest.PHONE_VALID);
        user.setBirthdate(ConstTest.BIRTHDATE_VALID);
        user.setEmail(ConstTest.EMAIL_VALID);
        user.setPassword(ConstTest.PASSWORD_VALID);

        assertThrows(InvalidUserDocumentFormatException.class, () -> userCase.createClient(user));
    }

    @Test
    void createClient_withEmptyLastName_shouldThrowException() {
        User user = new User();
        user.setName(ConstTest.NAME_VALID);
        user.setLastName(ConstTest.EMPTY_STRING);
        user.setDocument(ConstTest.DOCUMENT_VALID);
        user.setPhone(ConstTest.PHONE_VALID);
        user.setBirthdate(ConstTest.BIRTHDATE_VALID);
        user.setEmail(ConstTest.EMAIL_VALID);
        user.setPassword(ConstTest.PASSWORD_VALID);

        assertThrows(EmptyUserLastNameException.class, () -> userCase.createClient(user));
    }

    @Test
    void createClient_withBlankDocument_shouldThrowException() {
        User user = new User();
        user.setName(ConstTest.NAME_VALID);
        user.setLastName(ConstTest.LAST_NAME_VALID);
        user.setDocument(ConstTest.EMPTY_STRING);
        user.setPhone(ConstTest.PHONE_VALID);
        user.setBirthdate(ConstTest.BIRTHDATE_VALID);
        user.setEmail(ConstTest.EMAIL_VALID);
        user.setPassword(ConstTest.PASSWORD_VALID);

        assertThrows(EmptyUserDocumentException.class, () -> userCase.createClient(user));
    }

    @Test
    void createClient_withBlankPhone_shouldThrowException() {
        User user = new User();
        user.setName(ConstTest.NAME_VALID);
        user.setLastName(ConstTest.LAST_NAME_VALID);
        user.setDocument(ConstTest.DOCUMENT_VALID);
        user.setPhone(ConstTest.EMPTY_STRING);
        user.setBirthdate(ConstTest.BIRTHDATE_VALID);
        user.setEmail(ConstTest.EMAIL_VALID);
        user.setPassword(ConstTest.PASSWORD_VALID);

        assertThrows(EmptyUserPhoneException.class, () -> userCase.createClient(user));
    }

    @Test
    void createClient_withNullBirthdate_shouldThrowException() {
        User user = new User();
        user.setName(ConstTest.NAME_VALID);
        user.setLastName(ConstTest.LAST_NAME_VALID);
        user.setDocument(ConstTest.DOCUMENT_VALID);
        user.setPhone(ConstTest.PHONE_VALID);
        user.setBirthdate(null);
        user.setEmail(ConstTest.EMAIL_VALID);
        user.setPassword(ConstTest.PASSWORD_VALID);

        assertThrows(EmptyUserBirthdateException.class, () -> userCase.createClient(user));
    }

    @Test
    void createClient_withBlankEmail_shouldThrowException() {
        User user = new User();
        user.setName(ConstTest.NAME_VALID);
        user.setLastName(ConstTest.LAST_NAME_VALID);
        user.setDocument(ConstTest.DOCUMENT_VALID);
        user.setPhone(ConstTest.PHONE_VALID);
        user.setBirthdate(ConstTest.BIRTHDATE_VALID);
        user.setEmail(ConstTest.EMPTY_STRING);
        user.setPassword(ConstTest.PASSWORD_VALID);

        assertThrows(EmptyUserEmailException.class, () -> userCase.createClient(user));
    }

    @Test
    void createClient_withBlankPassword_shouldThrowException() {
        User user = new User();
        user.setName(ConstTest.NAME_VALID);
        user.setLastName(ConstTest.LAST_NAME_VALID);
        user.setDocument(ConstTest.DOCUMENT_VALID);
        user.setPhone(ConstTest.PHONE_VALID);
        user.setBirthdate(ConstTest.BIRTHDATE_VALID);
        user.setEmail(ConstTest.EMAIL_VALID);
        user.setPassword(ConstTest.EMPTY_STRING);

        assertThrows(EmptyUserPasswordException.class, () -> userCase.createClient(user));
    }

    @Test
    void createClient_withUserOlderThanValidAge_shouldThrowException() {
        User user = new User();
        user.setName(ConstTest.NAME_VALID);
        user.setLastName(ConstTest.LAST_NAME_VALID);
        user.setDocument(ConstTest.DOCUMENT_VALID);
        user.setPhone(ConstTest.PHONE_VALID);
        user.setBirthdate(LocalDate.now().minusYears(ConstValidation.MIN_AGE - 1));
        user.setEmail(ConstTest.EMAIL_VALID);
        user.setPassword(ConstTest.PASSWORD_VALID);

        assertThrows(UserOlderThatTheValidAgeException.class, () -> userCase.createClient(user));
    }
}