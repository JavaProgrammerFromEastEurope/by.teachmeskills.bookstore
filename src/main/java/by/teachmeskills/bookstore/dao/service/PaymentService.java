package by.teachmeskills.bookstore.dao.service;


import by.teachmeskills.bookstore.dao.entity.Payment;
import by.teachmeskills.bookstore.dao.entity.UserPayment;

public interface PaymentService {

    Payment setByUserPayment(UserPayment userPayment, Payment payment);
}
