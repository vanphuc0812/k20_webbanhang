package com.vanphuc.webbanhang.order.repository;

import com.vanphuc.webbanhang.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    Optional<Order> findById(UUID id);
}
