package com.vanphuc.webbanhang.product.service;

import com.vanphuc.webbanhang.common.exception.WBHBussinessException;
import com.vanphuc.webbanhang.common.utils.WBHMapper;
import com.vanphuc.webbanhang.product.dto.ProductDTO;
import com.vanphuc.webbanhang.product.dto.ProductDTOForSave;
import com.vanphuc.webbanhang.product.dto.ProductDTOForUpdate;
import com.vanphuc.webbanhang.product.model.Product;
import com.vanphuc.webbanhang.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    public ProductServiceImpl(ProductRepository productRepository, WBHMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public JpaRepository<Product, UUID> getRepository() {
        return productRepository;
    }

    @Override
    public ModelMapper getMapper() {
        return mapper;
    }

    @Override
    public ProductDTO save(ProductDTOForSave dto) {
        Product product = productRepository.save(mapper.map(dto, Product.class));
        return mapper.map(product, ProductDTO.class);
    }

    @Override
    public ProductDTO update(ProductDTOForUpdate dto) {
        Optional<Product> productOptional = productRepository.findById(dto.getId());
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setName(dto.getName());
            product.setPrice(dto.getPrice());
            product.setCatalog(dto.getCatalog());
            product.setImage(dto.getImage());
            return mapper.map(productRepository.save(product), ProductDTO.class);
        } else throw new WBHBussinessException("Product Not Found");
    }

    @Override
    public void deleteByID(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new WBHBussinessException("Product Not Found"));
        product.getOrders().forEach((order -> {
            order.getProducts().remove(product);
        }));
        productRepository.delete(product);
    }
}
