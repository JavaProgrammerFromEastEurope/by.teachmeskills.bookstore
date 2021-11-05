package by.teachmeskills.bookstore.dao.service.impl;

import by.teachmeskills.bookstore.dao.entity.BillingAddress;
import by.teachmeskills.bookstore.dao.entity.UserBilling;
import by.teachmeskills.bookstore.dao.service.BillingAddressService;
import org.springframework.stereotype.Service;

@Service
public class BillingAddressServiceImpl implements BillingAddressService {

    @Override
    public BillingAddress setByUserBilling(UserBilling userBilling, BillingAddress billingAddress) {
        billingAddress.setBillingAddressName(userBilling.getUserBillingName());
        billingAddress.setFirstBillingAddressStreet(userBilling.getUserBillingStreet1());
        billingAddress.setSecondBillingAddressStreet(userBilling.getUserBillingStreet2());
        billingAddress.setBillingAddressCity(userBilling.getUserBillingCity());
        billingAddress.setBillingAddressState(userBilling.getUserBillingState());
        billingAddress.setBillingAddressCountry(userBilling.getUserBillingCountry());
        billingAddress.setBillingAddressZipCode(userBilling.getUserBillingZipCode());

        return billingAddress;
    }
}
