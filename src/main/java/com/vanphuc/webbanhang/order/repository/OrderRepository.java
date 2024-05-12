package com.vanphuc.webbanhang.order.repository;

import com.vanphuc.webbanhang.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    Optional<Order> findById(UUID id);

    @Query("SELECT o FROM Order o WHERE o.user.username = :username")
    List<Order> findByUsername(String username);
}
