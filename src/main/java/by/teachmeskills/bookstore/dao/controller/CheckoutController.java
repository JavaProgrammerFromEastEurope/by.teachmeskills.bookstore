package by.teachmeskills.bookstore.dao.controller;

import by.teachmeskills.bookstore.dao.entity.*;
import by.teachmeskills.bookstore.dao.service.*;
import by.teachmeskills.bookstore.dao.utility.MailConstructor;
import by.teachmeskills.bookstore.dao.utility.USConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Controller
public class CheckoutController {

    private final UserService userService;

    private final MailConstructor mailConstructor;

    private final UserShippingService userShippingService;

    private final UserPaymentService userPaymentService;

    private final CartItemService cartItemService;

    private final ShippingAddressService shippingAddressService;

    private final PaymentService paymentService;

    private final BillingAddressService billingAddressService;

    private final ShoppingCartService shoppingCartService;

    @Autowired
    public JavaMailSender mailSender;

    private final OrderService orderService;

    private ShippingAddress shippingAddress = new ShippingAddress();
    private BillingAddress billingAddress = new BillingAddress();
    private Payment payment = new Payment();

    public CheckoutController(UserShippingService userShippingService,
                              UserService userService,
                              MailConstructor mailConstructor,
                              UserPaymentService userPaymentService,
                              CartItemService cartItemService,
                              ShippingAddressService shippingAddressService,
                              PaymentService paymentService,
                              BillingAddressService billingAddressService,
                              ShoppingCartService shoppingCartService,

                              OrderService orderService) {
        this.userShippingService = userShippingService;
        this.userService = userService;
        this.mailConstructor = mailConstructor;
        this.userPaymentService = userPaymentService;
        this.cartItemService = cartItemService;
        this.shippingAddressService = shippingAddressService;
        this.paymentService = paymentService;
        this.billingAddressService = billingAddressService;
        this.shoppingCartService = shoppingCartService;

        this.orderService = orderService;
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    public String checkout(
            @RequestParam("id") Long cartId,
            @RequestParam(value = "missingRequestField", required = false) boolean missingRequestField,
            Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());

        if (!cartId.equals(user.getShoppingCart().getId())) {
            return "badRequestPage";
        }

        List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());
        if (cartItemList.size() == 0) {
            model.addAttribute("emptyCart", true);
            return "forward:/shoppingCart/cart";
        }

        for (CartItem cartItem : cartItemList) {
            if (cartItem.getBook().getInStockNumber() < cartItem.getQuantity()) {
                model.addAttribute("notEnoughStock", true);
                return "forward:/shoppingCart/cart";
            }
        }

        List<UserShipping> userShippingList = user.getUserShippingList();
        List<UserPayment> userPaymentList = user.getUserPaymentsList();

        model.addAttribute("userShippingList", userShippingList);
        model.addAttribute("userPaymentList", userPaymentList);

        if (userPaymentList.size() == 0) {
            model.addAttribute("emptyPaymentList", true);
        } else {
            model.addAttribute("emptyPaymentList", false);
        }

        if (userShippingList.size() == 0) {
            model.addAttribute("emptyShippingList", true);
        } else {
            model.addAttribute("emptyShippingList", false);
        }

        ShoppingCart shoppingCart = user.getShoppingCart();

        for (UserShipping userShipping : userShippingList) {
            if (userShipping.isUserShippingDefault()) {
                shippingAddressService.setByUserShipping(userShipping, shippingAddress);
            }
        }

        for (UserPayment userPayment : userPaymentList) {
            if (userPayment.isDefaultPayment()) {
                paymentService.setByUserPayment(userPayment, payment);
                billingAddressService.setByUserBilling(userPayment.getUserBilling(), billingAddress);
            }
        }

        model.addAttribute("shippingAddress", shippingAddress);
        model.addAttribute("billingAddress", billingAddress);
        model.addAttribute("payment", payment);
        model.addAttribute("shoppingCart", user.getShoppingCart());
        model.addAttribute("cartItemList", cartItemList);

        List<String> stateList = USConstants.listOfUSStatesCode;
        Collections.sort(stateList);
        model.addAttribute("stateList", stateList);

        model.addAttribute("classActiveShipping", true);

        if (missingRequestField) {
            model.addAttribute("missingRequiredField", true);
        }
        return "checkout";
    }
@RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public String checkoutPost(
            @ModelAttribute("shippingAddress") ShippingAddress shippingAddress,
            @ModelAttribute("billingAddress") BillingAddress billingAddress,
            @ModelAttribute("payment") Payment payment,
            @ModelAttribute("billingSameAsShipping") String billingSameAsShipping,
            @ModelAttribute("shippingMethod") String shippingMethod,
            Principal principal, Model model) {
        ShoppingCart shoppingCart = userService.findByUsername(principal.getName()).getShoppingCart();

        List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
        model.addAttribute("cartItemList", cartItemList);

        if (billingSameAsShipping.equals("true")) {
            billingAddress.setBillingAddressName(shippingAddress.getShippingAddressName());
            billingAddress.setFirstBillingAddressStreet(shippingAddress.getShippingAddressStreet1());
            billingAddress.setSecondBillingAddressStreet(shippingAddress.getShippingAddressStreet2());
            billingAddress.setBillingAddressCity(shippingAddress.getShippingAddressCity());
            billingAddress.setBillingAddressState(shippingAddress.getShippingAddressState());
            billingAddress.setBillingAddressCountry(shippingAddress.getShippingAddressCountry());
            billingAddress.setBillingAddressZipCode(shippingAddress.getShippingAddressZipCode());
        }

        if (shippingAddress.getShippingAddressStreet1().isEmpty()
                || shippingAddress.getShippingAddressCity().isEmpty()
                || shippingAddress.getShippingAddressState().isEmpty()
                || shippingAddress.getShippingAddressName().isEmpty()
                || shippingAddress.getShippingAddressZipCode().isEmpty()

                || billingAddress.getFirstBillingAddressStreet().isEmpty()
                || billingAddress.getBillingAddressCity().isEmpty()
                || billingAddress.getBillingAddressState().isEmpty()
                || billingAddress.getBillingAddressName().isEmpty()
                || billingAddress.getBillingAddressZipCode().isEmpty()

                || payment.getCardNumber().isEmpty()
                || payment.getCvc() == 0) {

            return "redirect:/checkout?id=" + shoppingCart.getId() + "&missingRequiredField=true";
        }

        User user = userService.findByUsername(principal.getName());

        Order order = orderService.createOrder(shoppingCart, shippingAddress, billingAddress, payment, shippingMethod, user);

        mailSender.send(mailConstructor.constructOrderConfirmationEmail(user,order, Locale.ENGLISH));

        shoppingCartService.clearShoppingCart(shoppingCart);

        LocalDate today = LocalDate.now();
        LocalDate estimatedDeliveryDate;

        if (shippingMethod.equals("groundShipping")){
            estimatedDeliveryDate = today.plusDays(5);
        } else {
            estimatedDeliveryDate = today.plusDays(3);
        }

        model.addAttribute("estimatedDeliveryDate", estimatedDeliveryDate);

        return "orderSubmittedPage";
    }


    @RequestMapping(value = "/setShippingAddress", method = RequestMethod.POST)
    public String setShippingAddress(
            @RequestParam("userShippingId") Long userShippingId,
            Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        UserShipping userShipping = userShippingService.findById(userShippingId);

        if (!userShipping.getUser().getId().equals(user.getId())) {
            return "badRequestPage";
        } else {
            shippingAddressService.setByUserShipping(userShipping, shippingAddress);

            List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());

            BillingAddress billingAddress = new BillingAddress();

            model.addAttribute("billingAddress", billingAddress);
            model.addAttribute("payment", payment);
            model.addAttribute("shoppingCart", user.getShoppingCart());
            model.addAttribute("cartItemList", cartItemList);

            List<String> stateList = USConstants.listOfUSStatesCode;
            Collections.sort(stateList);
            model.addAttribute("stateList", stateList);

            List<UserShipping> userShippingList = user.getUserShippingList();
            List<UserPayment> userPaymentList = user.getUserPaymentsList();

            model.addAttribute("userShippingList", userShippingList);
            model.addAttribute("userPaymentList", userPaymentList);
            model.addAttribute("shippingAddress", shippingAddress);

            model.addAttribute("classActiveShipping", true);

            if (userPaymentList.size() == 0) {
                model.addAttribute("emptyPaymentList", true);
            } else {
                model.addAttribute("emptyPaymentList", false);
            }

            model.addAttribute("emptyShippingList", false);

            return "checkout";
        }
    }

    @RequestMapping(value = "/setPaymentMethod", method = RequestMethod.POST)
    public String setPaymentMethod(
            @RequestParam("userPaymentId") Long userPaymentId,
            Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        UserPayment userPayment = userPaymentService.findById(userPaymentId);
        UserBilling userBilling = userPayment.getUserBilling();

        if (!userPayment.getUser().getId().equals(user.getId())) {
            return "badRequestPage";
        } else {
            paymentService.setByUserPayment(userPayment, payment);

            List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());

            billingAddressService.setByUserBilling(userBilling, billingAddress);

            model.addAttribute("billingAddress", billingAddress);
            model.addAttribute("payment", payment);
            model.addAttribute("shoppingCart", user.getShoppingCart());
            model.addAttribute("cartItemList", cartItemList);

            List<String> stateList = USConstants.listOfUSStatesCode;
            Collections.sort(stateList);
            model.addAttribute("stateList", stateList);

            List<UserShipping> userShippingList = user.getUserShippingList();
            List<UserPayment> userPaymentList = user.getUserPaymentsList();

            model.addAttribute("userShippingList", userShippingList);
            model.addAttribute("userPaymentList", userPaymentList);
            model.addAttribute("shippingAddress", shippingAddress);
            model.addAttribute("classActivePayment", true);


            model.addAttribute("emptyPaymentList", false);

            if (userShippingList.size() == 0) {
                model.addAttribute("emptyShippingList", true);
            } else {
                model.addAttribute("emptyShippingList", false);
            }
            return "checkout";
        }
    }
}