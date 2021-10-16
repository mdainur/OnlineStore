package javaEE.springboot.springbootdemo.repositories;

import javaEE.springboot.springbootdemo.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
