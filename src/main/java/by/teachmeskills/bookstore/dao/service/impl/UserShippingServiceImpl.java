package by.teachmeskills.bookstore.dao.service.impl;


import by.teachmeskills.bookstore.dao.entity.UserShipping;
import by.teachmeskills.bookstore.dao.repository.UserShippingRepository;
import by.teachmeskills.bookstore.dao.service.UserShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserShippingServiceImpl implements UserShippingService {
    @Autowired
    private UserShippingRepository userShippingRepository;

    public UserShipping findById(Long id) {
        return userShippingRepository.findById(id).get();
    }

    @Override
    public void removeById(Long id) {
        userShippingRepository.deleteById(id);
    }

}
