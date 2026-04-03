package com.example.project_management.service;

import com.example.project_management.entity.Cart;
import com.example.project_management.entity.CartItem;
import com.example.project_management.entity.Product;
import com.example.project_management.repository.IProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CartService implements ICartService {

    private final IProductRepository productRepository;

    public CartService(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public void checkout(Cart cart) {
        for (CartItem item : cart.getItems()) {
            Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));

            if (product.getQuantity() < item.getQuantity()) {
                throw new RuntimeException("Sản phẩm " + product.getName() + " không đủ số lượng trong kho");
            }

            product.setQuantity(product.getQuantity() - item.getQuantity());
            productRepository.save(product);
        }
    }
}
