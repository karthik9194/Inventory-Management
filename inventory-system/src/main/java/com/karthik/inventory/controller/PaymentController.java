package com.karthik.inventory.controller;

import com.karthik.inventory.model.entity.Payment;
import com.karthik.inventory.repository.PaymentRepository;
import com.karthik.inventory.util.exceptions.ResourceNotFoundException;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/v1/inventory")
@Transactional
public class PaymentController {
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Inject
    private PaymentRepository paymentRepository;

    @ApiOperation(value = "")
    @RequestMapping(value = "/paymentlist/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllPayments() throws URISyntaxException {

        List<Payment> result =  paymentRepository.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "")
    @Async("imsAsyncThreadPoolTaskExecutor")
    @RequestMapping(value = "/payment/id", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPaymentById(@RequestBody Long paymentId) throws URISyntaxException {
        List<Payment> result =  paymentRepository.findOneByid(paymentId);
        if (result == null) {
            throw new ResourceNotFoundException("Terminal not found for the passed cashier Terminal Id ID : " + paymentId);
        }
        else{
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

}
