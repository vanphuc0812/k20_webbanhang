package com.vanphuc.webbanhang.order.service;

import com.vanphuc.webbanhang.common.exception.WBHBussinessException;
import com.vanphuc.webbanhang.common.utils.WBHMapper;
import com.vanphuc.webbanhang.order.dto.OrderDTO;
import com.vanphuc.webbanhang.order.dto.OrderDTOForSave;
import com.vanphuc.webbanhang.order.model.Order;
import com.vanphuc.webbanhang.order.repository.OrderRepository;
import com.vanphuc.webbanhang.product.model.Product;
import com.vanphuc.webbanhang.product.repository.ProductRepository;
import com.vanphuc.webbanhang.user.model.User;
import com.vanphuc.webbanhang.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final WBHMapper mapper;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository, WBHMapper mapper) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public OrderDTO save(OrderDTOForSave orderDTOForSave) {
        User user = userRepository.findByUsername(orderDTOForSave.getUsername())
                .orElseThrow(() -> new WBHBussinessException("User not found"));
        Set<Product> productList = new HashSet<>();
        final BigDecimal[] totalPrice = {BigDecimal.ZERO};
        orderDTOForSave.getProductIdList().forEach((productId) -> {
            Optional<Product> productOptional = productRepository.findById(productId);
            if (productOptional.isEmpty()) return;
            totalPrice[0] = totalPrice[0].add(productOptional.get().getPrice());
            productList.add(productOptional.get());
        });
        if (productList.isEmpty()) throw new WBHBussinessException("All inputted products are not found");

        return mapper.map(orderRepository.save(new Order(user, totalPrice[0], productList)), OrderDTO.class);
    }

    @Override
    public List<OrderDTO> findAll() {
//        orderRepository.findAll().forEach(order -> System.out.println());
        return orderRepository.findAll().stream().map((model) -> {
                    System.out.println(model.getProducts().size());

                    return mapper.map(model, OrderDTO.class);
                }
        ).toList();
    }

    @Override
    public OrderDTO findById(UUID id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new WBHBussinessException("Order not found"));
        return mapper.map(order, OrderDTO.class);
    }

    @Override
    public JpaRepository<Order, UUID> getRepository() {
        return orderRepository;
    }

    @Override
    public ModelMapper getMapper() {
        return mapper;
    }
}
