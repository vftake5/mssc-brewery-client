package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CustomerClientTest
{
	@Autowired
	CustomerClient client;

	@Test
	void getCustomerById()
	{
		CustomerDto customerDto = client.getCustomerById(UUID.randomUUID());

		assertNotNull(customerDto);
	}

	@Test
	void getCustomerByName()
	{
		CustomerDto customerDto = client.getCustomerByName("TEST");

		assertNotNull(customerDto);
	}

	@Test
	void saveNewCustomer()
	{
		CustomerDto customerDto = CustomerDto.builder().id(UUID.randomUUID()).name("New Boy").build();

		UUID uuid = client.saveNewCustomer(customerDto);

		assertNotNull(uuid);

	}

	@Test
	void updateCustomer()
	{
		CustomerDto customerDto = CustomerDto.builder().id(UUID.randomUUID()).name("New Boy").build();

		client.updateCustomer(customerDto.getId(), customerDto);

	}

	@Test
	void deleteCustomer()
	{
		client.deleteCustomer(UUID.randomUUID());
	}
}