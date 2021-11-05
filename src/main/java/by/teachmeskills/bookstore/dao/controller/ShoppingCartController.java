package by.teachmeskills.bookstore.dao.controller;

import by.teachmeskills.bookstore.dao.entity.Book;
import by.teachmeskills.bookstore.dao.entity.CartItem;
import by.teachmeskills.bookstore.dao.entity.ShoppingCart;
import by.teachmeskills.bookstore.dao.entity.User;
import by.teachmeskills.bookstore.dao.service.BookService;
import by.teachmeskills.bookstore.dao.service.CartItemService;
import by.teachmeskills.bookstore.dao.service.ShoppingCartService;
import by.teachmeskills.bookstore.dao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private BookService bookService;

    @RequestMapping("/cart")
    public String shoppingCart(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        ShoppingCart shoppingCart = user.getShoppingCart();

        List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);

        shoppingCartService.updateShoppingCart(shoppingCart);
        model.addAttribute("cartItemList", cartItemList);
        model.addAttribute("shoppingCart", shoppingCart);

        return "shoppingCart";
    }

    @RequestMapping(value = "/addItem", method = RequestMethod.GET)
    public String addItem(
            @ModelAttribute("book") Book book,
            @ModelAttribute("qty") String qty,
            Model model, Principal principal) {

        User user = userService.findByUsername(principal.getName());
        book = bookService.findOne(book.getId());

        if (Integer.parseInt(qty) > book.getInStockNumber()) {
            model.addAttribute("notEnoughStock", true);
            return "forward:/bookDetail?id=" + book.getId();
        }
        CartItem cartItem = cartItemService.addBookToCartItem(book, user, Integer.parseInt(qty));
            model.addAttribute("addBookSuccess", true);

        return "forward:/bookDetail?id=" + book.getId();
    }
}