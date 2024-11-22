package com.emazon.user.domain.model;

import com.emazon.user.domain.exception.*;
import java.time.LocalDate;
import java.time.Period;

public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String identityDocument;
    private String phone;
    private LocalDate birthDate;
    private String email;
    private String password;
    private Long id_role;

    public User() {}

    public User(String firstName, String lastName, String identityDocument, String phone,
                LocalDate birthDate, String email, String password, Long id_role) {
        setFirstName(firstName);
        setLastName(lastName);
        setIdentityDocument(identityDocument);
        setPhone(phone);
        setBirthDate(birthDate);
        setEmail(email);
        setPassword(password);
        setId_role(id_role);
    }

    public void validate() {
        validateFirstName(this.firstName);
        validateLastName(this.lastName);
        validatePhone(this.phone);
        validateIdentityDocument(this.identityDocument);
        validateBirthdate(this.birthDate);
        validateEmail(this.email);
        validatePassword(this.password);
        validateIdRole(this.id_role);
    }

    // Métodos de validación
    private void validateFirstName(String firstName) {
        if (firstName == null) {
            throw new FieldNameNullException();
        }
        if (firstName.trim().isEmpty()) {
            throw new FieldNameEmptyException();
        }
        if (firstName.length() > 50) {
            throw new FieldNameLengthException();
        }
    }

    private void validateLastName(String lastName) {
        if (lastName == null) {
            throw new FieldLastnameNullException();
        }
        if (lastName.trim().isEmpty()) {
            throw new FieldLastnameEmptyException();
        }
        if (lastName.length() > 50) {
            throw new FieldLastnameLengthException();
        }
    }

    private void validatePhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            throw new FieldPhoneNullException();
        }
    }

    private void validateIdentityDocument(String identityDocument) {
        if (identityDocument == null) {
            throw new FieldIdDocumentNullException();
        }
        if (!identityDocument.matches("\\d+")) {
            throw new FieldIdDocumentInvalidException();
        }
    }

    private void validateBirthdate(LocalDate birthDate) {
        if (birthDate == null) {
            throw new FieldBirthdateNullException();
        }
        if (Period.between(birthDate, LocalDate.now()).getYears() < 18) {
            throw new FieldBirthdateInvalidException();
        }
    }

    private void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new FieldEmailInvalidException();
        }
    }

    private void validatePassword(String password) {
        if (password == null || password.length() < 6) {
            throw new FieldPasswordNullException();
        }
    }

    private void validateIdRole(Long id_role) {
        if (id_role == null) {
            throw new FieldRoleNullException();
        }
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdentityDocument() {
        return identityDocument;
    }

    public void setIdentityDocument(String identityDocument) {
        this.identityDocument = identityDocument;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId_role() {
        return id_role;
    }

    public void setId_role(Long id_role) {
        this.id_role = id_role;
    }
}