package com.vanphuc.webbanhang.order.repository;

import com.vanphuc.webbanhang.order.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderProductRepository extends JpaRepository<OrderProduct, UUID> {
    Optional<OrderProduct> findByOrderIdAndProductId(UUID orderID, UUID productID);

    void delete(OrderProduct orderProduct);
}
