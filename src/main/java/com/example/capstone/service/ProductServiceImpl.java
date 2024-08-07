package com.example.capstone.service;

import com.example.capstone.domain.Product;
import com.example.capstone.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 *
 *
 *
 */
@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product findById(int theId) {
        Long theIdl=(long)theId;
        Optional<Product> result = productRepository.findById(theIdl);

        Product theProduct = null;

        if (result.isPresent()) {
            theProduct = result.get();
        }
        else {
            // we didn't find the product id
            throw new RuntimeException("Did not find part id - " + theId);
        }

        return theProduct;
    }

    @Override
    public void save(Product theProduct) {
        productRepository.save(theProduct);

    }

    @Override
    public void deleteById(int theId) {
        Long theIdl=(long)theId;
        productRepository.deleteById(theIdl);
    }
    public List<Product> listAll(String keyword){
        if(keyword !=null){
            return productRepository.findByNameContainingIgnoreCase(keyword);
        }
        return (List<Product>) productRepository.findAll();
    }

    public void buyProduct(long productId) {
        Optional<Product> result = productRepository.findById(productId);

        if (result.isPresent()) {
            Product product = result.get();
            int currentInv = product.getInv();

            if (currentInv > 0) {
                int newInv = Math.max(0, currentInv - 1);

                product.setInv(newInv);

                productRepository.save(product);
            }
        }
    }
}
