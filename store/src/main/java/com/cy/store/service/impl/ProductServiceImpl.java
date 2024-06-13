package com.cy.store.service.impl;

import com.cy.store.entity.Product;
import com.cy.store.mapper.ProductMapper;
import com.cy.store.service.IProductService;
import com.cy.store.service.ex.ProductNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 处理商品数据的业务层实现类。
 */
@Service
public class ProductServiceImpl implements IProductService {

    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    public List<Product> findHotList() {
        List<Product> productList = productMapper.findHotList();
        productList.forEach(product -> {
            product.setPriority(null);
            product.setCreatedUser(null);
            product.setCreatedTime(null);
            product.setModifiedUser(null);
            product.setModifiedTime(null);
        });
        return productList;
    }

    @Override
    public Product findById(Integer id) {
        Product product = productMapper.findById(id);
        if (product == null) {
            throw new ProductNotFoundException("尝试访问的商品数据不存在");
        }
        // 清除不需要的属性以保护数据安全
        product.setPriority(null);
        product.setCreatedUser(null);
        product.setCreatedTime(null);
        product.setModifiedUser(null);
        product.setModifiedTime(null);
        return product;
    }
}