package lk.ijse.spring_pos_system.service;

import lk.ijse.spring_pos_system.customObj.CustomerResponse;
import lk.ijse.spring_pos_system.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    void saveCustomer(CustomerDTO customer);
    void updateCustomer(CustomerDTO customer);
    void deleteCustomer(String id);
    List<CustomerDTO> getAllCustomers();
    CustomerResponse getCustomer(String custId);
}
