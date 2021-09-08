package com.karthik.inventory.controller;

import com.karthik.inventory.model.entity.Cashier;
import com.karthik.inventory.repository.CashierRepository;
import com.karthik.inventory.util.exceptions.ResourceNotFoundException;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/v1/inventory/")
@Transactional
public class CashierController {

    private static final Logger logger = LoggerFactory.getLogger(CashierController.class);

    @Inject
    private CashierRepository cashierRepository;

    @ApiOperation(value = "Find all Terminal", notes = "List all the open cash Terminal")
    @RequestMapping(value = "/cashterminal/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllTerminal() throws URISyntaxException {
        List<Cashier> result =  cashierRepository.findAll();
        if (result == null) {
            throw new ResourceNotFoundException("There is no available Cash Terminal");
        }
        else{
            logger.debug("Cashier Controller --> Listing all the open Terminals");
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Find Cash Terminal", notes = "Find the Cash Terminal by ID")
    @Async("imsAsyncThreadPoolTaskExecutor")
    @RequestMapping(value = "/cashterminal/query-one/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCashTerminalById(@PathVariable int id) throws URISyntaxException {
        List<Cashier> result = cashierRepository.findOneByCashierId(id);
        if (result == null) {
            throw new ResourceNotFoundException("Terminal not found for the passed cashier Terminal Id ID : " + id);
        }
        else{
            logger.debug("Cashier Controller --> Attempting to find an available Cash Terminal");
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @ApiOperation(value = "Setup Cash Terminal", notes = "Create a new Cash Terminal")
    @Async("imsAsyncThreadPoolTaskExecutor")
    @RequestMapping(value = "/cashterminal/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCashTerminal(@RequestBody Cashier newCashTerminal) throws URISyntaxException {
        cashierRepository.save(newCashTerminal);
        logger.debug("Cashier Controller --> Attempting to create a Cash terminal");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
