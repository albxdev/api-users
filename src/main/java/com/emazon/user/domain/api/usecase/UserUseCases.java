package com.emazon.user.domain.api.usecase;

import com.emazon.user.domain.api.IUserServicePort;
import com.emazon.user.domain.exception.*;
import com.emazon.user.domain.model.User;
import com.emazon.user.domain.spi.IUserPersistencePort;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class UserUseCases implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;

    public UserUseCases(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public void createUser(User user) {
        user.validate();
        validateEmail(user.getEmail());
        validatePhone(user.getPhone());
        validateIdentityDocument(user.getIdentityDocument());

        Optional<User> existingUser = userPersistencePort.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException();
        }

        // Cifrar la contraseña antes de almacenarla
        String encryptedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(encryptedPassword);

        // Validar el rol asignado
        if (!isValidRole(user.getId_role())) {
            throw new UserNotFoundException();
        }

        // Comprobar si el usuario es mayor de edad
        if (!isAdult(user.getBirthDate())) {
            throw new UserNotAdultException();
        }

        userPersistencePort.createUser(user);
    }

    @Override
    public User getUserById(Long id) {
        if (id <= 0) {
            throw new IdCannotBeNegativeOrZeroException();
        }
        return userPersistencePort.getUserById(id)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<User> listUsers(Integer page, Integer size) {
        if (page < 0 || size <= 0) {
            throw new InvalidPaginationParamsException();
        }
        return userPersistencePort.listUsers(page, size);
    }

    @Override
    public void deleteUserById(Long id) {
        String currentUserRole = getCurrentUserRole();
        if (!"admin".equals(currentUserRole)) {
            throw new UserUnauthorizedAccessException();
        }
        if (!userPersistencePort.existsById(id)) {
            throw new UserNotFoundException();
        }
        userPersistencePort.deleteUserById(id);
    }

    @Override
    public void updateUserById(Long id, User user) {
        String currentUserRole = getCurrentUserRole();
        if (!"admin".equals(currentUserRole) && !isCurrentUser(id)) {
            throw new UserUnauthorizedAccessException();
        }
        if (id <= 0) {
            throw new IdCannotBeNegativeOrZeroException();
        }
        if (!userPersistencePort.existsById(id)) {
            throw new UserNotFoundException();
        }
        user.validate();
        user.setId(id);
        userPersistencePort.updateUserById(id, user);
    }

    private String getCurrentUserRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails userDetails) {
                return userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .findFirst()
                        .orElse("ROLE_USER");
            }
        }
        return null;
    }

    private boolean isCurrentUser(Long userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails userDetails) {
                Long currentUserId = getUserIdFromUserDetails(userDetails);
                return currentUserId != null && currentUserId.equals(userId);
            }
        }
        return false;
    }

    private Long getUserIdFromUserDetails(UserDetails userDetails) {
        if (userDetails instanceof User user) {
            return user.getId();
        }
        return null;
    }

    private boolean isAdult(LocalDate birthDate) {
        return birthDate != null && Period.between(birthDate, LocalDate.now()).getYears() >= 18;
    }

    private void validateEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (!Pattern.matches(emailRegex, email)) {
            throw new InvalidEmailFormatException();
        }
    }

    private void validatePhone(String phone) {
        if (phone.length() > 13 || !phone.matches("[+\\d]+")) {
            throw new InvalidPhoneFormatException();
        }
    }

    private void validateIdentityDocument(String document) {
        if (!document.matches("\\d+")) {
            throw new InvalidIdentityDocumentException();
        }
    }

    private boolean isValidRole(Long idRole) {
        return idRole != null && (idRole.equals(1L) || idRole.equals(2L));
    }
}