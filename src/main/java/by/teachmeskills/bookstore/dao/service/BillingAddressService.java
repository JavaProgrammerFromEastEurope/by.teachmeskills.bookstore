package by.teachmeskills.bookstore.dao.service;


import by.teachmeskills.bookstore.dao.entity.BillingAddress;
import by.teachmeskills.bookstore.dao.entity.UserBilling;

public interface BillingAddressService {

    BillingAddress setByUserBilling(UserBilling userBilling, BillingAddress billingAddress);
}
