package guru.springframework.msscbrewery.services;


import guru.springframework.msscbrewery.web.model.CustomerDto;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface CustomerService {
    public CustomerDto getCustomerById(UUID id);
    public CustomerDto saveNewCustomer(CustomerDto customerDtoToSave);
    public void updateCustomer(UUID id, CustomerDto customerDto);
    public void deleteCustomer(UUID id);
}
