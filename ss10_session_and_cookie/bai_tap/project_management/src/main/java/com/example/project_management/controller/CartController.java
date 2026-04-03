package com.example.project_management.controller;

import com.example.project_management.entity.Cart;
import com.example.project_management.entity.Product;
import com.example.project_management.service.ICartService;
import com.example.project_management.service.IProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final IProductService productService;
    private final ICartService cartService;

    public CartController(IProductService productService, ICartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    private Cart getCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    @GetMapping("")
    public String showCart(HttpSession session, Model model) {
        Cart cart = getCart(session);
        model.addAttribute("cart", cart);
        return "cart";
    }

    @PostMapping("/add/{id}")
    public String addToCart(@PathVariable Long id, HttpSession session) {
        Product product = productService.findProductById(id);
        Cart cart = getCart(session);
        cart.addProduct(product);
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String updateCart(@RequestParam Long productId,
                             @RequestParam Integer quantity,
                             HttpSession session) {
        Cart cart = getCart(session);
        cart.updateQuantity(productId, quantity);
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }

    @GetMapping("/remove/{id}")
    public String removeProduct(@PathVariable Long id, HttpSession session) {
        Cart cart = getCart(session);
        cart.removeProduct(id);
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkoutPage(HttpSession session, Model model) {
        Cart cart = getCart(session);
        model.addAttribute("cart", cart);
        return "checkout";
    }

    @PostMapping("/checkout")
    public String checkout(HttpSession session, Model model) {
        Cart cart = getCart(session);
        cartService.checkout(cart);
        cart.clear();
        session.setAttribute("cart", cart);

        model.addAttribute("message", "Thanh toán thành công!");
        return "result";
    }
}