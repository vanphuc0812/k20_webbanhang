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

    OrderDTO findById(UUID id);
}
