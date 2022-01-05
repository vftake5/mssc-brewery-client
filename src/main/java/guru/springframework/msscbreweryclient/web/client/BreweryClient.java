package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
public class BreweryClient
{
	public final String BEER_PATH_V1 = "/api/v1/beer/";

	@Value("${sfg.brewery.apihost}")
	private String apihost;

	private final RestTemplate restTemplate;
	private WebClient webClient;

	@Autowired
	public BreweryClient(RestTemplateBuilder restTemplateBuilder)
	{
		this.restTemplate = restTemplateBuilder.build();
	}

	@PostConstruct
	private void init()
	{
		this.webClient = WebClient.builder().baseUrl(apihost + BEER_PATH_V1).build();
	}

	public BeerDto getBeerByIdW(UUID beerId)
	{

		Mono<BeerDto> getResult = webClient.get()
			.uri(beerId.toString())
			.retrieve().bodyToMono(BeerDto.class);

		return getResult.block();
	}

	public BeerDto getBeerById(UUID beerId)
	{
		return restTemplate.getForObject(apihost + BEER_PATH_V1 + beerId, BeerDto.class);
	}

	public UUID saveNewBeerW(BeerDto beerDto)
	{
		//restTemplate.postForObject(apihost + BEER_PATH_V1, beerDto, UUID.class);

		Mono<UUID>  getResult = webClient.post().body(Mono.just(beerDto), BeerDto.class).retrieve().bodyToMono(UUID.class);

		return getResult.block();
	}

	public UUID saveNewBeer(BeerDto beerDto)
	{
		return restTemplate.postForObject(apihost + BEER_PATH_V1, beerDto, UUID.class);
	}

	public void updateBeerByIdW(UUID beerId, BeerDto beerDto)
	{
//		restTemplate.put(apihost + BEER_PATH_V1, beerDto);
		webClient.put().uri(beerId.toString()).body(Mono.just(beerDto), BeerDto.class);

	}

	public void updateBeerById(UUID beerId, BeerDto beerDto)
	{
		restTemplate.put(apihost + BEER_PATH_V1 + beerId, beerDto);

	}

	public void deleteBeerW(UUID beerId)
	{
		webClient.delete().uri(beerId.toString());
	}

	public void deleteBeer(UUID beerId)
	{
		restTemplate.delete(apihost + BEER_PATH_V1 + beerId);
	}
}
