package com.vanphuc.webbanhang.product.service;

import com.vanphuc.webbanhang.common.service.GenericService;
import com.vanphuc.webbanhang.product.dto.ProductDTO;
import com.vanphuc.webbanhang.product.dto.ProductDTOForSave;
import com.vanphuc.webbanhang.product.dto.ProductDTOForUpdate;
import com.vanphuc.webbanhang.product.model.Product;

import java.util.UUID;


public interface ProductService extends GenericService<Product, ProductDTO, UUID> {
    ProductDTO save(ProductDTOForSave dto);

    ProductDTO update(ProductDTOForUpdate dto);

    void deleteByID(UUID id);
}
