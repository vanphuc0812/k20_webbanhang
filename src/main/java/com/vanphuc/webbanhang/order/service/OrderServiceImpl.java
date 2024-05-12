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
        Order order = new Order();
        Set<Product> productList = new HashSet<>();
        final BigDecimal[] totalPrice = {BigDecimal.ZERO};
        orderDTOForSave.getProductIdList().forEach((productId) -> {
            Optional<Product> productOptional = productRepository.findById(productId);
            if (productOptional.isEmpty()) return;
            Product product = productOptional.get();
            totalPrice[0] = totalPrice[0].add(product.getPrice());
            productList.add(productOptional.get());
            product.getOrders().add(order);
        });
        if (productList.isEmpty()) throw new WBHBussinessException("All inputted products are not found");
        order.setProducts(productList);
        order.setTotalPrice(totalPrice[0]);
        order.setUser(user);
        user.getOrders().add(order);
        return mapper.map(orderRepository.save(order), OrderDTO.class);
    }

    @Override
    public List<OrderDTO> findAll() {
        return orderRepository.findAll().stream().map((model) ->
                mapper.map(model, OrderDTO.class)
        ).toList();
    }

    @Override
    public List<OrderDTO> findOrderByProductID(UUID productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new WBHBussinessException("Product not found"));
        return product.getOrders().stream()
                .map((order -> mapper.map(order, OrderDTO.class))).toList();
    }

    @Override
    public List<OrderDTO> findOrderByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new WBHBussinessException("User not found"));
        return orderRepository.findByUsername(username).stream()
                .map((order -> mapper.map(order, OrderDTO.class))).toList();
    }

    @Override
    public OrderDTO findById(UUID id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new WBHBussinessException("Order not found"));
        return mapper.map(order, OrderDTO.class);
    }

    @Override
    public OrderDTO addProducts(UUID orderID, List<UUID> productIDs) {
        Order order = orderRepository.findById(orderID)
                .orElseThrow(() -> new WBHBussinessException("Order not found"));
        productIDs.forEach((productId) -> {
            Optional<Product> productOptional = productRepository.findById(productId);
            if (productOptional.isEmpty()) return;
            Product product = productOptional.get();
            order.setTotalPrice(order.getTotalPrice().add(product.getPrice()));
            order.getProducts().add(productOptional.get());
            product.getOrders().add(order);
        });
        return mapper.map(orderRepository.save(order), OrderDTO.class);
    }

    @Override
    public OrderDTO removeProducts(UUID orderID, List<UUID> productIDs) {
        Order order = orderRepository.findById(orderID)
                .orElseThrow(() -> new WBHBussinessException("Order not found"));
        productIDs.forEach((productId) -> {
            Optional<Product> productOptional = productRepository.findById(productId);
            if (productOptional.isEmpty()) return;
            Product product = productOptional.get();
            // check if product existed in product list of order
            if (order.getProducts().contains(product)) {
                order.getProducts().remove(product);
                product.getOrders().remove(order);
                order.setTotalPrice(order.getTotalPrice().subtract(product.getPrice()));
            }
        });

        return mapper.map(orderRepository.save(order), OrderDTO.class);
    }

    @Override
    public void deleteByID(UUID id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new WBHBussinessException("Order not found"));
        order.getProducts().forEach((product -> {
            product.getOrders().remove(order);
        }));
        orderRepository.delete(order);
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
