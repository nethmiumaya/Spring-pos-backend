package lk.ijse.spring_pos_system.controller;


import jakarta.validation.Valid;
import lk.ijse.spring_pos_system.customObj.CustomerResponse;
import lk.ijse.spring_pos_system.dto.CustomerDTO;
import lk.ijse.spring_pos_system.exception.CustomerNotFoundException;
import lk.ijse.spring_pos_system.exception.DataPersistFailedException;
import lk.ijse.spring_pos_system.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    @Autowired
    private final CustomerService customerService;
    static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCustomer(@Valid @RequestBody CustomerDTO customer) {

        try {
            customerService.saveCustomer(customer);
            logger.info("customer saved successfully");
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (DataPersistFailedException e) {
            logger.error("Error saving customer",e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            logger.error("Error saving customer",e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PutMapping(value = "/{custId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateCustomer(
            @Valid
            @PathVariable("custId") String custId,
            @RequestBody CustomerDTO customerDTO
    ) {
        try {
            customerDTO.setCustId(custId);
            customerService.updateCustomer(customerDTO);
            logger.info("Customer Updated Successfully");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CustomerNotFoundException e) {
            logger.info("Customer Updated Failed");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.info("Customer Updated Failed");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/allcustomers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerDTO> FindAll() {
        logger.info("Customer Retrieved Successfully");
        logger.info("Customers Retrieved Successfully");
        return customerService.getAllCustomers();
    }

    @DeleteMapping(value = "/{custId}")
    public ResponseEntity<Void> deleteCustomer(@Valid @PathVariable("custId") String custId) {
        try {
            System.out.println("delete customer " + custId);
            customerService.deleteCustomer(custId);
            logger.info("Customer Deleted Successfully");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (CustomerNotFoundException e) {
            logger.info("Customer Deleted Failed");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error occurred while deleting customer",e);
            return new ResponseEntity<>
                    (HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/{custId}",produces =MediaType.APPLICATION_JSON_VALUE )
    public CustomerResponse getUserId(@Valid @PathVariable("custId") String userId){
        logger.info("Get Customer By Id");
        return customerService.getCustomer(userId);
    }

}