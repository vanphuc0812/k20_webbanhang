package com.vanphuc.webbanhang.order.service;

import com.vanphuc.webbanhang.common.service.GenericService;
import com.vanphuc.webbanhang.order.dto.OrderDTO;
import com.vanphuc.webbanhang.order.dto.OrderDTOForSave;
import com.vanphuc.webbanhang.order.model.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService extends GenericService<Order, OrderDTO, UUID> {
    OrderDTO save(OrderDTOForSave orderDTOForSave);

    List<OrderDTO> findAll();

    List<OrderDTO> findOrderByProductID(UUID productId);

    List<OrderDTO> findOrderByUsername(String username);
    
    OrderDTO findById(UUID id);

    OrderDTO addProducts(UUID orderID, List<UUID> productIDs);

    OrderDTO removeProducts(UUID orderID, List<UUID> productIDs);

    void deleteByID(UUID id);

}
