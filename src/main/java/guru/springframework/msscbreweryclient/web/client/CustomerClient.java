package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import guru.springframework.msscbreweryclient.web.properties.ServiceProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
public class CustomerClient
{
// Ezek a változók a modul confing yml file-ból töltődnek. A "@ConfigurationProperties" meghatározta, hogy mi a prefix,
//	a property file-ban pedig ugyanezen a néven szerepelnek. Feltöltésükhöz szükséges minden változóhoz a "Setter" eljárás,
//  ezt a Lombok "@Setter" annotációval valósítottam meg.

//	private String apihost;
//	private String customerV1;
//	private String namePath;
//	private String savePath;
//	private String updatePath;
//	private String deletePath;

//	public final String CUSTOMER_PATH_V1 = "/api/v1/customer/";

	public final ServiceProperties properties;

	private WebClient webClient;

	public CustomerClient(ServiceProperties properties)
	{
		this.properties = properties;
	}

	@PostConstruct
	private void init()
	{
		this.webClient = WebClient.builder().baseUrl(properties.apihost() + properties.customerV1()).build();
	}

	public CustomerDto getCustomerById(UUID customerId)
	{
		Mono<CustomerDto> getResult = webClient.get()
			.uri(customerId.toString())
			.retrieve()
			.bodyToMono(CustomerDto.class);

		return getResult.block();
	}


	public CustomerDto getCustomerByName(String name)
	{
		Mono<CustomerDto> getResult = webClient.get()
			.uri(properties.namePath() + name)
			.retrieve()
			.bodyToMono(CustomerDto.class);

		return getResult.block();
	}

	public UUID saveNewCustomer(CustomerDto customerDto)
	{

		Mono<UUID> postResult = webClient.post()
			.uri(properties.savePath())
			.body(Mono.just(customerDto), CustomerDto.class).retrieve().bodyToMono(UUID.class);

		return postResult.block();
	}

	public void updateCustomer(UUID customerId, CustomerDto customerDto)
	{
		webClient.put()
			.uri(properties.updatePath() + customerId.toString())
			.body(Mono.just(customerDto), CustomerDto.class);
	}

	public void deleteCustomer(UUID customerId)
	{
		webClient.delete()
			.uri(properties.deletePath() + customerId.toString());
	}
}
