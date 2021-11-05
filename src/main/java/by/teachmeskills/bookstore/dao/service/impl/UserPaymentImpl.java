package by.teachmeskills.bookstore.dao.service.impl;

import by.teachmeskills.bookstore.dao.entity.UserPayment;
import by.teachmeskills.bookstore.dao.repository.UserPaymentRepository;
import by.teachmeskills.bookstore.dao.service.UserPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPaymentImpl implements UserPaymentService {
    @Autowired
    private UserPaymentRepository userPaymentRepository;

    public UserPayment findById(Long id) {
        return userPaymentRepository.findById(id).get();
    }

    @Override
    public void removeById(Long id) {
        userPaymentRepository.deleteById(id);
    }

}