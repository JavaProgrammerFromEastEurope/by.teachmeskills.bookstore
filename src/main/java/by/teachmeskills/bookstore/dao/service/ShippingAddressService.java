package by.teachmeskills.bookstore.dao.service;

import by.teachmeskills.bookstore.dao.entity.ShippingAddress;
import by.teachmeskills.bookstore.dao.entity.UserShipping;

public interface ShippingAddressService {

    ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress);
}
