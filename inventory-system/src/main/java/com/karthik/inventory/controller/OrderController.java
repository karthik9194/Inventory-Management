package com.karthik.inventory.controller;

import com.karthik.inventory.appconstant.InventoryConstants;
import com.karthik.inventory.model.entity.Order;

import com.karthik.inventory.model.entity.Product;
import com.karthik.inventory.repository.OrderRepository;
import com.karthik.inventory.repository.ProductRepository;
import com.karthik.inventory.util.exceptions.ResourceNotFoundException;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.net.URISyntaxException;
import java.util.*;

@RestController
@RequestMapping("/v1/inventory/")
@Transactional
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Inject
    private OrderRepository orderRepository;

    @Inject
    private ProductRepository productRepository;

    @ApiOperation(value = "Find all the Orders happened so far!", notes = "List all the Orders")
    @RequestMapping(value = "/order/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllOrders() throws URISyntaxException {

        List<Order> result = orderRepository.findAll();
        if (result == null) {
            throw new ResourceNotFoundException("No Orders Currently found");
        }
        else{
            logger.debug("Order Controller --> Attempting to find all the Orders happened so far");
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Find an Order", notes = "Pulling Order details based on ID")
    @Async("imsAsyncThreadPoolTaskExecutor")
    @RequestMapping(value = "/order/query-one/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOrderById(@PathVariable String id) throws URISyntaxException {
        List<Order> result = orderRepository.findOneByOrderId(id);
        if (result == null) {
            throw new ResourceNotFoundException("Order not found for the requested Order ID : " + id);
        }
        else{
            logger.debug("Order Controller --> find the order details for the  " + id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Product and Quantity", notes = "Find all Products and Quantity on each Order")
    @RequestMapping(value = "/order/quantity/{orderId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Map<String, Integer>>> getOrderAndQuantityforId(@PathVariable String orderId) throws URISyntaxException {
        List<Order> orderDetails = orderRepository.findOneByOrderId(orderId);
        if (orderDetails == null) {
            throw new ResourceNotFoundException("Order not found for the requested Order ID : " + orderId);
        }
        else{
            logger.debug("Order Controller --> Listing all the open Terminals");
            List<Map<String, Integer>> result = new ArrayList<>();

            int index = 0;
            while (orderDetails.size() > index)
            {
                List<Product> productData = productRepository.findOneByProductid(orderDetails.get(index++).getProductId());
                if (productData == null) {
                    throw new ResourceNotFoundException("Product Details Could not be found: " + orderDetails.get(index++).getProductId());
                }
                else{
                    Map<String, Integer> map = new HashMap<String, Integer>();
                    map.put(productData.get(0).getProductName(), orderDetails.get(index++).getQuantity());
                    result.add(map);
                }
            }

            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Update Order", notes = "Adding items to the order based on UPC code and Quantity")
    @Async("imsAsyncThreadPoolTaskExecutor")
    @RequestMapping(value = "/order/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateOrderByUPCAndQuantity(@RequestBody String orderId, @RequestBody String upcCode, @RequestBody Integer quantity){
        List<Product> productData = productRepository.findOneByUpcCode(upcCode);
        if (productData == null) {
            throw new ResourceNotFoundException("Product details could not be found for the passed UPC code : " + upcCode);
        }
        else{
            logger.debug("Order Controller --> Begin process for updating the Order ID");

            Order orderData = new Order();

            orderData.setOrderId(orderId);
            orderData.setQuantity(quantity);
            orderData.setOrderstatus(InventoryConstants.OrderStatus.PENDING.toString());
            orderData.setAmount_Txn(productData.get(0).getPrice() * quantity);
            orderData.setDateCreated(new Date());
            orderData.setStoreId(productData.get(0).getStoreNumber());

            logger.debug("Order Controller --> End Process of Order Updating");

            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @ApiOperation(value = "Determining Total", notes = "Finding the Total Price for the Order so far!")
    @RequestMapping(value = "/order/total/amount/{orderId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Double> getTotalOrderPrice(@PathVariable String orderId) throws URISyntaxException {
        List<Order> result = orderRepository.findOneByOrderId(orderId);
        if (result == null) {
            throw new ResourceNotFoundException("Unable to find the total price as Order could not be found : " + orderId);
        }
        else{
            logger.debug("Order Controller --> Listing all the open Terminals");
            Double totalAmount = result.stream().filter(o -> o.getAmountTxn() > 0).mapToDouble(o -> o.getAmountTxn()).sum();
            result.stream().filter(amt -> amt.getAmountTxn() > 0.00).mapToDouble(amt -> amt.getAmountTxn()).sum();
            return new ResponseEntity<>(totalAmount, HttpStatus.OK);
        }
    }
}
