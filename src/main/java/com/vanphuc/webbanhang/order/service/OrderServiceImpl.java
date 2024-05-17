package com.vanphuc.webbanhang.order.service;

import com.vanphuc.webbanhang.common.exception.WBHBussinessException;
import com.vanphuc.webbanhang.common.utils.WBHMapper;
import com.vanphuc.webbanhang.order.dto.OrderDTO;
import com.vanphuc.webbanhang.order.dto.OrderDTOForSave;
import com.vanphuc.webbanhang.order.dto.OrderProductDTOForSave;
import com.vanphuc.webbanhang.order.model.Order;
import com.vanphuc.webbanhang.order.model.OrderProduct;
import com.vanphuc.webbanhang.order.repository.OrderProductRepository;
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
    private final OrderProductRepository orderProductRepository;
    private final WBHMapper mapper;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository, OrderProductRepository orderProductRepository, WBHMapper mapper) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderProductRepository = orderProductRepository;
        this.mapper = mapper;
    }

    @Override
    public OrderDTO save(OrderDTOForSave orderDTOForSave) {
        User user = userRepository.findByUsername(orderDTOForSave.getUsername())
                .orElseThrow(() -> new WBHBussinessException("User not found"));
        Order order = new Order();
        Set<OrderProduct> orderProducts = new HashSet<>();
        final BigDecimal[] totalPrice = {BigDecimal.ZERO};
//         productList = orderDTOForSave.getProductList();
        // key = ProductID , value = Quantity
//        productList.keySet() // listKey (list productID)
        orderDTOForSave.getProductList().forEach((orderProductDTO) -> {
            Optional<Product> productOptional = productRepository.findById(orderProductDTO.getProductID());
            if (productOptional.isEmpty()) return;
            Product product = productOptional.get();
            int quantity = orderProductDTO.getQuantity();
            OrderProduct orderProduct = OrderProduct.builder()
                    .product(product)
                    .order(order)
                    .quantity(quantity) //get value of key
                    .build();
            totalPrice[0] = totalPrice[0].add(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
            orderProducts.add(orderProduct);
            product.getOrderProducts().add(orderProduct);
        });
        if (orderProducts.isEmpty()) throw new WBHBussinessException("All inputted products are not found");
        order.setOrderProducts(orderProducts);
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
        return null;
//        return product.getOrders().stream()
//                .map((order -> mapper.map(order, OrderDTO.class))).toList();
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
    public OrderDTO addProducts(UUID orderID, List<OrderProductDTOForSave> productIDs) {
        Order order = orderRepository.findById(orderID)
                .orElseThrow(() -> new WBHBussinessException("Order not found"));
        productIDs.forEach((orderProductDTO) -> {
            Optional<Product> productOptional = productRepository.findById(orderProductDTO.getProductID());
            if (productOptional.isEmpty()) return;
            Product product = productOptional.get();
            OrderProduct orderProduct;
            Optional<OrderProduct> orderProductOptional = orderProductRepository.findByOrderIdAndProductId(orderID, orderProductDTO.getProductID());
            if (orderProductOptional.isPresent()) {
                orderProduct = orderProductOptional.get();
                orderProduct.setQuantity(orderProduct.getQuantity() + orderProductDTO.getQuantity());
            } else {
                orderProduct = OrderProduct.builder()
                        .quantity(orderProductDTO.getQuantity())
                        .product(product)
                        .order(order)
                        .build();
            }

            order.setTotalPrice(
                    order.getTotalPrice().add(
                            product.getPrice().multiply(BigDecimal.valueOf(orderProductDTO.getQuantity()))
                    )
            );

            order.getOrderProducts().add(orderProduct);
            product.getOrderProducts().add(orderProduct);
        });
        return mapper.map(orderRepository.save(order), OrderDTO.class);
    }

    @Override
    public Object removeProducts(UUID orderID, List<OrderProductDTOForSave> productList) {
        Order order = orderRepository.findById(orderID)
                .orElseThrow(() -> new WBHBussinessException("Order not found"));
        productList.forEach((orderProductDto) -> {
            Optional<Product> productOptional = productRepository.findById(orderProductDto.getProductID());
            if (productOptional.isEmpty()) return;
            Product product = productOptional.get();
            OrderProduct orderProduct;
            Optional<OrderProduct> orderProductOptional = orderProductRepository.findByOrderIdAndProductId(orderID, orderProductDto.getProductID());

            if (orderProductOptional.isPresent()) {
                orderProduct = orderProductOptional.get();
                if (orderProduct.getQuantity() >= orderProductDto.getQuantity()) {
                    orderProduct.setQuantity(orderProduct.getQuantity() - orderProductDto.getQuantity());
                    order.setTotalPrice(
                            order.getTotalPrice().subtract(
                                    product.getPrice().multiply(BigDecimal.valueOf(orderProductDto.getQuantity()))
                            )
                    );
                } else {
                    order.setTotalPrice(
                            order.getTotalPrice().subtract(product.getPrice()
                                    .multiply(BigDecimal.valueOf(orderProduct.getQuantity())))
                    );
                    order.getOrderProducts().remove(orderProduct);
                    product.getOrderProducts().remove(orderProduct);
                    orderProductRepository.delete(orderProduct);
                }
            }
        });
        if (order.getOrderProducts().isEmpty()) {
            order.getUser().getOrders().remove(order);
            userRepository.save(order.getUser());
            orderRepository.delete(order);
            return "Deteled order due to product list is empty";
        }
        return mapper.map(orderRepository.save(order), OrderDTO.class);
    }

    @Override
    public void deleteByID(UUID id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new WBHBussinessException("Order not found"));
//        order.getProducts().forEach((product -> {
//            product.getOrders().remove(order);
//        }));
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
