package lk.ijse.spring_pos_system.service.serviceImpl;

import jakarta.transaction.Transactional;
import lk.ijse.spring_pos_system.customObj.CustomerErrorResponse;
import lk.ijse.spring_pos_system.customObj.CustomerResponse;
import lk.ijse.spring_pos_system.dto.CustomerDTO;
import lk.ijse.spring_pos_system.entity.CustomerEntity;
import lk.ijse.spring_pos_system.exception.CustomerNotFoundException;
import lk.ijse.spring_pos_system.exception.DataPersistFailedException;
import lk.ijse.spring_pos_system.repository.CustomerRepository;
import lk.ijse.spring_pos_system.service.CustomerService;
import lk.ijse.spring_pos_system.util.Mapping;

import lk.ijse.spring_pos_system.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private final CustomerRepository customerRepository;

    private final Mapping mapping;

    @Override
    public void saveCustomer(CustomerDTO customer) {
        customer.setCustId(Util.createCustomerId());
        CustomerEntity savedCustomer =
                customerRepository.save(mapping.convertToEntity(customer));
        if (savedCustomer == null && savedCustomer.getCustId() == null) {
            throw new DataPersistFailedException("cannot data saved");
        }
    }

    @Override
    public void updateCustomer(CustomerDTO customerDto) {
    Optional<CustomerEntity>tmpCustomerEntity =
            customerRepository.findById(customerDto.getCustId());
    if (!tmpCustomerEntity.isPresent()){
        throw new CustomerNotFoundException("Customer not found");
    }else {
        tmpCustomerEntity.get().setCustName(customerDto.getCustName());
        tmpCustomerEntity.get().setCustAddress(customerDto.getCustAddress());
        tmpCustomerEntity.get().setCustSalary(customerDto.getCustSalary());
    }
    }

    @Override
    public void deleteCustomer(String id) {
        Optional<CustomerEntity> selectedCustomerId =
                customerRepository.findById(id);
        System.out.println("selected id  " + selectedCustomerId.isPresent());
        if (selectedCustomerId.isPresent()) {
            customerRepository.deleteById(id);
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        System.out.println(customerRepository.findAll().size());
        return mapping.convertToDTO(customerRepository.findAll());
    }

    @Override
    public CustomerResponse getCustomer(String custId) {
        if (customerRepository.existsById(custId)){
            Optional<CustomerEntity> customerEntityByCustId = customerRepository.findById(custId);
            return mapping.convertToDTO(customerEntityByCustId.orElse(null));
        }
        else {
            return new CustomerErrorResponse(0, "Customer not found");
        }
    }
}
