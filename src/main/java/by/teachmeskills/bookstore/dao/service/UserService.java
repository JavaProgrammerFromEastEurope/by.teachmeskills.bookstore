package by.teachmeskills.bookstore.dao.service;


import by.teachmeskills.bookstore.dao.entity.*;
import by.teachmeskills.bookstore.dao.security.PasswordResetToken;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public interface UserService {
    PasswordResetToken getPasswordResetToken(String token);

    void createPasswordResetTokenForUser(User user, String token);

    User findById(Long id);

    User findByUsername(String username);

    User findByEmail(String email);

    User createUser(User user, Set<UserRole> userRoles) throws Exception;

    User save(User user);

    void updateUserBilling(UserPayment userPayment, UserBilling userBilling, User user);

    void updateUserShipping(UserShipping userShipping, User user);

    void setUserDefaultPayment(Long userPaymentId, User user);

    void setUserDefaultShipping(Long userShippingId, User user);
}