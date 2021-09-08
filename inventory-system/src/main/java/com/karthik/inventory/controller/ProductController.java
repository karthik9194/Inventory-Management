package com.karthik.inventory.controller;

import com.karthik.inventory.model.entity.Product;
import com.karthik.inventory.repository.ProductRepository;
import com.karthik.inventory.util.exceptions.ResourceNotFoundException;


import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/v1/inventory")
@Transactional
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Inject
    private ProductRepository productRepository;

    @ApiOperation(value = "")
    @RequestMapping(value = "/product/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllProduct() throws URISyntaxException {

        List<Product> result = productRepository.findAll();
        if (result == null) {
            throw new ResourceNotFoundException("No Products Currently found");
        }
        else{
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "")
    @Async("imsAsyncThreadPoolTaskExecutor")
    @RequestMapping(value = "/product/id", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProductById(@RequestBody int productId) throws URISyntaxException {
        List<Product> result = productRepository.findOneByProductid(productId);
        if (result == null) {
            throw new ResourceNotFoundException("Product not found for the passed Product ID : " + productId);
        }
        else{
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "")
    @RequestMapping(value = "/product/upccode", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getProductByUpcCode(@RequestBody String upcCode) throws URISyntaxException {
        List<Product> result = productRepository.findOneByUpcCode(upcCode);
        if (result == null) {
            throw new ResourceNotFoundException("Product not found for the passed UPC code : " + upcCode);
        }
        else{
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "")
    @Async("imsAsyncThreadPoolTaskExecutor")
    @RequestMapping(value = "/product/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateProduct(@RequestBody int productId, @RequestBody Product updateProduct){
        List<Product> productRepositoryOne = productRepository.findOneByProductid(productId);
        if (productRepositoryOne == null) {
            throw new ResourceNotFoundException("Product not found for the passed Product ID : " + productId);
        }
        else{
            productRepositoryOne.get(0).setProductName(updateProduct.getProductName());
            productRepositoryOne.get(0).setStockCount(updateProduct.getStockCount());
            productRepositoryOne.get(0).setStoreNumber(updateProduct.getStoreNumber());
            productRepositoryOne.get(0).setUpcCode(updateProduct.getUpcCode());
            productRepositoryOne.get(0).setPrice(updateProduct.getPrice());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
