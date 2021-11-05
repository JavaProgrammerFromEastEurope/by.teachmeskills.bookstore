package by.teachmeskills.bookstore.dao.service;

import by.teachmeskills.bookstore.dao.entity.UserPayment;

public interface UserPaymentService {

    UserPayment findById(Long id);

    void removeById(Long id);
}
