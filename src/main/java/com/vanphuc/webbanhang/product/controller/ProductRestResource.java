package com.vanphuc.webbanhang.product.controller;

import com.vanphuc.webbanhang.common.utils.ResponseUtil;
import com.vanphuc.webbanhang.product.dto.ProductDTO;
import com.vanphuc.webbanhang.product.dto.ProductDTOForSave;
import com.vanphuc.webbanhang.product.dto.ProductDTOForUpdate;
import com.vanphuc.webbanhang.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductRestResource {
    ProductService productService;

    public ProductRestResource(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public Object findAll() {
        return ResponseUtil.get(productService.findAll(ProductDTO.class), HttpStatus.OK);
    }

    @PostMapping("/save")
    public Object save(@RequestBody ProductDTOForSave dto) {
        return ResponseUtil.get(
                productService.save(dto), HttpStatus.OK
        );
    }

    @PutMapping("/update")
    public Object update(@RequestBody ProductDTOForUpdate dto) {
        return ResponseUtil.get(productService.update(dto), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public Object delete(@RequestParam UUID id) {
        productService.deleteByID(id);
        return ResponseUtil.get("Delete successfully", HttpStatus.OK);
    }
}
