package by.teachmeskills.bookstore.dao.service;

import by.teachmeskills.bookstore.dao.entity.UserShipping;

public interface UserShippingService {
    UserShipping findById(Long id);

    void removeById(Long id);
}
