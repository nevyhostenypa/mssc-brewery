package guru.springframework.msscbrewery.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbrewery.services.CustomerService;
import guru.springframework.msscbrewery.web.controller.CustomerController;
import guru.springframework.msscbrewery.web.model.BeerDto;
import guru.springframework.msscbrewery.web.model.CustomerDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @MockBean
    private CustomerService customerService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private CustomerDto validCustomer;

    @Before
    public void setUp() {
        validCustomer = CustomerDto.builder().id(UUID.randomUUID())
                .name("Kai Greene").build();
    }

    @Test
    public void handlePost() throws Exception {
        CustomerDto customerDto = CustomerDto.builder().id(UUID.randomUUID()).name("Mr Arnold Monkykean").build();
        String customerDtoJson = objectMapper.writeValueAsString(customerDto);

        given(customerService.saveNewCustomer((any()))).willReturn(customerDto);

        mockMvc.perform(post("/api/v1/customer/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerDtoJson))
                .andExpect(status().isCreated());

    }
    @Test
    public void handlePostBadNameField() throws Exception {
        CustomerDto customerDto = CustomerDto.builder().id(UUID.randomUUID()).name("Hi").build();
        String customerDtoJson = objectMapper.writeValueAsString(customerDto);

        given(customerService.saveNewCustomer((any()))).willReturn(customerDto);

        mockMvc.perform(post("/api/v1/customer/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerDtoJson))
                .andExpect(status().isBadRequest());

    }
    @Test
    public void handleUpdate() throws Exception {
        CustomerDto customerDto = CustomerDto.builder().id(UUID.randomUUID()).name("Mr Arnold Monkykean").build();
        String customerDtoJson = objectMapper.writeValueAsString(customerDto);

        mockMvc.perform(put("/api/v1/customer/" + UUID.randomUUID())
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(customerDtoJson))
                    .andExpect(status().isNoContent());

            then(customerService).should().updateCustomer(any(), any());

    }
    @Test
    public void handleUpdateBadNameField() throws Exception {
        CustomerDto customerDto = CustomerDto.builder().id(UUID.randomUUID()).name("Mr.").build();
        String customerDtoJson = objectMapper.writeValueAsString(customerDto);

        mockMvc.perform(put("/api/v1/customer/" + UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerDtoJson))
                .andExpect(status().isBadRequest());

        then(customerService).shouldHaveNoInteractions();

    }
}

