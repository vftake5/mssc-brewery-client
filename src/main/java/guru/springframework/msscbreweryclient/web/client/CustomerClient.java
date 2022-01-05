package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
@ConfigurationProperties(prefix = "sfg.brewery")
@Setter
public class CustomerClient
{

	private String apihost;
	private String customerV1;
	private String namePath;
	private String savePath;
	private String updatePath;
	private String deletePath;

//	public final String CUSTOMER_PATH_V1 = "/api/v1/customer/";

	private WebClient webClient;


	@PostConstruct
	private void init()
	{
		this.webClient = WebClient.builder().baseUrl(apihost + customerV1).build();
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
			.uri(namePath + name)
			.retrieve()
			.bodyToMono(CustomerDto.class);

		return getResult.block();
	}

	public UUID saveNewCustomer(CustomerDto customerDto)
	{

		Mono<UUID> postResult = webClient.post()
			.uri(savePath)
			.body(Mono.just(customerDto), CustomerDto.class).retrieve().bodyToMono(UUID.class);

		return postResult.block();
	}

	public void updateCustomer(UUID customerId, CustomerDto customerDto)
	{
		webClient.put()
			.uri(updatePath + customerId.toString())
			.body(Mono.just(customerDto), CustomerDto.class);
	}

	public void deleteCustomer(UUID customerId)
	{
		webClient.delete()
			.uri(deletePath + customerId.toString());
	}
}
