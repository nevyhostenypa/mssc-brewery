package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public CustomerDto getCustomerById(UUID id) {
        return CustomerDto.builder()
                .id(UUID.randomUUID())
                .name("Evzen Novak")
                .build();
    }

    @Override
    public CustomerDto saveNewCustomer(CustomerDto customerDtoToSave) {
        customerDtoToSave.setId(UUID.randomUUID());
        log.info("Customer saved with id : {} ", customerDtoToSave.getId());
        return customerDtoToSave;
    }

    @Override
    public void updateCustomer(UUID id, CustomerDto customerDto) {
        log.info("Customer updated with id : {}", id);
    }

    @Override
    public void deleteCustomer(UUID id) {
        log.info("Customer deleted with id : {}" ,id);

    }
}
